package org.joker.oscp.gateway.controller;

import com.baomidou.mybatisplus.plugins.Page;
import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.common.CommonResult;
import org.joker.oscp.common.util.CurrentUser;
import org.joker.oscp.gateway.service.OrderFeignedApi;
import org.joker.oscp.system.api.order.vo.OrderVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 将订单聚合到网关
 * @author JOKER
 */
@Slf4j
@RestController
public class OrderControllerPre {

    private OrderFeignedApi orderFeignedApi;

    @Autowired
    public OrderControllerPre(OrderFeignedApi orderFeignedApi){
       this.orderFeignedApi = orderFeignedApi;
    }

    @RequestMapping(value = "/buyTickets", method = RequestMethod.POST)
    public CommonResult buyTickets(Long fieldId, String soldSeats, String seatsName) {
        // 验证出票是否为真
        boolean isTrue = orderFeignedApi.isTrueSeats(fieldId,soldSeats);
        // 已售座位里是否重复
        boolean isNotSold = orderFeignedApi.isNotSoldSeats(fieldId, soldSeats);

        // 验证上述两项必须都为真，才可创建订单信息、
        if (isTrue && isNotSold) {
            // 创建订单信息，获取登录人
            Long userId = CurrentUser.getCurrentUser();
            if (userId == null) {
                return CommonResult.failed("用户未登录");
            }
            OrderVO orderVO = orderFeignedApi.saveOrderInfo(fieldId, soldSeats, seatsName, userId);
            if (orderVO == null) {
                log.error("购票未成功！！！");
                return CommonResult.serviceFailed("购票业务异常");
            } else {
                return CommonResult.success(orderVO);
            }
        } else {
            return CommonResult.serviceFailed("订单中的座位编号有问题");
        }
    }

    @RequestMapping(value = "/getOrderInfo", method = RequestMethod.POST)
    public CommonResult getOrderInfo(
            @RequestParam(name = "nowPage", required = false, defaultValue = "1")Integer nowPage,
            @RequestParam(name = "nowPage", required = false, defaultValue = "5")Integer pageSize) {
        // 获取当前登录人
        Long userId = CurrentUser.getCurrentUser();

        // 使用当前登录人获取已经购买的订单
        Page<OrderVO> page = new Page<>(nowPage, pageSize);
        if (userId != null){
            Page<OrderVO> result = orderFeignedApi.getOrderByUserId(userId, page);
            int totalPages = (int)result.getPages();
            List<OrderVO> orderVOList = new ArrayList<>();
            orderVOList.addAll(result.getRecords());
            return CommonResult.success(nowPage, totalPages, orderVOList);
        } else {
            return CommonResult.serviceFailed("用户未登陆");
        }
    }

}

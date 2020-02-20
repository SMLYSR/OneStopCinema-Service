package org.joker.oscp.gateway.service;

import com.baomidou.mybatisplus.plugins.Page;
import org.joker.oscp.system.api.order.vo.OrderVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>远程调用</p>
 * 订单中心服务
 *
 * @author JOKER
 */
@Service
@FeignClient(value = "oscp-order-center", path = "/order")
public interface OrderFeignedApi {

    /**
     * <p>【远程调用】</p>
     * 验证售出的票是否为真
     *
     * @param fieldId 影厅Id
     * @param seats   选择的座位号
     * @return 真票即返回true
     */
    @PostMapping(value = "/isTrueSeats")
    boolean isTrueSeats(@RequestParam(value = "fieldId") Long fieldId,
                        @RequestParam(value = "seats") String seats);

    /**
     * <p>【远程调用】</p>
     * 已经销售的座位里，有没有这些座位
     *
     * @param fieldId 影厅Id
     * @param seats   选择的座位号
     * @return 没有即返回true
     */
    @PostMapping(value = "/isNotSoldSeats")
    boolean isNotSoldSeats(@RequestParam(value = "fieldId") Long fieldId,
                           @RequestParam(value = "seats") String seats);

    /**
     * 创建订单信息
     *
     * @param fieldId   影厅Id
     * @param soldSeats 该订单售出的座位
     * @param seatsName 座位名称
     * @param userId    下单用户
     * @return {@link OrderVO}
     */
    @PostMapping(value = "/saveOrderInfo")
    OrderVO saveOrderInfo(@RequestParam(value = "fieldId")Long fieldId,
                          @RequestParam(value = "soldSeats")String soldSeats,
                          @RequestParam(value = "seatsName")String seatsName,
                          @RequestParam(value = "userId")Long userId);

    /**
     * 使用当前登陆人获取已经购买的订单
     * @param userId 用户Id
     * @param page 分页项
     * @return 分页项
     */
    @PostMapping(value = "/getOrderByUserId")
    Page<OrderVO> getOrderByUserId(@RequestParam(value = "userId")Long userId,
                                   @RequestParam(value = "page")Page<OrderVO> page);
}

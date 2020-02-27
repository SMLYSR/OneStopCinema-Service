package org.joker.oscp.gateway.controller;

import org.joker.oscp.common.CommonResult;
import org.joker.oscp.common.util.CurrentUser;
import org.joker.oscp.gateway.service.PaymentFeignedApi;
import org.joker.oscp.system.api.payment.PaymentCenterApi;
import org.joker.oscp.system.api.payment.vo.AliPayInfoVO;
import org.joker.oscp.system.api.payment.vo.AliPayResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>服务聚合</p>
 * 支付中心前级控制器
 *
 * @author JOKER
 */
@RestController
public class PayCenterControllerPre {
    private static final Integer TRY_NUMS = 4 ;

    private PaymentFeignedApi paymentFeignedApi;

    @Autowired
    public PayCenterControllerPre(PaymentFeignedApi paymentFeignedApi) {
        this.paymentFeignedApi = paymentFeignedApi;
    }

    @PostMapping(value = "/getPayInfo")
    public CommonResult getPayInfo(@RequestParam("orderId") String orderId) {
        Long userId = CurrentUser.getCurrentUser();
        if (userId == null) {
            return CommonResult.serviceFailed("用户未登录");
        }
        // 订单二维码返回结果
        AliPayInfoVO aliPayInfoVO = paymentFeignedApi.getQRCode(orderId);
        return CommonResult.success(aliPayInfoVO);
    }

    @PostMapping(value = "/getPayResult")
    public CommonResult getPayResult(@RequestParam("orderId")String orderId,
                                     @RequestParam(name="tryNums",required = false,defaultValue = "1")Integer tryNums) {
        Long userId = CurrentUser.getCurrentUser();
        if (userId == null) {
            return CommonResult.serviceFailed("用户未登录");
        }

        if (tryNums >= TRY_NUMS) {
            return CommonResult.serviceFailed("订单支付失败，请稍后重试");
        } else {
            AliPayResultVO aliPayResultVO = paymentFeignedApi.getOrderStatus(orderId);
            if (aliPayResultVO == null || aliPayResultVO.getOrderId().isEmpty()) {
                AliPayResultVO serviceFailVO = new AliPayResultVO();
                serviceFailVO.setOrderId(orderId);
                serviceFailVO.setOrderStatus(0);
                serviceFailVO.setOrderMsg("支付成功！");
                return CommonResult.success(serviceFailVO);
            }
            return CommonResult.success(aliPayResultVO);
        }
    }
}

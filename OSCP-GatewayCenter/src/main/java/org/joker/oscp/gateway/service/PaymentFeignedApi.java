package org.joker.oscp.gateway.service;

import org.joker.oscp.system.api.payment.vo.AliPayInfoVO;
import org.joker.oscp.system.api.payment.vo.AliPayResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * <p>远程调用</p>
 * 支付中心服务
 *
 * @author JOKER
 */
@Service
@FeignClient(value = "oscp-payment-center", path = "/pay")
public interface PaymentFeignedApi {

    /**
     * 获取订单二维码
     *
     * @param orderId 交易订单
     * @return {@link AliPayInfoVO}
     */
    @PostMapping(value = "/getQRCode")
    AliPayInfoVO getQRCode(@RequestParam(value = "orderId") String orderId);

    /**
     * 获取当前订单状态
     *
     * @param orderId 交易订单
     * @return {@link AliPayResultVO}
     */
    @PostMapping(value = "/getOrderStatus")
    AliPayResultVO getOrderStatus(@RequestParam(value = "orderId")String orderId);

}

package org.joker.oscp.pay.controller;

import org.joker.oscp.system.api.payment.PaymentCenterApi;
import org.joker.oscp.system.api.payment.vo.AliPayInfoVO;
import org.joker.oscp.system.api.payment.vo.AliPayResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author JOKER
 */
@RestController
public class PaymentCenterController {

    private PaymentCenterApi paymentCenterApi;

    @Autowired
    public PaymentCenterController(PaymentCenterApi paymentCenterApi) {
        this.paymentCenterApi = paymentCenterApi;
    }

    @PostMapping(value = "/getQRCode")
    public AliPayInfoVO getQRCode(@RequestParam(value = "orderId") String orderId) {
        return paymentCenterApi.getQRCode(orderId);
    }

    @PostMapping(value = "/getOrderStatus")
    public AliPayResultVO getOrderStatus(@RequestParam(value = "orderId")String orderId) {
        return paymentCenterApi.getOrderStatus(orderId);
    }

}

package org.joker.oscp.pay.controller;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.system.api.payment.PaymentCenterApi;
import org.joker.oscp.system.api.payment.vo.AliPayInfoVO;
import org.joker.oscp.system.api.payment.vo.AliPayResultVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class testController {

    private PaymentCenterApi paymentCenterApi;

    @Autowired
    public testController(PaymentCenterApi paymentCenterApi) {
        this.paymentCenterApi = paymentCenterApi;
    }


    @GetMapping(value = "/contextLoads")
    public String contextLoads() {
        log.info("{}", this.paymentCenterApi);
        return this.paymentCenterApi.toString();
    }


    @GetMapping(value = "/testQR")
    public void testGetQRCode() {
        AliPayInfoVO qrCode = paymentCenterApi.getQRCode("d69ec1f582944f808ba1d68821ca4520");
        log.info("alipay{}",qrCode);
    }

    @GetMapping(value = "/testGetOrderStatus")
    public void testGetOrderStatus() {
        AliPayResultVO orderStatus = paymentCenterApi.getOrderStatus("d69ec1f582944f808ba1d68821ca4520");
        log.info("状态：{}", orderStatus);
    }

    @GetMapping(value = "/test")
    public String test() {
        return "HELLO!";
    }

}

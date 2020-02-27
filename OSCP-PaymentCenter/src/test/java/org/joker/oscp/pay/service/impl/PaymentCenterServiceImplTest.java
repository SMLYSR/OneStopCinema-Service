package org.joker.oscp.pay.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.joker.oscp.system.api.payment.PaymentCenterApi;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentCenterServiceImplTest {

    @Autowired
    private PaymentCenterServiceImpl paymentCenterApi;

    @Test
    public void contextLoad() {
        log.info("{}",paymentCenterApi);
    }




}
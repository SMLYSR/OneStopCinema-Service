package org.joker.oscp.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 订单中心启动类
 * @author JOKER
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class PaymentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentCenterApplication.class, args);
    }

}

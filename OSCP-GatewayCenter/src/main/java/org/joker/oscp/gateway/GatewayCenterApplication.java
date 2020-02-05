package org.joker.oscp.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * 网关中心启动类
 * @author JOKER
 */
@EnableFeignClients
@SpringCloudApplication
@EnableZuulProxy
public class GatewayCenterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayCenterApplication.class, args);
    }
}

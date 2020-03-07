package org.joker.oscp.community;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 用户中心启动类
 * @author JOKER
 */
@EnableDiscoveryClient
@SpringBootApplication
public class CommunityServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityServiceApplication.class,args);
    }
}

package org.joker.oscp.cinema;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 影院服务启动类
 * @author JOKER
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CinemaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CinemaServiceApplication.class, args);
    }
}

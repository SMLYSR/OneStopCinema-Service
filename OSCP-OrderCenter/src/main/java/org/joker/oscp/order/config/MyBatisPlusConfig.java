package org.joker.oscp.order.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 * @author JOKER
 */
@Configuration
@MapperScan(basePackages = {"org.joker.oscp.order.dao"})
public class MyBatisPlusConfig {
}

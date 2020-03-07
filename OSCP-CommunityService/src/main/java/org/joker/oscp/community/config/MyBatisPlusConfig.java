package org.joker.oscp.community.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis配置类
 * @author JOKER
 */
@EnableTransactionManagement
@Configuration
@MapperScan({"org.joker.oscp.community.dao"})
public class MyBatisPlusConfig {

    /**
     * 配置分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

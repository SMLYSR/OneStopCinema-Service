package org.joker.oscp.user.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * MyBatis配置类
 * @author JOKER
 */
@EnableTransactionManagement
@Configuration
@MapperScan({"org.joker.oscp.user.dao"})
public class MyBatisPlusConfig {

    private DataSource dataSource;

    @Autowired
    public MyBatisPlusConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 配置分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}

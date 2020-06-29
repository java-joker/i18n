package com.i18n.core.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-16 14:36
 */
@Configuration
@EnableTransactionManagement
@MapperScan(value = {"com.i18n.core.**.mapper.**"})
public class MybatisPlusConfig {

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
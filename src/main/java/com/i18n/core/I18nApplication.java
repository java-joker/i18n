package com.i18n.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-16 18:33
 */
@SpringBootApplication
@EnableScheduling
public class I18nApplication {

    public static void main(String[] args) {
        SpringApplication.run(I18nApplication.class, args);
    }
}

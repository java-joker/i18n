package com.i18n.core.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

import com.i18n.core.resolver.AcceptHeaderResolver;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-17 13:29
 */
@Configuration
public class LocalConfig {

    @Bean
    public LocaleResolver localeResolver() {
        AcceptHeaderResolver resolver = new AcceptHeaderResolver();
        resolver.setDefaultLocale(Locale.SIMPLIFIED_CHINESE);

        return resolver;
    }
}

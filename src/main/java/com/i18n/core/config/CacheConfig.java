package com.i18n.core.config;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-17 13:29
 */
@Configuration
public class CacheConfig {

    public static final String I18N_KEY = "i18nMap";

    @Bean("i18nMapCache")
    public Cache<String, Map<String, String>> i18nMapCache() {
        return Caffeine.newBuilder().recordStats().expireAfterWrite(3, TimeUnit.MINUTES).maximumSize(10000).build();
    }
}

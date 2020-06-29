package com.i18n.core.schedule;

import java.util.Map;

import com.i18n.core.service.I18nService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.i18n.core.config.CacheConfig;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-16 15:53
 */
@Component
@Slf4j
public class I18nSchedule {

    @Autowired
    private I18nService i18nService;
    @Autowired
    @Qualifier("i18nMapCache")
    private Cache<String, Map<String, String>> i18nMapCache;

    /**
     * 刷新translate存在ehcache的缓存
     */
    @Scheduled(cron = "${schedule.cron.i18n}")
    public void refreshI18n() {
        try {
            log.info("【刷新翻译caffeine的缓存】定时任务刷新翻译caffeine缓存，每1分钟执行一次");

            CacheStats cacheStats = i18nMapCache.stats();
            log.info("【刷新翻译caffeine的缓存】统计明细,cacheStats:{}", cacheStats.toString());

            i18nMapCache.put(CacheConfig.I18N_KEY, i18nService.getRefreshI18nMap());
            log.info("刷新翻译caffeine的缓存】成功");
        } catch (Exception e) {
            log.error("【刷新翻译caffeine的缓存】失败，key:{}，i18n={}", CacheConfig.I18N_KEY, e.toString(), e);
        }
    }

}

package com.i18n.core.service.impl;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.i18n.core.service.MessageSourceService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangbowen@heytea.com
 * @since 2020-06-28 11:14
 */
@Slf4j
@Service
public class MessageSourceServiceImpl implements MessageSourceService, ApplicationContextAware {

    private static MessageSource messageSource;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        messageSource = applicationContext.getBean(MessageSource.class);
    }

    /**
     * 获取翻译的内容
     * 
     * @param msgKey
     * @param args
     * @return
     */
    @Override
    public String get(String msgKey, String... args) {

        return messageSource.getMessage(msgKey, args, LocaleContextHolder.getLocale());

    }
}

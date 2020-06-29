package com.i18n.core.service;

/**
 * @author zhangbowen@heytea.com
 * @since 2020-06-28 11:14
 */
public interface MessageSourceService {

    /**
     * 获取翻译的内容
     * 
     * @param msgKey
     * @param args
     * @returno
     */
    String get(String msgKey, String... args);
}

package com.i18n.core.enums;

import lombok.Getter;

/**
 * @author xiezhenzhong
 * @since 2019-09-11 22:22
 */
@Getter
public enum LanguageEnum {

    /**
     * 中文简体
     */
    ZH_CN("zh-CN", "中文简体"),
    /**
     * 中国香港繁体
     */
    ZH_HK("zh-HK", "香港繁体"),
    /**
     * 中国台湾繁体
     */
    ZH_TW("zh-TW", "台湾繁体"),
    /**
     * 英文
     */
    EN_US("en-US", "英文"),
    /**
     * 日语
     */
    JA_JP("ja-JP", "日语");

    /**
     * 国际化语言code
     */
    private String code;
    /**
     * 国际语言中文名称
     */
    private String message;

    LanguageEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

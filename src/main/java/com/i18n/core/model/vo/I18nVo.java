package com.i18n.core.model.vo;

import com.i18n.core.model.entity.I18n;

import lombok.Data;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-22 10:15
 */
@Data
public class I18nVo {

    private Integer id;
    /**
     * 关联ID
     */
    private Integer refId;
    /**
     * 编号，不能类型必须唯一
     */
    private String refType;
    /**
     * 语言类型，国际通用列表:zh-CN(中文)，zh-HK(香港繁体)，zh-TW(台湾繁体)，en-US(英文)，ja-JP(日语)
     */
    private String languageType;
    /**
     * 翻译内容
     */
    private String translateText;

    public I18nVo(I18n i18n) {
        this.id = i18n.getId();
        this.refId = i18n.getRefId();
        this.refType = i18n.getRefType();
        this.languageType = i18n.getLanguageType();
        this.translateText = i18n.getTranslateText();
    }
}

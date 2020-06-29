package com.i18n.core.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.i18n.core.model.form.I18nForm;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-16 15:53
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class I18n implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
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

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public I18n buildI18n(I18nForm form) {
        this.id = form.getId();
        this.refId = form.getRefId();
        this.refType = form.getRefType();
        this.languageType = form.getLanguageType();
        this.translateText = form.getTranslateText();
        return this;
    }
}

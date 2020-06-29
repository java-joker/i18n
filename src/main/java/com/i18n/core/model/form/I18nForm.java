package com.i18n.core.model.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

import com.i18n.core.validate.ValidationGroups;

import lombok.Data;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-22 09:44
 */
@Data
public class I18nForm {

    /**
     * 主键
     */
    @NotNull(groups = ValidationGroups.Update.class)
    @Null(groups = ValidationGroups.Insert.class)
    private Integer id;

    /**
     * 关联ID
     */
    @NotNull
    private Integer refId;
    /**
     * RefTypeEnum.class
     */
    @NotBlank
    private String refType;
    /**
     * LanguageEnum.class
     */
    @NotBlank
    private String languageType;
    /**
     * 翻译后的内容
     */
    @NotBlank
    private String translateText;
}

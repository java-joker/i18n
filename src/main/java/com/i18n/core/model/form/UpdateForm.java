package com.i18n.core.model.form;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-22 09:57
 */
@Data
public class UpdateForm {
    /**
     * 翻译后的语言建议同时翻译多种语言(原始语言，默认语言类型是中文)
     */
    @NotEmpty
    @Valid
    private List<I18nForm> i18nList;
}

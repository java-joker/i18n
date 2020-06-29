package com.i18n.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.i18n.core.enums.RefTypeEnum;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-16 14:36
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface I18n {
    /**
     * 指定要翻译字段的主键id名称，eg：refId=id
     *
     * @return
     */
    String refIdAlias();

    /**
     * 按顺序翻译，如果第一个值为空，就用第二个，以此类推
     *
     * @return
     */
    RefTypeEnum[] refType();
}

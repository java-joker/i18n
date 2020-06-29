package com.i18n.core.enums;

import lombok.Getter;

/**
 * @author zhangbowen@heytea.com
 * @since 2020-06-28 11:18
 */
@Getter
public enum DiscountEnum {
    DESC("discount.desc");

    DiscountEnum(String code) {
        this.code = code;
    }

    private String code;

}

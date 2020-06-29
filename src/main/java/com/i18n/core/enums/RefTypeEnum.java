package com.i18n.core.enums;

import lombok.Getter;

/**
 * @author panguangchao
 */
@Getter
public enum RefTypeEnum {

    /**
     * 商品名称
     */
    PRODUCT_NAME("1", "商品名称");

    private String code;
    private String message;

    RefTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

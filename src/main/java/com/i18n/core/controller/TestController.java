package com.i18n.core.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.i18n.core.annotation.I18n;
import com.i18n.core.enums.DiscountEnum;
import com.i18n.core.enums.RefTypeEnum;
import com.i18n.core.service.MessageSourceService;

import lombok.Data;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-16 15:51
 */
@RestController
public class TestController {

    @Autowired
    private MessageSourceService messageSourceService;

    @GetMapping("test")
    public ShopDemo
        test(@RequestHeader(value = "Accept-Language", defaultValue = "zh-CN", required = false) String language) {
        ShopDemo shopDemo = new ShopDemo();
        return shopDemo;
    }

    @Data
    class ShopDemo {
        private Integer id = 1;

        @I18n(refIdAlias = "id", refType = {RefTypeEnum.PRODUCT_NAME})
        private String name = "中文商品名称";
    }

    @GetMapping("test1")
    public String
        test1(@RequestHeader(value = "Accept-Language", defaultValue = "zh-CN", required = false) String language) {
        return messageSourceService.get(DiscountEnum.DESC.getCode(), "\uD83D\uDE04");
    }
}

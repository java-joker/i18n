package com.i18n.core.advice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.i18n.core.service.I18nService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-16 17:48
 */
@ControllerAdvice
@Slf4j
public class I18nResponseBodyAdvice implements ResponseBodyAdvice {
    @Autowired
    private I18nService i18nService;

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        // 这里直接返回true,表示对任何handler的responseBody都调用beforeBodyWrite方法
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
        Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        // resBody就是controller方法中返回的值，对其进行修改后再return就可以了
        request.getHeaders();

        // 当视图的多语言有值的时候
        HttpHeaders httpHeaders = request.getHeaders();
        if (httpHeaders == null) {
            return body;
        }
        List<String> languageList = httpHeaders.get("Accept-Language");
        if (CollectionUtils.isEmpty(languageList)) {
            return body;
        }
        String language = languageList.get(0);
        if (StringUtils.isEmpty(language)) {
            return body;
        }
        return i18nService.translate(language, body);
    }
}
package com.i18n.core.resolver;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

/**
 * @author zhangbowen
 * @email 2791951533@qq.com
 * @since 2020-06-17 13:29
 */
public class AcceptHeaderResolver extends AcceptHeaderLocaleResolver {

    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        // 可以改写输入请求的实现逻辑
        return super.resolveLocale(request);
    }
}

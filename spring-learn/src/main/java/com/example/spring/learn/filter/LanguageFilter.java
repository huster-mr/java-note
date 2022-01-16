package com.example.spring.learn.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.i18n.LocaleContextHolder;

import javax.servlet.*;
import java.io.IOException;
import java.util.Locale;

public class LanguageFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(LanguageFilter.class);

    private static final String ZH_CN = "zh_cn";
    private static final String EN = "en";
    private static final String EN_US = "en-US";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        String language = servletRequest.getParameter("lang");
        LocaleContextHolder.setLocale(getLocale(language));
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private Locale getLocale(String language) {
        if (ZH_CN.equalsIgnoreCase(language)) {
            return Locale.CHINA;
        } else if (EN.equalsIgnoreCase(language)) {
            return Locale.ENGLISH;
        } else if (EN_US.equalsIgnoreCase(language)) {
            return Locale.US;
        } else {
            return Locale.CHINA;
        }
    }

    @Override
    public void destroy() {
    }
}

package com.example.spring.learn.filter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author
 * @Description
 * @Date 2021/12/13
 */
@ServletComponentScan
@Component
@WebFilter(filterName = "corsFilter", urlPatterns = "/filter/interceptor/test/*")
public class CorsFilter implements Filter {
    private static final Logger logger = LoggerFactory.getLogger(CorsFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("corsFilter init!");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain) throws IOException, ServletException {
        String safeOrigin = "*";
        logger.info("doFilter start!");
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;
        if (StringUtils.isNoneBlank(request.getHeader("origin"))) {
            safeOrigin = request.getHeader("origin");
        }
        response.setHeader("Access-Control-Allow-Origin", safeOrigin);
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, OPTIONS, DELETE");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-control-Allow-Headers",
                "Origin, X-Requestd-With, Content-Type, Accept, Content-Disposition");
        response.setHeader("Access-Control-Expose-Headers",
                "Cache-Control, Content-Language, Content-Type, Expires, Last-Modified, Pragma, X-Requested-With, Content-Disposition");
        filterChain.doFilter(req, resp);
    }

    @Override
    public void destroy() {
        logger.info("corsFilter destroy");
    }
}

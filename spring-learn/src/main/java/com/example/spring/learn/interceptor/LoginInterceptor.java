package com.example.spring.learn.interceptor;

import com.example.spring.learn.annotation.LoginRequired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author
 * @Description
 * @Date 2021/12/14
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("进入拦截器了");
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        LoginRequired annotation = ((HandlerMethod) handler).getMethod().getAnnotation(LoginRequired.class);
        if (annotation == null) {
            return true;
        }
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().print("需要登录");
        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}

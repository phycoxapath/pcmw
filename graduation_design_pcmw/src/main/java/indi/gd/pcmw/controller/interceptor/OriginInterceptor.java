package indi.gd.pcmw.controller.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OriginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getHeader("Origin") == null){
            response.getWriter().write("invalid access");
            return false;
        }else {
            return request.getHeader("Origin").equals("http://localhost:8080");
        }

    }
}

//package com.zxg.demoForOauth2.config;
//
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
//import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.io.IOException;
//@Component
//@Slf4j
//public class OpenCheckInterceptor implements HandlerInterceptor {
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
//        // 获取请求路径
//        String requestURI = request.getRequestURI();
//        log.info(requestURI);
//        request.getParameterMap().forEach((k, v) -> {
//            log.info(k + ":" + v);
//        });
//        // 是否含有我指定的注解
//        if (requestURI.equals("/oauth/token")){
//
//            // 含有需要更改的请求路径则需要更正路径  这里我将路径中的 /open  去除了
//            String replacePath = requestURI.replace("/oauth", "/oauth2");
//            log.info(replacePath);
//            log.info("dsdada");
//            // 转发
//            request.getRequestDispatcher(replacePath).forward(request, response);
//        }
//        // 最终放行请求
//        return true;
//    }
//}

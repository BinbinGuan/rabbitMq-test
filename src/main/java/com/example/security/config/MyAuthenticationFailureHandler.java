//package com.example.security.config;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
///**
// * @author: GuanBin
// * @date: Created in 上午11:17 2020/9/22
// */
//@Component
//public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
//    @Autowired
//    private ObjectMapper mapper;
//
//
//    @Override
//    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
//        httpServletResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        httpServletResponse.setContentType("application/json;charset=utf-8");
//        httpServletResponse.getWriter().write(mapper.writeValueAsString(e.getMessage()));
//    }
//}

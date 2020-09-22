package com.example.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: GuanBin
 * @date: Created in 上午10:57 2020/9/22
 */
@Component
public class MyAuthenticationSucessHandler implements AuthenticationSuccessHandler {
    private static final ObjectMapper mapper = new ObjectMapper();
    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(mapper.writeValueAsString(authentication));

//        //成功登录跳转
//        SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
//        redirectStrategy.sendRedirect(httpServletRequest, httpServletResponse, savedRequest.getRedirectUrl());

    }
}

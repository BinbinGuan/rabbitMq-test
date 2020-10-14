//package com.example.security.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
///**
// * @author: GuanBin
// * @date: Created in 上午9:59 2020/9/22
// */
//@Configuration
//public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private MyAuthenticationSucessHandler authenticationSucessHandler;
//    private MyAuthenticationFailureHandler authenticationFailureHandler;
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.formLogin() // 表单登录
//                // http.httpBasic() // HTTP Basic
//                .loginPage("/authentication/require") // 登录跳转 URL
//                .loginProcessingUrl("/login")
//                .successHandler(authenticationSucessHandler)
//                .failureHandler(authenticationFailureHandler)
//                .and()
//                .authorizeRequests() // 授权配置
//                .antMatchers("/authentication/require", "/static/login.html").permitAll() // 登录跳转 URL 无需认证
//                .anyRequest()  // 所有请求
//                .authenticated()// 都需要认证//        http.httpBasic().and().authorizeRequests().anyRequest().authenticated();
//                .and().csrf().disable();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//
//}

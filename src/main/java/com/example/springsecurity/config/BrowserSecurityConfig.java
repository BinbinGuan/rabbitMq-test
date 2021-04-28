//package com.example.springsecurity.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
////        http.formLogin() // 表单登录
////                // http.httpBasic() // HTTP Basic
////                .loginPage("/login")
////                .loginProcessingUrl("/login")
////                .and()
////                .authorizeRequests() // 授权配置
////                .antMatchers("/login.html").permitAll()
////                .anyRequest()  // 所有请求
////                .authenticated()
////                .and()
////                .csrf().disable(); // 都需要认证
//        http
//                .authorizeRequests()
//                .antMatchers("/login.html").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                //指定登录页的路径
//                .loginPage("/login.html")
//                //指定自定义form表单请求的路径
//                .loginProcessingUrl("/authentication/form")
//                .failureUrl("/login?error")
//                .defaultSuccessUrl("/success")
//                //必须允许所有用户访问我们的登录页（例如未验证的用户，否则验证流程就会进入死循环）
//                //这个formLogin().permitAll()方法允许所有用户基于表单登录访问/login这个page。
//                .permitAll();
//        //默认都会产生一个hiden标签 里面有安全相关的验证 防止请求伪造 这边我们暂时不需要 可禁用掉
//        http .csrf().disable();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//}

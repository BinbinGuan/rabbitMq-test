//package com.example.security;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//
///**
// * @author: GuanBin
// * @date: Created in 上午9:58 2020/9/22
// */
//@RestController
//public class TestController {
//    @GetMapping("hello")
//    public String hello() {
//        return "hello spring security";
//    }
//
//
//    @GetMapping("index")
//    public Object index(Authentication authentication) {
//        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println(authentication1);
//        return authentication;
//    }
//
//}

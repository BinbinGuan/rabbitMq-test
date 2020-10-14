package com.example.aop;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: GuanBin
 * @date: Created in 下午4:55 2020/10/13
 */
@RestController
@RequestMapping("/aop")
public class AopController {

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String test() {
        int i = 1 / 0;
        System.out.println("haohao");
        return "success";
    }
}

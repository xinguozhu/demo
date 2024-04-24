package com.zxg.demoForOauth2.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Map;

@RestController
@Slf4j
public class testController {

//    @Autowired
//    private RedirctController redirctController;
    // 重定向
    private static final String REDIRECT_ ="redirect:";

    @GetMapping("/test111")
    public String test() {
        return "test";
    }

    @PostMapping("/test1")
    public String  test1(@RequestParam Map<String, String> parameters) {
        System.out.println(parameters);
        return "test1";

    }

//    @PostMapping("/test")
//    public void  test(@RequestParam Map<String, String> parameters) {
//        redirctController.redirect(parameters);
//
//    }

//    @PostMapping("/oauth/token")
//    public String test2(@RequestParam Map<String, String> parameters) {
//        return "/oauth/token";
//    }
}

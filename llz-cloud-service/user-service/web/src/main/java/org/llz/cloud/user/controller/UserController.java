package org.llz.cloud.user.controller;

import lombok.AllArgsConstructor;
import org.llz.auth.service.TokenManageService;
import org.llz.cloud.user.pojo.User;
import org.llz.cloud.user.service.UserService;
import org.llz.dynamic.feign.DynamicService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    DynamicService dynamicService;
    UserService userService;
    TokenManageService tokenManageService;

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }


    @PostMapping("/token")
    public String testToken(String hello) {
        return tokenManageService.createToken(hello, 3600 * 3L);
    }


}

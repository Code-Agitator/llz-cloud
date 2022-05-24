package org.llz.cloud.user.controller;

import lombok.AllArgsConstructor;
import org.llz.cloud.user.pojo.User;
import org.llz.cloud.user.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserController {
    UserService userService;

    @GetMapping("/hello")
    public List<User> hello() {
        return userService.list();
    }
}

package com.rev.demoSecurity.controller;

import com.rev.demoSecurity.Service.UserService;
import com.rev.demoSecurity.model.Users;
import com.rev.demoSecurity.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    UserService userService;
    @PostMapping("/register")
    public Users register(@RequestBody Users user) {
        return userService.register(user);
//        return user;
    }

    @PostMapping("/login")
    public String login(@RequestBody Users user) {
        return userService.verify(user);
//        return "ok";
    }
}

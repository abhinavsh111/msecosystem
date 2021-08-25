package com.tut.msecosystem.userservice.controller;

import com.tut.msecosystem.userservice.entity.User;
import com.tut.msecosystem.userservice.pojo.Response;
import com.tut.msecosystem.userservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    @Value("${env.name}")
    private String env;

    @PostMapping("/")
    public User saveUser(@RequestBody User user){
        log.info("inside saveUser");
        return userService.saveUser(user);
    }

    @GetMapping("{id}")
    public Response getUserWithDepartment(@PathVariable("id") Long userId){
        log.info("inside getUserWithDepartment");
        log.info("ENV name:: "+env);
        return userService.getUserWithDepartment(userId);
    }
}

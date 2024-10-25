package com.zhuwl.springcloud.controller;

import com.zhuwl.springcloud.entity.CommonResult;
import com.zhuwl.springcloud.entity.User;
import com.zhuwl.springcloud.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public CommonResult<User> getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }
}

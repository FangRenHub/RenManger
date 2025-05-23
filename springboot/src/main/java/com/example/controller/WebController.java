package com.example.controller;

import com.example.common.Result;
import com.example.service.AdminService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {

    @Resource
    AdminService adminService;

    @GetMapping("/")
    public Result hello() {
        return Result.success("hello world");
    }

}

package com.shicirili.fastservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class UserController {
    
    @RequestMapping("/")
    @ResponseBody
    public String index(@RequestParam(value = "uid") Integer uid) {
        String result = "hello " + uid;
        return result;
    }
}

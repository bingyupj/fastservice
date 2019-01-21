package com.shicirili.fastservice.controller;

import com.shicirili.fastservice.common.exception.GlobalException;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
public class IndexController {

    @RequestMapping(value = {"/","/index"})
    @ResponseBody
    public String index(@RequestParam(value = "t") Integer time) {
        System.out.println("请求来了，不响应:" + time);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentLength(3000);
        try {
            if (time==null || time ==0){
                time=60 ;
            }
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Hello World!" + new Date();
    }

    @RequestMapping("err")
    @ResponseBody
    public String err() throws Exception{
        throw  new Exception("错误");
    }

    @RequestMapping("myerr")
    @ResponseBody
    public String myerr() throws Exception{
        throw  new GlobalException(101,"自定义错误!");
    }

}

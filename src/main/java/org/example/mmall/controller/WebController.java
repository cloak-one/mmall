package org.example.mmall.controller;

import org.example.mmall.response.api.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebController {
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public CommonResult hello(){return CommonResult.success("hello");}
}

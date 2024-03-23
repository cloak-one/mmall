package org.example.mmall.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.example.mmall.response.api.CommonResult;
import org.example.mmall.service.UserService;
import org.example.mmall.request.LoginParam;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author why
 * @since 2024-02-03
 */
@Controller
@Tag(name = "UseController",description = "用户登录管理")
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Value(value = "${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Operation(summary = "登录以后返回token")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult login(@Validated @RequestBody LoginParam loginParam){

        return userService.login(loginParam);
    }

    @Operation(summary = "获取当前登录用户信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public CommonResult getUserInfo(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(this.tokenHead.length());;
        return userService.findUserByToken(token);
    }
}

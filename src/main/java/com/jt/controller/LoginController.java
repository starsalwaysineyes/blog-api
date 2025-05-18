package com.jt.controller;


import com.jt.common.annotation.LogAnnotation;
import com.jt.common.constant.ResultCode;
import com.jt.common.result.Result;
import com.jt.entity.User;
import com.jt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.subject.Subject;


@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    @LogAnnotation(module = "login",operation = "login")
    public Result register(@RequestBody User user){
        Result r = new Result();

        User temp = userService.getUserByAccount(user.getAccount());

        if(null !=temp){
            r.setResultCode(ResultCode.USER_HAS_EXISTED);
            return r;
        }

        String account =user.getAccount();
        String password =user.getPassword();

        Long userId = userService.saveUser(user);

        if(userId > 0){
            excuteLogin(account,password,r);
        }else{
            r.setResultCode(ResultCode.USER_Register_ERROR);
        }

        return r;


    }

    private void excuteLogin(String account,String password,Result r){
        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(account,password);






    }





}

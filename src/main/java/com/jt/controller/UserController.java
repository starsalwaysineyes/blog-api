package com.jt.controller;


import com.alibaba.fastjson.support.spring.annotation.*;
import com.jt.common.annotation.LogAnnotation;
import com.jt.common.constant.Base;
import com.jt.common.constant.ResultCode;
import com.jt.common.result.Result;
import com.jt.common.util.UserUtils;
import com.jt.entity.User;
import com.jt.service.UserService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @LogAnnotation(module = "用户",operation = "获取所有用户")
    @RequiresRoles(Base.ROLE_ADMIN)
    public Result listUsers() {
        List<User> users = userService.findAll();

        return Result.success(users);
    }

    @GetMapping("/{id}")
    @LogAnnotation
    @RequiresRoles(Base.ROLE_ADMIN)
    public Result getUserById(@PathVariable Long id){
        Result r = new Result();

        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        User user = userService.getUserById(id);

        if(null == user){
            r.setResultCode(ResultCode.USER_NOT_EXIST);
        }

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(user);

        return r;

    }


    @GetMapping("/currentUser")
    @FastJsonView(
            include = {@FastJsonFilter(clazz = User.class , props = {"id","account","nickname","avatar"})})
    @LogAnnotation(module = "用户",operation = "获取当前用户")
    public Result getCurrentUser(HttpServletRequest request){
        Result r = new Result();

        User currentUser = UserUtils.getCurrentUser();

        r.setResultCode(ResultCode.SUCCESS);
        r.setData(currentUser);

        return r;


    }


    @PostMapping("/create")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "用户",operation = "添加用户")
    public Result createUser(@Validated @RequestBody User user){
        Result r = new Result();

        Long userId = userService.saveUser(user);

        r.simple().put("userId",userId);

        return r;


    }


    @PostMapping("/update")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "用户",operation = "修改用户")
    public Result updateUser(@RequestBody User user){
        Result r = new Result();
        if(null == user.getId()){
            r.setResultCode(ResultCode.USER_NOT_EXIST);
            return r;
        }

        Long userId = userService.saveUser(user);

        r.simple().put("userId",userId);

        return r;


    }


    @GetMapping("/delete/{id}")
    @RequiresRoles(Base.ROLE_ADMIN)
    @LogAnnotation(module = "用户",operation = "删除用户")
    public Result deleteUser(@PathVariable Long id){
        Result r = new Result();

        if(null == id){
            r.setResultCode(ResultCode.PARAM_IS_BLANK);
            return r;
        }

        userService.deleteUserById(id);

        r.setResultCode(ResultCode.SUCCESS);

        return r;


    }







}

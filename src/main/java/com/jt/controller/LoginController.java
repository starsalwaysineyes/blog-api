package com.jt.controller;


import com.jt.common.annotation.LogAnnotation;
import com.jt.common.constant.Base;
import com.jt.common.constant.ResultCode;
import com.jt.common.result.Result;
import com.jt.entity.User;
import com.jt.oauth.OAuthSessionManager;
import com.jt.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.authc.UsernamePasswordToken;

import org.apache.shiro.subject.Subject;

import javax.servlet.http.HttpServletRequest;


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

        try{
            subject.login(token);

            User currentUser = userService.getUserByAccount(account);

            subject.getSession().setAttribute(Base.CURRENT_USER,currentUser);

            r.setResultCode(ResultCode.SUCCESS);

            r.simple().put(OAuthSessionManager.OAUTH_TOKEN, subject.getSession().getId());
        }catch (UnknownAccountException e){
            r.setResultCode(ResultCode.USER_NOT_EXIST);
        }catch (LockedAccountException e){
            r.setResultCode(ResultCode.USER_ACCOUNT_FORBIDDEN);
        }catch (AuthenticationException e){
            r.setResultCode(ResultCode.USER_LOGIN_ERROR);
        }catch (Exception e){
            r.setResultCode(ResultCode.ERROR);
        }


    }

    @RequestMapping(value = "/handleLogin")
    public Result handleLogin(HttpServletRequest request){
        String id = request.getHeader(OAuthSessionManager.OAUTH_TOKEN);

        System.out.println("超市登录...:"+id);

        return Result.error(ResultCode.SESSION_TIME_OUT);

    }

    @GetMapping("/logout")
    @LogAnnotation(module = "logout",operation = "logout")
    public Result logout(){
        Result r = new Result();
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        r.setResultCode(ResultCode.SUCCESS);
        return r;
    }





}

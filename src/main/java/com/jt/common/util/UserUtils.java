package com.jt.common.util;

import com.jt.entity.User;
import org.apache.shiro.SecurityUtils;


public class UserUtils {

    public static User getCurrentUser(){
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        return user;
    }



}

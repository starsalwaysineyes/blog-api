package com.jt.common.aspect;


import com.jt.common.annotation.LogAnnotation;
import com.jt.common.util.HttpContextUtils;
import com.jt.entity.Log;
import com.jt.service.LogService;
import java.lang.reflect.Method;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import com.jt.common.util.IPUtils;


@Aspect
@Component
public class LogAspect {

    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.jt.common.annotation.LogAnnotation)")
    public void logPointCut(){

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();

        Object result = point.proceed();

        long endTime = System.currentTimeMillis();

        long time = endTime - beginTime;

        saveLog(point,time);

        return result;

    }

    private void saveLog(ProceedingJoinPoint point,long time){
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        Log log = new Log();
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);


        if(log != null){
            log.setModule(logAnnotation.module());

            log.setOperation(logAnnotation.operation());
        }

        String className = point.getTarget().getClass().getName();
        String methodName = signature.getName();

        log.setMethod(className + "." +methodName + "()");

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();

        log.setIp(IPUtils.getIpAddr(request));

        //        //用户名
//        User user = UserUtils.getCurrentUser();
//
//        if (null != user) {
//            log.setUserId(user.getId());
//            log.setNickname(user.getNickname());
//        } else {
//            log.setUserId(-1L);
//            log.setNickname("获取用户信息为空");
//        }

        log.setTime(time);

        log.setCreateDate(new Date());

        logService.saveLog(log);



    }


}

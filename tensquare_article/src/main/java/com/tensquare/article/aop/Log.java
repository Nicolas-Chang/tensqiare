package com.tensquare.article.aop;


import com.mysql.cj.x.protobuf.MysqlxDatatypes;
import com.tensquare.entity.Result;
import com.tensquare.entity.StatusCode;
import com.tensquare.utils.IpUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*@Component
@Aspect   // 注明此类是切面类
public class Log {

    //定义切点
    @Pointcut("execution(public * com.tensquare.article.Controller..*.*(..))")
    public void webLog(){}


    @Around(value = "webLog()")//环绕方法
    public Result ArroundCup(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        String longString = proceedingJoinPoint.toLongString();// 连接点所在位置的全部信息
        System.out.println(longString);
        long l = System.currentTimeMillis();

        String clientIp = IpUtil.getClientIp(request);
        System.out.println(clientIp);
        proceedingJoinPoint.proceed();
        long l1 = System.currentTimeMillis();
        System.out.println("处理此条请求共用了"+ (l1 - l));

        return new Result();
    }

}*/

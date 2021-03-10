package com.example.demo.common;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author: xiaozhu13539
 * @Date: 2021/3/9
 */
@Aspect
@Slf4j
@Component
@Order(-1)
public class RpcInterceptor {
    //traceId
    public static final String MDC_TRADE_ID = "INNER_TRACE_ID";

    //环绕通知
    @Around("execution(public * com.example.demo.common..*.*(..)) || execution(public * com.example.demo.common..*.*(..))")
    public Object execute(ProceedingJoinPoint pjp)throws Throwable{
        Object[] args = pjp.getArgs();
        Object result = null;
        long executeTime = 0L;
        String methodSignature = pjp.getSignature().getName();
        Exception invokeException = null;
        String method = getMethod(pjp);
        try {
            long startTime = System.currentTimeMillis();
            result = pjp.proceed(args);
            long endTime = System.currentTimeMillis();
            executeTime = endTime - startTime;
        } catch (Exception e) {
            invokeException = e;
            //打印日志
            log.error("invoke method = {} exception", methodSignature, e);

        }finally {
            //TODO 处理指标统计
        }
        return result;
    }

    /**
     * 获取方法名
     * @param joinPoint
     * @return
     */
    private String getMethod(ProceedingJoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        MethodSignature ms = (MethodSignature) signature;
        Method m = ms.getMethod();
        return m.getName();
    }
}

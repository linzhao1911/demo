package com.example.demo.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import java.util.Arrays;

@Slf4j
@Aspect
@Component
public class LogAspect {
    
    @Around("execution(* com.example.demo.service.impl.*.*(..))")
    public Object logServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        String methodName = joinPoint.getSignature().getName();
        
        log.info("开始执行方法: {}, 参数: {}", methodName, 
                Arrays.toString(joinPoint.getArgs()));
        
        try {
            Object result = joinPoint.proceed();
            log.info("方法: {} 执行完成, 耗时: {}ms", methodName, 
                    System.currentTimeMillis() - startTime);
            return result;
        } catch (Exception e) {
            log.error("方法: {} 执行异常", methodName, e);
            throw e;
        }
    }
} 
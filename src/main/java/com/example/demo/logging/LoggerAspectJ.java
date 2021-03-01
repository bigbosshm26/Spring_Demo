package com.example.demo.logging;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspectJ {
//
//    private static final Logger LOGGER = LogManager.getLogger(LoggerAspectJ.class);
//
//    @Before("execution(* com.example.demo.controller.DemoController.*(..))")
//    public void logBefore(JoinPoint joinPoint) {
//        LOGGER.info("before method: " + joinPoint.getSignature().getName());
//    }
//    @After("execution(* com.example.demo.controller.DemoController.*(..))")
//    public void logAfter(JoinPoint joinPoint) {
//        LOGGER.info("after method: " + joinPoint.getSignature().getName());
//    }
//
//    @Around("execution(* com.example.demo.controller.DemoController.*(..))")
//    public void logAround(JoinPoint joinPoint) {
//        LOGGER.info("around method: "+ joinPoint.getSignature().getName());
//    }

}

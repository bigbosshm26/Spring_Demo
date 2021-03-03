package com.example.demo.logging;

import java.util.Arrays;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspectJ {

    private static final Logger LOGGER = LogManager.getLogger(LoggerAspectJ.class);

    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void restController() {
        // pointcut la tat ca class co annotation la restcontroller
    }

    @Pointcut("execution(* *.*(..))")
    protected void allMethod() {
        // pointcut la tat ca method
    }

    @Pointcut("execution(public * *(..))")
    protected void loggingPublicOperation() {
        // pointcut la tat ca cac public operation
    }

    @Pointcut("execution(* *.*(..))")
    protected void loggingAllOperation() {
        // pointcut la tat ca cac operation
    }

    @Before("restController() && allMethod()")
    public void logBefore(JoinPoint joinPoint) {

        LOGGER.debug("Entering in Method :  " + joinPoint.getSignature().getName());
        LOGGER.debug("Class Name :  " + joinPoint.getSignature().getDeclaringTypeName());
        LOGGER.debug("Arguments :  " + Arrays.toString(joinPoint.getArgs()));
        LOGGER.debug("Target class : " + joinPoint.getTarget().getClass().getName());
    }

    @After("restController() && allMethod()")
    public void logAfter(JoinPoint joinPoint){
        LOGGER.info("After execution of " + joinPoint.getSignature().getName());
    }
    //After -> All method within resource annotated with @RestController annotation
    // and return a  value
    @AfterReturning(pointcut = "restController() && allMethod()", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        String returnValue = this.getValue(result);
        LOGGER.debug("Method Return value : " + returnValue);
    }

    //After -> Any method within resource annotated with @RestController annotation
    // throws an exception ...Log it
    @AfterThrowing(pointcut = "restController() && allMethod()", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable exception) {
        LOGGER.error("An exception has been thrown in " + joinPoint.getSignature().getName() + " ()");
        LOGGER.error("Cause : " + exception.getCause());
    }

    //Around -> Any method within resource annotated with @RestController annotation
    @Around("restController() && allMethod()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();
        try {
            String className = joinPoint.getSignature().getDeclaringTypeName();
            String methodName = joinPoint.getSignature().getName();
            Object result = joinPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            LOGGER.debug("Method " + className + "." + methodName + " ()" + " execution time : "
                + elapsedTime + " ms");

            return result;
        } catch (IllegalArgumentException e) {
            LOGGER.error("Illegal argument " + Arrays.toString(joinPoint.getArgs()) + " in "
                + joinPoint.getSignature().getName() + "()");
            throw e;
        }
    }

    private String getValue(Object result) {
        String returnValue = null;
        if (null != result) {
            if (result.toString().endsWith("@" + Integer.toHexString(result.hashCode()))) {
                returnValue = ReflectionToStringBuilder.toString(result);
            } else {
                returnValue = result.toString();
            }
        }
        return returnValue;
    }

}

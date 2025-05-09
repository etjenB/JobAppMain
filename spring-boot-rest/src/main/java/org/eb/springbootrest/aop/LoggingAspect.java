package org.eb.springbootrest.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingAspect.class);

    //returnType className.methodName(args)
    //* - all return types, all classes, all methods
    //.. - all arguments
    @Before("execution(* org.eb.springbootrest.service.JobService.*(..))")
    public void logMethodCall(){
        LOGGER.info("Method called");
    }

    @Before("execution(* org.eb.springbootrest.service.JobService.getAllJobs(..)) || execution(* org.eb.springbootrest.service.JobService.addJob(..))")
    public void logMethodCallForGetAllJobsAndAddJobMethods(JoinPoint jp){
        LOGGER.info("Method {} called", jp.getSignature().getName());
    }

    @After("execution(* org.eb.springbootrest.service.JobService.getAllJobs(..)) || execution(* org.eb.springbootrest.service.JobService.addJob(..))")
    public void logMethodExecuted(JoinPoint jp){
        LOGGER.info("Method {} finished", jp.getSignature().getName());
    }

    @AfterThrowing("execution(* org.eb.springbootrest.service.JobService.getAllJobs(..)) || execution(* org.eb.springbootrest.service.JobService.addJob(..))")
    public void logMethodException(JoinPoint jp){
        LOGGER.info("In the method {} exception happened", jp.getSignature().getName());
    }

    @AfterReturning("execution(* org.eb.springbootrest.service.JobService.getAllJobs(..)) || execution(* org.eb.springbootrest.service.JobService.addJob(..))")
    public void logMethodSuccesfulExecution(JoinPoint jp){
        LOGGER.info("Method {} executed successfully", jp.getSignature().getName());
    }
}

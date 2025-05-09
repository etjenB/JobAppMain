package org.eb.springbootrest.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ValidationAspect {
    private static final Logger LOGGER = LoggerFactory.getLogger(ValidationAspect.class);

    @Around("execution(* org.eb.springbootrest.service.JobService.getJob(..)) && args(postId)")
    public Object validateAndUpdate(ProceedingJoinPoint proceedingJoinPoint, int postId) throws Throwable {
        if (postId < 0){
            LOGGER.info("Negative input {} for method {} updated to positive", postId, proceedingJoinPoint.getSignature().getName());
            postId = -postId;
        }
        Object object = proceedingJoinPoint.proceed(new Object[]{postId});
        return object;
    }
}

package com.tprest.app;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * Created by marwen
 */

@Aspect
@Component
public class RestControllerAspect {

    @Before("execution(public * com.khoubyari.example.api.rest.*Controller.*(..))")
    public void logBeforeRestCall(JoinPoint pjp) throws Throwable {
        System.out.println(":::::AOP Before REST call:::::" + pjp);
    }

}

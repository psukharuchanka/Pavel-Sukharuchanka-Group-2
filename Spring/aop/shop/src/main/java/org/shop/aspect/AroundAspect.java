package org.shop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;

@Aspect
public class AroundAspect {
    
    @Around("execution(* org.shop.api.OrderService.createOrder(..))")
    public void logBefore(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println(getClass().getSimpleName() + " before");
        joinPoint.proceed();
        System.out.println(getClass().getSimpleName() + " after");
    }
}

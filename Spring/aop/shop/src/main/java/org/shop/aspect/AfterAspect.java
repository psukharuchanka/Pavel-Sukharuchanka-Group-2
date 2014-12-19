package org.shop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AfterAspect {
    
    @After("execution(* org.shop.api.OrderService.createOrder(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(getClass().getSimpleName());
    }
}

package org.shop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class AnnotationAspect {
    
    @AfterReturning(value = "@annotation(org.shop.aspect.MyAnnotation)", returning = "retObject")
    public void logBefore(JoinPoint joinPoint, Object retObject) {
        System.out.println(getClass().getSimpleName() + " " + retObject);
    }
}

package org.shop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.Ordered;

@Aspect
public class BeforeAspect implements Ordered {
    
    @Before("execution(* org.shop.api.OrderService.createOrder(..))")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println(getClass().getSimpleName() + joinPoint.getSignature());
    }

    /* (non-Javadoc)
     * @see org.springframework.core.Ordered#getOrder()
     */
    @Override
    public int getOrder() {
        return 1000;
    }
}

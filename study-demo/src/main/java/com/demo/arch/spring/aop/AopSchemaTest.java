package com.demo.arch.spring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wu on 17/6/15.
 */
public class AopSchemaTest {

    @Test
    public void test() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring/aop/appcontext-aop-schema.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService.getUserName(1234));
    }

    private static class LogAspect {

        public void logBefore(ProceedingJoinPoint joinPoint) {
            System.out.println("logBefore start");
            System.out.println(joinPoint.getSignature().getName());
        }

        public void logAfter(ProceedingJoinPoint joinPoint) {
            System.out.println("logAfter start");
            System.out.println(joinPoint.getThis().getClass().toString());
        }
    }
}



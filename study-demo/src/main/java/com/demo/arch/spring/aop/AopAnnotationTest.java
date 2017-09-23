package com.demo.arch.spring.aop;

import com.demo.entity.Person;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by wu on 17/6/15.
 */
public class AopAnnotationTest {

    private UserService userService;

    @org.junit.Before
    public void before() throws Exception {
        ApplicationContext context = new ClassPathXmlApplicationContext("classpath:config/spring/aop/appcontext-aop-annotation.xml");
        userService = context.getBean("userService", UserService.class);
    }

    @Test
    public void test() throws Exception {
        System.out.println(userService.getUserName(1234));
    }

    @Test
    public void test1() throws Exception {
        System.out.println(userService.getUserAddress(1234));
    }

    @Test
    public void test3() throws Exception {
        System.out.println(userService.addUser(Person.getDefaultPerson()));
    }

    @Aspect
    private static class LogAspect {

        @Before("execution(* com.demo.arch.spring.aop.UserService.*(..))")
        public void logBefore(JoinPoint joinPoint) {
            System.out.println("logBefore start");
        }

        @After("execution(* com.demo.arch..*Service.*(..)) || execution(* com.demo..*.(..))")
        public void logAfter(JoinPoint joinPoint) {
            Object field = joinPoint.getArgs()[0];

            System.out.println("logAfter start");
        }

        /**
         * ProceedingJoinPoint is only supported for around advice
         * @param joinPoint
         */
//        @Around("execution(* com.demo.arch.spring.aop.UserService.*(..))")
        public void logAround(ProceedingJoinPoint joinPoint) throws Throwable {
            System.out.println("logAround start");
            try {
                joinPoint.proceed();
            } finally { //can not catch Throwable
                System.out.println("logAround proceeding");
            }
            System.out.println("logAround end");
        }

        @AfterReturning(value = "execution(* com.demo.arch.spring.aop.UserService.*(..))", returning = "retVal")
        public void logAfterReturning(JoinPoint joinPoint, Object retVal) {
            System.out.println(joinPoint.getArgs()[0]);
            System.out.println("logAfterReturning start, retVal = " + retVal);
        }

        @AfterThrowing("execution(* com.demo.arch.spring.aop.UserService.*(..))")
        public void logAfterThrowing(JoinPoint joinPoint) {
            System.out.println("logAfterThrowing start");
        }
    }

}



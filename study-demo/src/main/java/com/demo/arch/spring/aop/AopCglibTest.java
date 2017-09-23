package com.demo.arch.spring.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.junit.Test;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.lang.reflect.Method;

/**
 * Created by wu on 17/6/15.
 */
public class AopCglibTest {

    ApplicationContext context;

    @org.junit.Before
    public void before() throws Exception {
        context = new ClassPathXmlApplicationContext("classpath:config/spring/aop/appcontext-aop-cglib.xml");
    }

    @Test
    public void test() throws Exception {
        UserService userService = context.getBean("userServiceProxy", UserService.class); //必须使用代理的beanName
        System.out.println(userService.getUserName(1234));
    }

    @Test
    public void test1() throws Exception {
        UserService userService = context.getBean("userServiceProxy", UserService.class); //必须使用代理的beanName
        System.out.println(userService.getUserAddress(1234));
    }

    @Test
    public void test2() throws Exception {
        UserService userService = context.getBean("userServiceAdvisorProxy", UserService.class);
        System.out.println(userService.getUserName(1234));
    }

    private static class MethodBefore implements MethodBeforeAdvice {

        @Override
        public void before(Method method, Object[] args, Object target) throws Throwable {
            System.out.println("before start");
        }
    }

    private static class MethodAfterReturning implements AfterReturningAdvice {

        @Override
        public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
            System.out.println("afterReturning start");
        }
    }

    /**
     * can not use private static class ...
     */
    public static class MethodAfterThrowing implements ThrowsAdvice {
        public void afterThrowing(IllegalArgumentException e) throws Throwable {
            System.out.println("afterThrowing start");
        }
    }

    private static class MethodAround implements MethodInterceptor {

       @Override
       public Object invoke(MethodInvocation invocation) throws Throwable {
           System.out.println("afterAround start");
           invocation.proceed();
           System.out.println("afterAround end");
           return null;
       }
    }
}



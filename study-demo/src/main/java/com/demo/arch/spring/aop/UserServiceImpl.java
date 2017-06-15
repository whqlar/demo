package com.demo.arch.spring.aop;

/**
 * Created by wu on 17/6/15.
 */
public class UserServiceImpl implements UserService {

    @Override
    public String getUserName(int id) {
        System.out.println("getUserName " + id);
        return "jim";
    }

    @Override
    public String getUserAddress(int id) throws IllegalArgumentException {
        System.out.println("getUserAddress " + id);
        throw new IllegalArgumentException("参数错误");
    }
}

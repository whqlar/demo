package com.demo.arch.spring.aop;

import com.demo.entity.Person;

/**
 * Created by wu on 17/6/15.
 */
public interface UserService {

    String getUserName(int id);

    String getUserAddress(int id) throws IllegalArgumentException;

    int addUser(Person person);
}

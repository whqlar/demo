package com.demo.entity;

import com.google.common.collect.Lists;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Created by wu on 16/8/18.
 */
public class Student extends Person {
    private String school;

    private List<String> books;

    public Student() {
    }

    public Student(String name, int age, String school) {
        super(name, age);
        this.school = school;
    }

    public static Student getDefaultStudent() {
        Student student = new Student(Person.getDefaultPerson(), "SMU");
        student.setBooks(Lists.newArrayList("yuwen,shuxue"));
        return student;
    }



    public Student(Person person, String school) {
        BeanUtils.copyProperties(person, this);
        this.setSchool(school);
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public List<String> getBooks() {
        return books;
    }

    public void setBooks(List<String> books) {
        this.books = books;
    }
}

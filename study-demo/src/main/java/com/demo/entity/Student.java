package com.demo.entity;

import org.springframework.beans.BeanUtils;

/**
 * Created by wu on 16/8/18.
 */
public class Student extends Person {
    private String school;

    public Student() {
    }

    public Student(String name, int age, String school) {
        super(name, age);
        this.school = school;
    }

    public static Student getDefaultStudent() {
        Student student = (Student) Person.getDefaultPerson();
        student.setSchool("SMU");
        return student;
    }



    public Student(Person person) {
        BeanUtils.copyProperties(person, this);
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }
}

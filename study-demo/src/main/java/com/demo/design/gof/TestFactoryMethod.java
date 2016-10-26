package com.demo.design.gof;

/**
 * ��������ģʽ��Factory Method��
 * �������д�������Ĳ����������������?����һ����Ʒ�Ĺ����ӿڣ���ʵ�ʵĹ���ת�Ƶ���������ࡣ��������ϵͳ��չ������
 * ���ӿڵĳ��󻯴�����໥�����Ķ��󴴽��ṩ����õĳ���ģʽ��
 * 
 * @author Administrator
 * 
 */
public class TestFactoryMethod {

	public static void main(String[] args) {

		AnimalFactory af = new DogFactory();

		Animal1 a = af.getAnimal();

	}

}

abstract class Animal1 {
}

class Dog1 extends Animal1 {
}

class Cat1 extends Animal1 {
}

abstract class AnimalFactory {

	public abstract Animal1 getAnimal();

}

class DogFactory extends AnimalFactory {

	public Animal1 getAnimal() {

		System.out.println("Dog");

		return new Dog1();

	}

}

class CatFactory extends AnimalFactory {

	public Animal1 getAnimal() {

		System.out.println("Cat");

		return new Cat1();

	}

}
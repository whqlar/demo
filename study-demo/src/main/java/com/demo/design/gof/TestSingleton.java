package com.demo.design.gof;

/**
 * ����ģʽ��Singleton��
 * ����ȫ�ֱ���������ռ�ĳ�ͻ������˵��һ�ָ����˵�ȫ�ֱ���������һ����ֻ��һ��ʵ�����ṩһ������ȫ�ֵ�ķ�ʽ��������ı�֤��ʵ��Ĵ����ͷ���Լ��
 * ��ϵͳ��ֻ��һ��ʵ����˹��췽��Ӧ��Ϊ˽�� ����ʽ�������ʱֱ�Ӵ�����̬ʵ�� ����ʽ����һ����Ҫʱ�Ŵ���һ��ʵ����ônewInstance����Ҫ��ͬ��
 * ����ʽ������ʽҪ�ã�������Դ������Ҫ����ǲ���ͬ����
 * 
 * @author Administrator
 * 
 */
public class TestSingleton {

	public static void main(String[] args) {

	}

}

class ClassA { // ����ʽ

	private static ClassA i = new ClassA();

	public static ClassA newInstance() {

		return i;

	}

	private ClassA() {
	}

}

class ClassB { // ����ʽ

	private static ClassB i = null;

	public static synchronized ClassB newInstance() {

		if (i == null)
			i = new ClassB();

		return i;

	}

	private ClassB() {
	}

}
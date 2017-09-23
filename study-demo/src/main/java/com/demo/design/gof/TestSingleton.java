package com.demo.design.gof;

import org.junit.Test;

/**
 * 这里静态
 * @author Administrator
 */
public class TestSingleton {

	@Test
	public void testSingletonA() throws Exception {
		SingletonA1 singletonA1 = SingletonA1.INSTANCE;
		SingletonA2 singletonA2 = SingletonA2.getInstance();
	}

	@Test
	public void testSingletonB() throws Exception {
		SingletonB1 singletonB1 = SingletonB1.getInstance();
		SingletonB2 singletonB2 = SingletonB2.getInstance();
	}

	@Test
	public void testSingletonC() throws Exception {
		SingletonC singletonC = SingletonC.getInstance();
	}

	@Test
	public void testSingletonD() throws Exception {
		SingletonD singletonD = SingletonD.INSTANCE;
	}

}

class SingletonA1 {

	public final static SingletonA1 INSTANCE = new SingletonA1();

	private SingletonA1() {

	}
}

class SingletonA2 {

	private final static SingletonA2 INSTANCE = new SingletonA2();

	private SingletonA2() {

	}
	public static SingletonA2 getInstance() {
		return INSTANCE;
	}
}

class SingletonB1 {

	private static SingletonB1 instance = null;

	private SingletonB1() {

	}

	public static SingletonB1 getInstance() {
		if (instance == null) {
			synchronized(instance) {
				if (instance == null) {
					instance = new SingletonB1();
				}
			}
		}
		return instance;
	}
}

class SingletonB2 {

	private static SingletonB2 instance = null;

	private SingletonB2() {}

	public synchronized static SingletonB2 getInstance() {
		if (instance == null) {
			instance = new SingletonB2();
		}
		return instance;
	}
}

class SingletonC {

	private SingletonC() {}

	private static class SingletonCHolder {
		private static final SingletonC INSTANCE = new SingletonC();
	}

	public static SingletonC getInstance() {
		return SingletonCHolder.INSTANCE;
	}
}

enum SingletonD {

	INSTANCE;

	public void doSomeThring() {

	}
}
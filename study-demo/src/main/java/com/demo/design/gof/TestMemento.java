package com.demo.design.gof;

/**
 * 备忘录模式（Memento） 备忘录对象用来存储另一个对象的快照对象，保存其内部状态，使得可以随时恢复。
 * 备忘录角色：保存发起人对象的内部状态，保护内容不被除发起人对象之外的对象获取
 * 。窄接口：负责人对象和其他对象看到的接口，只允许把备忘录对象传给其他对象。宽接口：发起人能看到的接口，允许读取内部状态。
 * 发起人角色：创建并使用备忘录对象来保存其状态 负责人角色：负责保存备忘录对象。  白箱实现：备忘录类对其他类也可见，这样发起人的状态可能会存在安全问题。
 *  黑箱实现：把备忘录类作成发起人的内部类，对外提供一个标识接口。
 * 
 * @author Administrator
 * 
 */
public class TestMemento {

	public static void main(String[] args) {

		Originator ori = new Originator();

		Caretaker c = new Caretaker();

		ori.setState("State 1");

		IFMemento m = ori.createMemento();

		c.save(m);

		ori.setState("State 2");

		m = c.retrieve();

		ori.restore(m);

		System.out.println("Now State:" + ori.getState());

	}

}

class Originator {

	String state;

	public void setState(String s) {

		state = s;

		System.out.println("State change to: " + s);

	}

	public String getState() {

		return this.state;

	}

	public IFMemento createMemento() {

		return new Memento(state);

	}

	public void restore(IFMemento m) {

		Memento mt = (Memento) m;

		this.state = mt.getState();

	}

	private class Memento implements IFMemento {

		private String state;

		public Memento(String s) {

			this.state = s;

		}

		public String getState() {

			return this.state;

		}

	}

}

class Caretaker {

	private IFMemento m;

	public IFMemento retrieve() {

		return this.m;

	}

	public void save(IFMemento m) {

		this.m = m;

	}

}

interface IFMemento {

}

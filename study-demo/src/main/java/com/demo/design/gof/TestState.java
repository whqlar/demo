package com.demo.design.gof;

/**
 * ״̬ģʽ��State�� �ڶ����ڲ�״̬�ı�ʱ�ı�����Ϊ�������о��Ķ������Ϊ��װ�ڲ�ͬ��״̬�����С�
 * 
 * @author Administrator
 * 
 */
import static java.lang.System.*;

public class TestState {

	public static void main(String[] args) {

		BBSUser u = new BBSUser();

		u.setState(new GuestState());

		u.publish();

		u.setState(new NormalState());

		u.publish();

		u.setState(new BlockedState());

		u.publish();

		u.setState(new NewComerState());

		u.publish();

	}

}

class BBSUser {

	private State state;

	public void setState(State state) {

		this.state = state;

	}

	public void publish() {

		state.action();

	}

}

abstract class State {

	public abstract void action();

}

class GuestState extends State {

	public void action() {

		out.println("�����ο�״̬�����ȵ�¼");

	}

}

class NormalState extends State {

	public void action() {

		out.println("������״̬�����·���ɹ�");

	}

}

class BlockedState extends State {

	public void action() {

		out.println("���ڱ���״̬�����·���ʧ��");

	}

}

class NewComerState extends State {

	public void action() {

		out.println("�������֣�����ѧϰһ�£�3�������");

	}

}

class StateFactory {

	public static State createState(int i) {

		if (i == 1)
			return new GuestState();

		else
			return new NormalState();

	}

}

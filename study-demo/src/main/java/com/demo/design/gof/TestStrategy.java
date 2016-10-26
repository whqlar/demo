package com.demo.design.gof;

/**
 * ����ģʽ��Strategy��
 * ��ͬLayoutManager�;���Ĳ��ֹ������Ĺ�ϵ���ڳ���������ж��巽���������ڱ仯�Ĳ��ַ�װΪ�ӿڣ�ͨ��Strategy
 * ��װһЩ���㷨��ʹ֮�ܻ�����Bruce Zhang����Ĳ������ᵽ����ģʽ��ʵ��һ�֡�����ӿڡ��ı�̷���������ǡ����֡�
 * �ھ������������ʵ�֣��ͻ������ݲ�ͬ����Ҫѡ����Ӧ�ľ����࣬������������ж��ּ۸��㷨�� һ�ֲ���һ��ѡ�У����ϵͳ�������ǲ��仯��
 * 
 * @author Administrator
 * 
 */
public class TestStrategy {

	public static void main(String[] args) {

		Strategy s1 = new May1Strategy();

		Strategy s2 = new June1Strategy();

		Book b = new Book(100);

		b.setS(s2);

		System.out.println(b.getPrice());

	}

}

class Book {

	Strategy s;

	public Book(double price) {

		this.price = price;

	}

	private double price;

	public void setS(Strategy s) {

		this.s = s;

	}

	public double getPrice() {

		return price * s.getZheKou();

	}

}

interface Strategy {

	double getZheKou();

}

class May1Strategy implements Strategy {

	public double getZheKou() {

		return 0.8;

	}

}

class June1Strategy implements Strategy {

	public double getZheKou() {

		return 0.7;

	}

}

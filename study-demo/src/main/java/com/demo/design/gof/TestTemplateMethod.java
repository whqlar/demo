package com.demo.design.gof;

/**
 * ģ�巽����Template Method��
 * ׼��һ�������࣬�Ѳ���ȷ�����߼�������ĳЩ�����У���������󷽷�ʵ��ʣ����߼�����ͬ�������Щ�߼��в�ͬ��ʵ�֡�
 * �÷���������������������岢ʵ��һ��ģ�巽�������������������巽����Ƴٵ�����ʵ�֡�������Ըı丸��Ŀɱ䲿�֣������ܸı�ģ�巽������Ķ����߼���
 * 
 * @author Administrator
 * 
 */
public class TestTemplateMethod {

	public static void main(String[] args) {

		XiaoPin xp = new DaPuKe();

		xp.act();

	}

}

abstract class XiaoPin {

	public abstract void jiaoLiu();

	public abstract void xuShi();

	public abstract void gaoXiao();

	public abstract void shanQing();

	public final void act() {

		jiaoLiu();

		xuShi();

		gaoXiao();

		shanQing();

	}

}

class DaPuKe extends XiaoPin {

	public void jiaoLiu() {

		System.out.println("˳����");

	}

	public void xuShi() {

		System.out.println("�𳵳�Ϧ����ͬѧ����");

	}

	public void gaoXiao() {

		System.out.println("��Ƭ�����˿�");

	}

	public void shanQing() {

		System.out.println("��Ҿ�");

	}

}

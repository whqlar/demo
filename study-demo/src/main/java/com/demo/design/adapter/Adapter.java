package com.demo.design.adapter;

public class Adapter implements New{

	private Old old;

	public Adapter(Old old){
		this.old = old;
	}

	@Override
	public void newMethed(String s) {
		System.out.println(s);
		old.oldMethod(1);
	}

}

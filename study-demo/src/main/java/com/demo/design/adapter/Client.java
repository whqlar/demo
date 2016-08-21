package com.demo.design.adapter;

public class Client {
	public static void main(String[] args) {
		Adapter adapter = new Adapter(new Old() {

			@Override
			public void oldMethod(int i) {
				System.out.println(i);
			}
		});
		adapter.newMethed("s");
	}
}

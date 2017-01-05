package com.demo.design;

public class Enums {
	public static void main(String[] args) {
		DefaultHandler.RED.handle();
		DefaultHandler.BLUE.handle();
	}
	private static enum DefaultHandler implements Handler {
		RED {
			@Override
			public void handle() {
				System.out.println(this.toString());
			}
		},
		BLUE {
			@Override
			public void handle() {
				System.out.println(this.toString());
			}
		}
	}
	
	protected interface Handler {
		void handle();
	}
}

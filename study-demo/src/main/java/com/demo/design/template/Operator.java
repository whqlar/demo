/**
 * @description
 * @author haiqian.wu
 * @date Jun 8, 2015 11:00:49 AM
 */
package com.demo.design.template;

public enum Operator {
	ADD("加法"),
	SUB("减法"),
	MUL("乘法"),
	DIV("除法")
	;
	public String desc;

	private Operator(String desc) {
		this.desc = desc;
	}

	public String getDesc() {
		return desc;
	}

}

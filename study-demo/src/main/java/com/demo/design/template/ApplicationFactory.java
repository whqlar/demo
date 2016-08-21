/**
 * @description
 * @author haiqian.wu
 * @date Jun 8, 2015 11:09:18 AM
 */
package com.demo.design.template;

public class ApplicationFactory {
	public static Application createApplication(Operator operator) {
		switch (operator) {
		case ADD :
			return new AddApplication();
		case SUB :
			return new SubApplication();
		default :
			throw new UnsupportedOperationException("不支持的操作");
		}
	}
}

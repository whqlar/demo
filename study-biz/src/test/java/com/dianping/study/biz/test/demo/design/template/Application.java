/**
 * @description
 * @author haiqian.wu
 * @date Jun 8, 2015 10:34:56 AM
 */
package com.dianping.study.biz.test.demo.design.template;

public abstract class Application {
	public String compute() {
		return doGetOperator();
	}

	protected abstract String doGetOperator();

	/**
	 * @param add
	 * @return
	 */
	public static Application createApplication(Operator operator) {
		return ApplicationFactory.createApplication(operator);
	}

}

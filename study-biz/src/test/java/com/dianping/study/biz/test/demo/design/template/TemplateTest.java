/**
 * @description
 * @author haiqian.wu
 * @date Jun 8, 2015 10:49:00 AM
 */
package com.dianping.study.biz.test.demo.design.template;

import java.util.ArrayList;
import java.util.List;

public class TemplateTest {
	public static void main(String[] args) {
		List<Application> acs = new ArrayList<Application>();
		for(Application ac : acs) {
			System.out.println(ac.doGetOperator());
		}


//		Application application = Application.createApplication(Operator.SUB);
//		String result = application.compute();
//		System.out.println(result);

	}
}

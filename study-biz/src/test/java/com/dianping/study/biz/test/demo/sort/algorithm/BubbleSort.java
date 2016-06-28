/**
 * @description
 * @author haiqian.wu
 * @date Jul 2, 2015 3:07:05 PM
 */
package com.dianping.study.biz.test.demo.sort.algorithm;

public class BubbleSort extends SortAlgorithm{

	@Override
	public int[] doSort(int[] origin) {
		for(int i = origin.length - 1; i >=1; i--) {
			for(int j = 0; j < i ; j++) {
				if(origin[j] > origin[j + 1]) {
					origin[j + 1] = origin[j] + origin[j + 1];
					origin[j] = origin[j + 1] - origin[j];
					origin[j + 1] = origin[j + 1] - origin[j];
				}
			}
		}
		return origin;
	}

}

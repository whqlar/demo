/**
 * @description
 * @author haiqian.wu
 * @date Jul 2, 2015 3:07:22 PM
 */
package com.demo.util.sort.algorithm;

import java.util.Random;


public class SortTest {
	public static final int ARRAY_LENGTH = 15;

	public static void main(String[] args) {
		Random random = new Random();
		int[] array = new int[ARRAY_LENGTH];
		for(int i = 0; i < ARRAY_LENGTH; i++) {
			array[i] = random.nextInt(ARRAY_LENGTH);
		}
		SortAlgorithm sa = SortAlgorithmFactory.createSortAlgorithm(SortAlgorithmEnum.BubbleSort);
		sa.sort(array);
	}
}

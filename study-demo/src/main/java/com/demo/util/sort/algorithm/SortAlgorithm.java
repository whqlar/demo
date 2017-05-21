/**
 * @description
 * @author haiqian.wu
 * @date Jul 2, 2015 3:08:23 PM
 */
package com.demo.util.sort.algorithm;


public abstract class SortAlgorithm {
	protected String sortAlgorithm;
	public SortAlgorithm() {
		sortAlgorithm = this.getClass().getSimpleName();
	}

	public abstract int[] doSort(int[] origin);

	public void sort(int[] origin) {
		System.out.println(sortAlgorithm + " start");
		printArray(origin);

		long start = System.currentTimeMillis();
		int[] result = doSort(origin);
		long end = System.currentTimeMillis();

		printArray(result);

		System.out.println(sortAlgorithm + " take " + (end-start) + " millis");
	}

	public void printArray(int[] array) {
		for(int i = 0; i < array.length; i++) {
			System.out.print(array[i]);
			System.out.print(",");
		}
		System.out.println();
	}
}

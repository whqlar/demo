/**
 * @description
 * @author haiqian.wu
 * @date Jul 2, 2015 3:26:20 PM
 */
package com.demo.sort.algorithm;

public class SortAlgorithmFactory {
	public static SortAlgorithm createSortAlgorithm(SortAlgorithmEnum sortAlgorithmEnum) {
		switch (sortAlgorithmEnum) {
			case BubbleSort:
				return new BubbleSort();
			case HeapSort:
				return new HeapSort();
			case InsertionSort:
				return new InsertionSort();
			case SelectionSort:
				return new SelectionSort();
			case MergeSort:
				return new MergeSort();
			case QuickSort:
				return new QuickSort();
			default :
				throw new RuntimeException("no matching algorithm");
		}
	}
}

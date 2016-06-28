package com.dianping.study.biz.test.demo.concurrent;

import java.math.BigInteger;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiThread {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		long start = System.currentTimeMillis();
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		long num = 1000000000l;
		final int seg = 1000;
		long loc = 0; // Cannot refer to a non-final variable loc inside an
						// inner class defined in a different method
		BigInteger result = new BigInteger("0");
		// List<BigInteger> biList = new ArrayList<>();
		while (loc < num) {
			final long segStart = loc;
			Future<BigInteger> resultF = executorService.submit(new Callable<BigInteger>() {
						@Override
						public BigInteger call() throws Exception {
							BigInteger segResult = new BigInteger("0");
							for (long i = segStart; i < segStart + seg; i++) {
								segResult = segResult.add(new BigInteger(i + ""));
							}
							return segResult;
						}
					});
			loc += seg;
			Future<List<BigInteger>> segSumF = executorService.submit(new Converter(resultF.get()));
			for (BigInteger bi : segSumF.get()) {
				result = result.add(bi);
			}
			// result = result.add(resultF.get());
		}

		long stop = System.currentTimeMillis();
		System.out.println((stop - start) / 1000);
		System.out.println(result.toString());

		executorService.shutdownNow();
		// long start = System.currentTimeMillis();
		// BigInteger result = new BigInteger("0");
		// for(long i = 0; i < 100000000l; i ++) {
		// BigInteger s = new BigInteger(String.valueOf(i));
		// result = result.add(s);
		// }
		// long stop = System.currentTimeMillis();
		// System.out.println((stop - start) / 1000);
		// System.out.println(result.toString());
	}
	static class Converter implements Callable<List<BigInteger>> {
		BigInteger bi;

		public Converter(BigInteger bi) {
			this.bi = bi;
		}

		@Override
		public List<BigInteger> call() throws Exception {
			return null;
		}

	}

}
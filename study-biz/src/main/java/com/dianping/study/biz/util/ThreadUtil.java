package com.dianping.study.biz.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wu on 16/6/30.
 */
public class ThreadUtil {

    public static void executeFixedTimes(int nThreads, int times, Runnable runnable) {
        ExecutorService executorService = Executors.newFixedThreadPool(nThreads);
        for (int i = 1;i < times + 1; i++) {
            System.out.println("============ 第 " + i + " 次开始 ============");
            for (int j = 0; j < nThreads; j++) {
                executorService.execute(runnable);
            }
            System.out.println("============ 第 " + i + " 次结束 ============");
            try {
                Thread.sleep(3000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

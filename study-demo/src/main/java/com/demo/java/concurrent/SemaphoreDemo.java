package com.demo.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by wu on 16/8/2.
 */
public class SemaphoreDemo {

    private final static Semaphore semaphore = new Semaphore(2, true);

    public static void main(String args[]) throws Exception {

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0;i < 100; i ++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        doOperation();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });

        }
    }

    public static void doOperation() throws InterruptedException {
        try {
            semaphore.acquire();
            System.out.println("i am doing something " + Thread.currentThread().getName());
            Thread.sleep(2000);
            semaphore.release();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

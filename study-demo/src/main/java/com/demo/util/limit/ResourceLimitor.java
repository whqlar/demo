package com.demo.util.limit;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * 可以作为简单的限流最贱
 * 原理 先把请求放入队列中，超出队列长度直接返回，然后获取信号量，获取成功则从队列中弹出请求并调用方法，失败则不能调用方法
 * 缺点 1队列的长度不好控制 2超出队列长度，直接返回，调用方可能会重试，这样更加大了服务方的压力
 * Created by wu on 17/1/5.
 */
public class ResourceLimitor {
    public static void main(String[] args) {
        ResourceLimitor limitor = new ResourceLimitor();
        if(limitor.applyResource()){
            try{
                //do something
            }catch(Exception ex){

            }finally{
                limitor.releaseResource();
            }
        }
    }

    /**
     * wait threads to call the method
     */
    private LinkedBlockingDeque<Integer> waitQueue = new LinkedBlockingDeque<Integer>(20);
    /**
     * remaining thread count that can call the method
     */
    private Semaphore canExecuteCount = new Semaphore(10, true);
    /**
     * the time every thread can stay in wait queue
     */
    private static final int TRY_EXECUTE_TIMEOUT = 20;

    public boolean applyResource() {
        // check whether the thread can enter in the wait queue
        if (!waitQueue.offer(0)) {
            return false;
        }
        // after enter the wait queue,try to acquire a permit to call the methos with a timeout
        boolean canExecute = false;
        try {
            canExecute = canExecuteCount.tryAcquire(1, TRY_EXECUTE_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        // if this thread can't call the method,before return a result ,should poll it out of the wait queue
        // if this thread can call the method,then after call the method ,shoud call releaseResource() to poll it out of
        // the wait queue
        if (!canExecute) {
            waitQueue.poll();
        }
        return canExecute;

    }

    public void releaseResource() {
        canExecuteCount.release(1);
        waitQueue.poll();
    }

}
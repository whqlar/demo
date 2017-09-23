package com.demo.java.lang;

import org.junit.Test;
import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * Created by wu on 17/6/6.
 */
public class ShutDownHookTest {

    @Test
    public void test() throws Exception {
        addShutdownHook();
    }

    @Test
    public void testSignal() throws Exception {
        Signal signal = new Signal(getOSSignalType());
        Signal.handle(signal, new SystemSignalHandler());
    }

    private String getOSSignalType() {
        return System.getProperty("os.name").toLowerCase().startsWith("win") ? "INT" : "USR2";
    }

    private static class SystemSignalHandler implements SignalHandler {

        @Override
        public void handle(Signal signal) {
            addShutdownHook();
        }
    }

    public static void addShutdownHook() {
        Thread t = new Thread(new MyShutdownHook());
        Runtime.getRuntime().addShutdownHook(t);
    }

    private static class MyShutdownHook implements Runnable {
        @Override
        public void run() {
            System.out.println("shutdown hook start!!");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("shutdown hook end");
        }
    }

}

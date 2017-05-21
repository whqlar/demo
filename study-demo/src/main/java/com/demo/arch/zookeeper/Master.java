package com.demo.arch.zookeeper;

import org.apache.zookeeper.*;

import java.util.Random;

/**
 * Created by wu on 16/11/19.
 */
public class Master implements Watcher {
    ZooKeeper zooKeeper;

    static boolean isLeader = false;
    static String serverId = Integer.toHexString(new Random().nextInt());

    @Override
    public void process(WatchedEvent event) {
        System.out.println(event);
    }

    public void startZK() throws Exception{
        zooKeeper = new ZooKeeper("127.0.0.1:2181", 15000, this);
    }

    public static void main(String[] args) throws Exception {
        Master m = new Master();
        m.startZK();

        if (isLeader) {
            System.out.println("i'm the leader");
            Thread.sleep(600000);
        } else {
            System.out.println("someone else is the leader");
        }

    }
}

package com.demo.java.net;

import org.junit.Test;

import java.net.InetAddress;

/**
 * Created by wu on 17/6/6.
 */
public class InetAddressTest {

    @Test
    public void testInetAddress() throws Exception {
        InetAddress inetAddress = InetAddress.getByName("127.0.0.1");

        System.out.println("hostname = " + inetAddress.getHostName());

        System.out.println("canonicalHostName = " + inetAddress.getCanonicalHostName());

        System.out.println("hostAddress = " +  inetAddress.getHostAddress());

        System.out.println("isLoopbackAddress = " + inetAddress.isLoopbackAddress());

        System.out.println("inetAddress = " + inetAddress);
    }
}

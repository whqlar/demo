package com.demo.arch.netty;

import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by wu on 17/5/21.
 */
public class BIOTimeClient {

    public static void main(String[] args) {
        Socket socket = null;

        BufferedReader reader = null;
        PrintWriter writer = null;
        try {
            socket = new Socket("127.0.0.1", 8080);

            writer = new PrintWriter(socket.getOutputStream(), true);
            writer.println("hello, world");

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = null;

            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(socket);
        }
    }
}

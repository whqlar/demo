package com.demo.arch.netty;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * Created by wu on 17/5/22.
 */
public class AIOTimeServer {
    public static void main(String[] args) {
        AsynTimeServerHandler handler = new AsynTimeServerHandler(8080);
        new Thread(handler).start();
    }

    private static class AsynTimeServerHandler implements Runnable {

        int port;
        AsynchronousServerSocketChannel asynchronousServerSocketChannel;
        CountDownLatch latch;

        public AsynTimeServerHandler(int port) {
            this.port = port;
            try {
                asynchronousServerSocketChannel = AsynchronousServerSocketChannel.open();
                asynchronousServerSocketChannel.bind(new InetSocketAddress(port));
                System.out.println("time server start");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            latch = new CountDownLatch(1);
            doAccept();
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private void doAccept() {
            asynchronousServerSocketChannel.accept(this, new AcceptCompletionHandler());
        }

        private static class AcceptCompletionHandler
                implements CompletionHandler<AsynchronousSocketChannel, AsynTimeServerHandler> {

            @Override
            public void completed(AsynchronousSocketChannel channel, AsynTimeServerHandler handler) {
                handler.asynchronousServerSocketChannel.accept(handler, this);
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                channel.read(byteBuffer, byteBuffer, new ReadCompletionHandler(channel));
            }

            @Override
            public void failed(Throwable exc, AsynTimeServerHandler attachment) {
                exc.printStackTrace();
                attachment.latch.countDown();
            }

            private class ReadCompletionHandler implements CompletionHandler<Integer, ByteBuffer> {

                AsynchronousSocketChannel channel;

                public ReadCompletionHandler(AsynchronousSocketChannel result) {
                    this.channel = result;
                }

                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    attachment.flip();
                    byte[] body = new byte[attachment.remaining()];
                    attachment.get(body);
                    try {
                        System.out.println(new String(body, "UTF-8"));
                        doWrite();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }

                private void doWrite() {
                    String response = new Date().toString();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(response.getBytes().length);
                    byteBuffer.put(response.getBytes());
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    try {
                        channel.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }
}

package com.demo.arch.netty;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.util.concurrent.CountDownLatch;

/**
 * Created by wu on 17/5/22.
 */
public class AIOTimeClient {
    public static void main(String[] args) {
        AsynTimeClientHandler handler = new AsynTimeClientHandler(8080);
        new Thread(handler).start();
    }

    private static class AsynTimeClientHandler implements Runnable, CompletionHandler<Void, AsynTimeClientHandler>{

        int port;
        AsynchronousSocketChannel channel;
        CountDownLatch latch = new CountDownLatch(1);

        public AsynTimeClientHandler(int port) {
            this.port = port;
            try {
                channel = AsynchronousSocketChannel.open();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            latch = new CountDownLatch(1);
            channel.connect(new InetSocketAddress("127.0.0.1", port), this, this);
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void completed(Void result, AsynTimeClientHandler attachment) {
            String request = "hello, world";
            ByteBuffer byteBuffer = ByteBuffer.allocate(request.getBytes().length);
            byteBuffer.put(request.getBytes());
            byteBuffer.flip();

            channel.write(byteBuffer, byteBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer buffer) {
                    if (buffer.hasRemaining()) {
                        channel.write(buffer, buffer, this);
                    } else {
                        ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                        channel.read(readBuffer, readBuffer, new CompletionHandler<Integer, ByteBuffer>() {
                            @Override
                            public void completed(Integer result, ByteBuffer attachment) {
                                attachment.flip();
                                byte[] bytes = new byte[attachment.remaining()];
                                attachment.get(bytes);
                                try {
                                    System.out.println("get response from server" + new String(bytes, "UTF-8"));
                                } catch (UnsupportedEncodingException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void failed(Throwable exc, ByteBuffer attachment) {
                                try {
                                    channel.close();
                                    latch.countDown();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    exc.printStackTrace();
                    latch.countDown();
                }
            });
        }

        @Override
        public void failed(Throwable exc, AsynTimeClientHandler attachment) {

        }
    }
}

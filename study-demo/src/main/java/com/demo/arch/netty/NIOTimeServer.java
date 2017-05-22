package com.demo.arch.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by wu on 17/5/22.
 */
public class NIOTimeServer {
    public static void main(String[] args) {
        MultiplexerTimeServer server = new MultiplexerTimeServer(8080);

        new Thread(server).start();
    }

    private static class MultiplexerTimeServer implements Runnable {

        ServerSocketChannel serverSocketChannel;

        Selector selector;

        public MultiplexerTimeServer(int port) {
            try {
                serverSocketChannel = ServerSocketChannel.open();

                serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
                serverSocketChannel.configureBlocking(false);

                selector = Selector.open();
//                new Thread(new ReactorTack()).start();

                serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
                System.out.println("time server is start in port : " + port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select(1000);
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();
                    SelectionKey key = null;
                    while (iterator.hasNext()) {
                        key = iterator.next();
                        iterator.remove();

                        try {
                            handleInput(key);
                        } catch (Exception e) {
                            if (key != null) {
                                key.cancel();
                                if (key.channel() != null) {
                                    key.channel().close();
                                }
                            }
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private void handleInput(SelectionKey key) throws IOException {
            if (key.isValid()) {
                if (key.isAcceptable()) {
                    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                    SocketChannel sc = ssc.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                }

                if (key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                    int readBytes = sc.read(readBuffer);
                    if (readBytes > 0) {
                        readBuffer.flip();
                        byte[] bytes = new byte[readBuffer.remaining()];
                        readBuffer.get(bytes);
                        System.out.println("receive order : " + new String(bytes, "UTF-8"));
                        doWrite(sc);
                    } else if (readBytes < 0) {
                        key.cancel();
                        sc.close();
                    } else {

                    }
                }
            }
        }

        private void doWrite(SocketChannel sc) throws IOException {
            String date = new Date().toString();
            ByteBuffer writeBuffer = ByteBuffer.allocate(date.getBytes().length);
            writeBuffer.put(date.getBytes());
            writeBuffer.flip();
            sc.write(writeBuffer);
        }
    }
}

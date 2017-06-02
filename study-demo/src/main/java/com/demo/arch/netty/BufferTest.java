package com.demo.arch.netty;

import io.netty.buffer.*;
import org.junit.Test;

import java.nio.ByteBuffer;

/**
 * Created by wu on 17/5/29.
 */
public class BufferTest {

    /**
     * jdk原生ByteBuffer存在的问题
     * 1 长度固定
     * 2 需要手动flip()和rewind()
     * 3 功能有限
     */
    @Test
    public void testByteBuffer() throws Exception {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        String name = "jim";
        byteBuffer.put(name.getBytes());
        byteBuffer.flip();

        byte[] bytes = new byte[name.length()];
        byteBuffer.get(bytes);

        System.out.println(new String(bytes, "UTF-8"));
    }

    @Test
    public void testByteBuf() throws Exception {

    }

    @Test
    public void testCompositeByteBuf() throws Exception {
//        CompositeByteBuf compositeByteBuf = new CompositeByteBuf(ByteBufAllocator.DEFAULT, true, )
    }

    @Test
    public void testDirectBuffer() throws Exception {

    }

    @Test
    public void testPooledByteBuf() throws Exception {
        String content = "hello, world";
        int loop = 3000000;
        long start;
        long end;
        ByteBuf byteBuf;

        start = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            byteBuf = PooledByteBufAllocator.DEFAULT.directBuffer(1024);
            byteBuf.writeBytes(content.getBytes());
            byteBuf.release();
        }
        end = System.currentTimeMillis();
        System.out.println("pooled ByteBuf use " + (end - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            byteBuf = Unpooled.directBuffer(1024);
            byteBuf.writeBytes(content.getBytes());
            byteBuf.release();
        }
        end = System.currentTimeMillis();
        System.out.println("unpooled ByteBuf user " + (end - start));

    }
}

package com.demo.io;

import com.demo.AbstractDemoTestBase;
import org.junit.Test;

import java.io.*;

/**
 * Created by wu on 16/8/18.
 */
public class InputStreamTest extends AbstractDemoTestBase {



    @Test
    public void testObjectInputStream() {
        try {

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_PATH));
            print(in.readObject());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testPipedInputStream() {
        PipedInputStream in = new PipedInputStream();

    }

    @Test
    public void testFileInputStream() {
        FileInputStream in = null;
        try {
            in = new FileInputStream(FILE_PATH);
            print(in.available());

            print(in.read());

            print(in.markSupported());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 和ByteArrayInputStream类似，不同的是BufferedInputStream需要使用其他的输入流作为构造函数的参数
     */
    @Test
    public void testBufferedInputStream() {
        try {
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(FILE_PATH));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testByteArrayInputStream() {
        ByteArrayInputStream in = new ByteArrayInputStream(new byte[]{1, 2, 3, 4, 5, 6});
        print(in.available());
        print(in.read()); //1 读取一个字节
        print(in.markSupported()); //true 支持标记

        byte[] buffer = new byte[10];
        print(in.read(buffer, 0, 2));
        print(buffer);
        print(in.available());

        System.out.println(in.skip(1));
        print(in.available());

        in.reset(); //重置读取位置
        print(in.available());

        in.mark(1);
        print(in.read());

        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        print(in.available());
    }
}

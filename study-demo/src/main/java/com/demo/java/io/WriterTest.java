package com.demo.java.io;

import com.demo.AbstractDemoTestBase;
import org.junit.Test;

import java.io.PrintWriter;

/**
 * Created by wu on 16/8/21.
 */
public class WriterTest extends AbstractDemoTestBase {


    /**
     * 常用在servlet里
     */
    @Test
    public void testPrintWriter() {
        PrintWriter writer = new PrintWriter(System.out);

        writer.write("hhh");
        writer.flush();
    }
}

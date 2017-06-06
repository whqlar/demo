package com.demo.java.io;

import com.demo.AbstractDemoTestBase;
import com.demo.entity.Person;
import org.junit.Test;

import java.io.*;

/**
 * Created by wu on 16/8/18.
 */
public class OutputStreamTest extends AbstractDemoTestBase {

    @Test
    public void testPrintStream() {
        PrintStream out = new PrintStream(System.out);
        out.print(Person.getDefaultPerson());
    }

    @Test
    public void testObjectOutputStream() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
            out.writeObject(Person.getDefaultPerson());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFileOutputStream() {
        try {
            FileOutputStream out = new FileOutputStream(FILE_PATH);
            out.write(new byte[]{97, 98, 1, 99}); //1是不可见字符
//            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testByteArrayOutputStream() {
        ByteArrayOutputStream out = new ByteArrayOutputStream(10);
        print(out);

        out.write(10);
        print(out);

        print(out.size());

    }
}
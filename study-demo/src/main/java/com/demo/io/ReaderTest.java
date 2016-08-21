package com.demo.io;

import com.demo.AbstractDemoTestBase;
import org.junit.Test;

import java.io.*;

/**
 * Created by wu on 16/8/18.
 */
public class ReaderTest extends AbstractDemoTestBase {

    @Test
    public void testStringReader() {
        StringReader reader = new StringReader("hh");
        try {
            print(reader.read());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInputStreamReader() {
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(FILE_PATH));

            reader.read();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }
    }


    @Test
    public void testBufferedReader() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH), 4096);
            print(reader.read());

            print(reader.readLine());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {

        }

    }
}


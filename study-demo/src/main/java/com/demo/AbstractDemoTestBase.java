package com.demo;

import com.google.gson.Gson;

/**
 * Created by wu on 16/8/18.
 */
public abstract class AbstractDemoTestBase {

    public static final String FILE_PATH = "/Users/wu/workspace/study/study-demo/src/main/java/com/demo/a.txt";

    public static void print(Object object) {
        System.out.println(new Gson().toJson(object));
    }
}

package com;

import com.google.gson.Gson;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring/appcontext-*.xml" })
public abstract class AbstractTestBase {

    public static void print(Object object) {
        System.out.println(new Gson().toJson(object));
    }
}

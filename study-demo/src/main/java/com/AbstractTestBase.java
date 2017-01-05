package com;

import com.alibaba.fastjson.JSON;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/config/spring/appcontext-*.xml" })
public abstract class AbstractTestBase {

    public static void print(Object object) {
        System.out.println("=========================");
        System.out.println(JSON.toJSONString(object));
        System.out.println("=========================");
    }
}

package com.demo.redis;

import com.demo.AbstractDemoTestBase;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by wu on 16/8/27.
 */
public class RedisTest extends AbstractDemoTestBase {

    Jedis jedis;

    @Before
    public void before() {
        jedis = new Jedis("172.24.36.31", 6379);
    }

    @Test
    public void test() {
        String result = jedis.set("name", "jim");
        System.out.println(result);
        String name = jedis.get("name");
        System.out.println(name);

    }
}

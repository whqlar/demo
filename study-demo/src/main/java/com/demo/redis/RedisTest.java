package com.demo.redis;

import com.demo.AbstractDemoTestBase;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * Created by wu on 16/8/27.
 */
public class RedisTest extends AbstractDemoTestBase {

    @Test
    public void test() {
        Jedis jedis = new Jedis("172.24.36.31", 6379);
        String result = jedis.set("name", "jim");
        System.out.println(result);

        String name = jedis.get("name");
        System.out.println(name);
    }
}

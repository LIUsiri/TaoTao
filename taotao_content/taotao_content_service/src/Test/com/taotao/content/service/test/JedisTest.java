package com.taotao.content.service.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * class_name: JedisTest
 * package: com.taotao.content.service.test
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/29
 * creat_time: 19:46
 **/

public class JedisTest {


    @Test
    public void test(){
        Jedis jedis = new Jedis("120.77.211.233",6379);
        jedis.set("password", "123456");
        String password = jedis.get("password");
        System.out.println(password);
        jedis.close();
    }
}

package com.taotao.content.service;

import redis.clients.jedis.Jedis;

/**
 * class_name: Test
 * package: com.taotao.content.service
 * describe: TODO
 * @author: Liuxianglong
 * @date: 2018/1/27
 * creat_time: 19:23
 **/


public class Test {


    public static void main(String[] args) {
        Jedis jedis = new Jedis("120.77.211.233", 6379);
        String key = jedis.get("name");
        System.out.println(key);
        jedis.close();

    }
}

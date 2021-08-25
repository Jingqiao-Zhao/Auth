package com.example.login.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AuthControllerTest {

    @Autowired
    private RedisTemplate redisTemplate;




    @Test
    public void get() {
        System.out.println(redisTemplate.opsForList().range("admin",0,-1));
    }
}
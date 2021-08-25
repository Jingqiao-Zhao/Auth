package com.example.login.controller;
import com.example.login.common.JWTUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

@RestController
@RequestMapping("/user")
public class AuthController {

    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/auth")
    public ArrayList<String> Auth(@RequestParam("token") String auth) throws MalformedURLException {

        System.out.println(auth);
        Claims claims = JWTUtils.checkJWT(auth);
        String role = (String) claims.get("role");


//        for (String pri : (List<String>) redisTemplate.opsForList().range(role, 0, -1)) {
//
//            if(pri.equals(auth)){
//                return true;
//            }
//        }
        Set<String> privileges = redisTemplate.opsForZSet().range(role,0,-1);
        ArrayList<String> privilegeArrayList = new ArrayList<>();
        for (String privilege : privileges) {
            System.out.println(privilege);
            privilegeArrayList.add(privilege);
        }

//        System.out.println(privilegeArrayList.);
        return privilegeArrayList;
    }
}
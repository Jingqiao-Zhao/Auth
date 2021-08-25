package com.example.login.controller;
import com.example.login.common.JWTUtils;

import com.example.login.pojo.User;
import com.example.login.pojo.UserDTO;

import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Encoder;
import sun.security.provider.MD5;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserDTO userDTO;

    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping("/login")
    public String login(){
        return "index";
    }

    @PostMapping("/get_token")
    public String getToken(String name, String password,
                           HttpServletResponse resp,
                           Model model
                           ) throws NoSuchAlgorithmException, UnsupportedEncodingException {

        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update((password).getBytes("UTF-8"));
        byte b[] = md5.digest();

        int i;
        StringBuffer buf = new StringBuffer("");

        for (int offset = 0; offset < b.length; offset++) {
            i = b[offset];
            if (i < 0) {
                i += 256;
            }
            if (i < 16) {
                buf.append("0");
            }
            buf.append(Integer.toHexString(i));
        }
        password = buf.toString();

        User user = userService.getUser(name, password);

        if(user == null){
            resp.setStatus(403);
            return null;
        }
        userDTO.setUser(user);
        String role = userService.getRoleByUserId(user.getUserId());
        userDTO.setRole(role);
        String token = JWTUtils.geneJsonWebToken(userDTO);
        model.addAttribute("privileges",redisTemplate.opsForZSet().range(role,0,-1));
        Cookie cookie = new Cookie("token",token);
        resp.addCookie(cookie);
        model.addAttribute("token", token);
//        System.out.println("cookie设置完成");
//        System.out.println("cookie是" + cookie.getValue());

        return "dashboard";

    }

}

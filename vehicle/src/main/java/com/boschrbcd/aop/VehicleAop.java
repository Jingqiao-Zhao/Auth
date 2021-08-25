package com.boschrbcd.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;

@Aspect
@Component
public class VehicleAop {

    @Autowired
    private RestTemplate restTemplate;

    @Pointcut("execution(public * com.boschrbcd.controller..*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut() && args(token,model)")
    public void listAspect(String token, Model model) {
        System.out.println("list方法被实施");
        HashMap<String, String> authMap = new HashMap<>();
        authMap.put("auth", token);
        ArrayList<String> privileges = restTemplate.getForObject("http://bcsc.gateway.com:8888/user/auth?token={auth}", ArrayList.class, authMap);
        System.out.println(privileges);
        model.addAttribute("privileges", privileges);
    }

    @Before("pointcut() && args(token,model,location)")
    public void countAspect(String token, Model model, String location) {
        System.out.println("count方法被实施");
        HashMap<String, String> authMap = new HashMap<>();
        authMap.put("auth", token);
        ArrayList<String> privileges = restTemplate.getForObject("http://bcsc.gateway.com:8888/user/auth?token={auth}", ArrayList.class, authMap);
        System.out.println(privileges);
        model.addAttribute("privileges", privileges);
    }
}

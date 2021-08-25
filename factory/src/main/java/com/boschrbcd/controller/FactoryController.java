package com.boschrbcd.controller;

import com.boschrbcd.pojo.Factory;
import com.boschrbcd.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
@RequestMapping("api/v1/factory")
public class FactoryController {

    @Autowired
    private FactoryService factoryService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("list")
    public String getAll(@RequestParam("token") String token, Model model) {
        ArrayList<String> privileges = (ArrayList<String>) model.getAttribute("privileges");
        if(privileges.contains("factory_management")) {
            List<Factory> factories = factoryService.getAll();
            model.addAttribute("factories", factories);
            model.addAttribute("privileges", privileges);
            model.addAttribute("token", token);
            return "/factory/list";
        } else {
            return "/error/403";
        }
    }

    @RequestMapping("count_factory")
    @ResponseBody
    public ArrayList countFactory(@RequestParam("token") String token, Model model,
                                  @RequestParam(required=false, value="loc", defaultValue= "wuxi") String loc) {
        ArrayList<String> privileges = (ArrayList<String>) model.getAttribute("privileges");
        ArrayList<Object> privilegesNumberList = new ArrayList<Object>();
        for (String privilege : privileges) {
            privilegesNumberList.add(privilege);
        }
        if(privileges.contains("factory_management")) {
            privilegesNumberList.add(factoryService.countFactory(loc));
        } else {
            privilegesNumberList.add(-1);
        }
        System.out.println(privilegesNumberList);
        return privilegesNumberList;
    }
}


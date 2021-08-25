package com.boschrbcd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;


@Controller
@RequestMapping("api/v1/data/")
public class DataAnalysisController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("analysis")
    public String data_analysis(@RequestParam("token") String token, Model model) {
        ArrayList<String> privileges = (ArrayList<String>) model.getAttribute("privileges");
        if(privileges.contains("data_analysis")) {
            model.addAttribute("privileges", privileges);
            model.addAttribute("token", token);
            return "/data/data";
        } else {
            return "/error/403";
        }
    }

    @PostMapping("analysis/vehicle")
    public String analyzeVehicle(@RequestParam("token") String token, String location, Model model) {
        ArrayList<Object> privilegesNumberList = restTemplate.getForObject("http://bcsc.gateway.com:8888/api/v1/vehicle/count_vehicle?loc=" + location + "&token=" + token, ArrayList.class);
        ArrayList<String> privileges = new ArrayList<String>();
        for (int i = 0; i < privilegesNumberList.size()-1; i++) {
            privileges.add((String) privilegesNumberList.get(i));
        }
        int number = (Integer) privilegesNumberList.get(privilegesNumberList.size()-1);
        System.out.println(privileges);
        System.out.println(number);
        if(number==-1) {
            return "/error/403";
        }
        model.addAttribute("privileges", privileges);
        model.addAttribute("location",location);
        model.addAttribute("number",number);
        model.addAttribute("token", token);
        System.out.println(location);
        return "/data/vehicle";
    }

    @PostMapping("analysis/factory")
    public String analyzeFactory(@RequestParam("token") String token, String location, Model model) {
        ArrayList<Object> privilegesNumberList = restTemplate.getForObject("http://bcsc.gateway.com:8888/api/v1/factory/count_factory?loc=" + location + "&token=" + token, ArrayList.class);
        ArrayList<String> privileges = new ArrayList<String>();
        for (int i = 0; i < privilegesNumberList.size()-1; i++) {
            privileges.add((String) privilegesNumberList.get(i));
        }
        int number = (Integer) privilegesNumberList.get(privilegesNumberList.size()-1);
        System.out.println(privileges);
        System.out.println(number);
        if(number==-1) {
            return "/error/403";
        }
        model.addAttribute("privileges", privileges);
        model.addAttribute("location",location);
        model.addAttribute("number",number);
        model.addAttribute("token", token);
        System.out.println(location);
        return "/data/factory";
    }
}


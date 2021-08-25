package com.boschrbcd.controller;

import com.boschrbcd.pojo.Vehicle;
import com.boschrbcd.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("api/v1/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("list")
    public String getAll(@RequestParam("token") String token, Model model) {
        ArrayList<String> privileges = (ArrayList<String>) model.getAttribute("privileges");
        if(privileges.contains("vehicle_management")) {
            List<Vehicle> vehicles = vehicleService.getAll();
            model.addAttribute("vehicles", vehicles);
            model.addAttribute("privileges", privileges);
            model.addAttribute("token", token);
            return "/vehicle/list";
        } else {
            return "/error/403";
        }
    }

    @RequestMapping("count_vehicle")
    @ResponseBody
    public ArrayList countVehicle(@RequestParam("token") String token, Model model,
                                  @RequestParam(required=false, value="loc", defaultValue= "wuxi") String loc) {
        ArrayList<String> privileges = (ArrayList<String>) model.getAttribute("privileges");
        ArrayList<Object> privilegesNumberList = new ArrayList<Object>();
        for (String privilege : privileges) {
            privilegesNumberList.add(privilege);
        }
        if(privileges.contains("vehicle_management")) {
            privilegesNumberList.add(vehicleService.countVehicle(loc));
        } else {
            privilegesNumberList.add(-1);
        }
        System.out.println(privilegesNumberList);
        return privilegesNumberList;
    }
}

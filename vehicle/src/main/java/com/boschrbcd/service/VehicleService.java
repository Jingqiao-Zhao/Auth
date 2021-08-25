package com.boschrbcd.service;

import com.boschrbcd.pojo.Vehicle;

import java.util.List;

public interface VehicleService {

    List<Vehicle> getAll();

    int countVehicle(String loc);
}

package com.boschrbcd.service.impl;

import com.boschrbcd.mapper.VehicleMapper;
import com.boschrbcd.pojo.Vehicle;
import com.boschrbcd.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleMapper vehicleMapper;

    @Override
    public List<Vehicle> getAll() {
        return vehicleMapper.getAll();
    }

    @Override
    public int countVehicle(String loc) {
        return vehicleMapper.countVehicle(loc);
    }
}

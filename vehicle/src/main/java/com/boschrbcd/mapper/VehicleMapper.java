package com.boschrbcd.mapper;

import com.boschrbcd.pojo.Vehicle;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface VehicleMapper {

    List<Vehicle> getAll();

    int countVehicle(@Param("loc") String loc);
}

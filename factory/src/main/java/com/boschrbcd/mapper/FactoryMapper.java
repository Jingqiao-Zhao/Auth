package com.boschrbcd.mapper;

import com.boschrbcd.pojo.Factory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface FactoryMapper {

    List<Factory> getAll();

    int countFactory(@Param("loc") String loc);
}

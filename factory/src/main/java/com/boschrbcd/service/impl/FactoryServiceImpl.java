package com.boschrbcd.service.impl;

import com.boschrbcd.mapper.FactoryMapper;
import com.boschrbcd.pojo.Factory;
import com.boschrbcd.service.FactoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FactoryServiceImpl implements FactoryService {

    @Autowired
    private FactoryMapper factoryMapper;

    @Override
    public List<Factory> getAll() {
        return factoryMapper.getAll();
    }

    @Override
    public int countFactory(String loc) {
        return factoryMapper.countFactory(loc);
    }
}

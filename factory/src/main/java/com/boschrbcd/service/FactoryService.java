package com.boschrbcd.service;

import com.boschrbcd.pojo.Factory;

import java.util.List;

public interface FactoryService {

    List<Factory> getAll();

    int countFactory(String loc);
}

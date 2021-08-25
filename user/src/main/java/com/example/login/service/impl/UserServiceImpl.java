package com.example.login.service.impl;


import com.example.login.dao.UserDao;
import com.example.login.pojo.User;
import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class UserServiceImpl implements UserService {

    @Override
    public List<Map<String,Object>> getPrivilegeByRole(String role) {
        return userDao.getPrivilegeByRole(role);
    }

    @Override
    public List<String> getAllRole() {
        return userDao.getAllRole();
    }

    @Autowired
    private UserDao userDao;

    @Override
    public String getRoleByUserId(Integer userId) {
        return userDao.getRoleByUserId(userId);
    }

    @Override
    public User getUser(String username, String password) {
        return userDao.getUser(username,password);
    }
}

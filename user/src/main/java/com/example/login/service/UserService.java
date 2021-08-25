package com.example.login.service;
import com.example.login.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

public interface UserService {

    User getUser(String username, String password);

    String getRoleByUserId(Integer userId);

    List<Map<String,Object>> getPrivilegeByRole(String role);

    List<String> getAllRole();
}

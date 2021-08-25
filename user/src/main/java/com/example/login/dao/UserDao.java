package com.example.login.dao;


import com.example.login.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface UserDao {

    User getUser(@Param("name") String name, @Param("password") String password);

    String getRoleByUserId(@Param("userId") Integer userId);

    List<Map<String,Object>> getPrivilegeByRole(@Param("role") String role);

    List<String> getAllRole();
}

package com.example.login.pojo;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserDTO {

    @Autowired
    private User user;

    private String role;

}

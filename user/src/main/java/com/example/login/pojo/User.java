package com.example.login.pojo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {

    private Integer userId;

    private String name;

    private String password;

    private String email;


}

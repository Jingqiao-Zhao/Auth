package com.example.login.scheduled;



import com.example.login.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Component
@EnableScheduling
public class SetPrivilegeToRedis {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private UserService userService;

    @Scheduled(cron = "* 1 * * * ?")
    public void setPrivilegeToRdeis(){

        List<String> allRoleList = userService.getAllRole();
        System.out.println("执行静态定时任务时间: " + LocalDateTime.now());
        for (String role : allRoleList) {
            List<Map<String,Object>> privilegeByRole = userService.getPrivilegeByRole(role);


            for (Map<String, Object> roleMap : privilegeByRole) {
                String privilege = roleMap.get("privilege").toString();
                double id = Double.parseDouble(roleMap.get("privilegeId").toString());
                redisTemplate.opsForZSet().add(role,privilege,id);
            }

        }

    }
}
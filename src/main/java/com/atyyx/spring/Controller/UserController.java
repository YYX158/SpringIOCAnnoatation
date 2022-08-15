package com.atyyx.spring.Controller;

import com.atyyx.spring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller(value = "userController") // 自定义bean的id
public class UserController
{
    @Autowired
    @Qualifier("userServiceImpl") // 指定id="userServiceImpl"的bean来给其自动装配
    private UserService userService;

    public void saveUser()
    {
        userService.saveUser();
    }
}

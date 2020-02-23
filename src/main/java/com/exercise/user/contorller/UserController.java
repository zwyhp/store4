package com.exercise.user.contorller;

import com.exercise.user.domain.User;
import com.exercise.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/save")
    public String saveUser(@RequestParam(value = "username") String username ,
                           @RequestParam(value = "password") String password
                           ){
        User  user = new User(username,password);
        userService.saveUser(user);
        return "succeed";
    }
    @PostMapping("/save1")
    public String saveUser1(@RequestBody User user){
        System.out.println(user);
        return "succeed";
    }

}

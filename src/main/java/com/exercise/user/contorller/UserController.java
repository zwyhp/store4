package com.exercise.user.contorller;


import com.exercise.domain.PageDomain;
import com.exercise.util.ResponseUtil;
import com.exercise.user.domain.User;
import com.exercise.user.service.IuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/admin")
public class UserController {
    @Autowired
    private IuserService userService;

    @PostMapping("/user")
    public Object user(@RequestBody User user){
        int i = userService.addObject(user);
        return i>0? ResponseUtil.ok():ResponseUtil.fail();
    }

    @DeleteMapping("/user/{id}")
    public Object user(@PathVariable int id){
        userService.deleteObjectById(id);
        return "success";
    }
    @GetMapping("/user")
    public Object user(){
        PageDomain pageDomain = userService.pagingfindAll(1, 10);
        return pageDomain;
    }

    @GetMapping("/conditions")
    public Object user1(@RequestParam(value = "username") String username,
                        @RequestParam(value = "roleid",defaultValue = "1") int roleid){
        List list = userService.conditionsQuery(username,roleid);
        return list;
    }
}

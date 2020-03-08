package com.exercise.user.contorller;

import com.exercise.util.verify.VerifyUpdate;
import com.exercise.domain.PageDomain;
import com.exercise.util.ResponseUtil;
import com.exercise.user.domain.User;
import com.exercise.user.service.IuserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserController {

    @Autowired
    private IuserService userService;

    @PostMapping("/user")
    public Object user(@RequestBody @Validated User user){
        int i = userService.addObject(user);
        return i>0? ResponseUtil.ok():ResponseUtil.fail();
    }

    @PutMapping("/user")
    public Object updeteUser(@RequestBody @Validated(VerifyUpdate.class) User user){
        userService.updateObjectById(user);
        return ResponseUtil.ok();
    }

    @DeleteMapping("/user/{id}")
    public Object user(@PathVariable int id){
        userService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/users")
    public Object user(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                       @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        if (pagenum <= 0 ){
            return ResponseUtil.badArgument("页码必须为整数");
        }
        PageDomain pageDomain = userService.pagingfindAll(pagenum, pagesize);
        return ResponseUtil.ok(pageDomain);
    }

    /**
     * 根据角色id或者name查询
     * @param roleId
     * @return
     */
    @GetMapping("/conditions")
    public Object user1(@RequestParam(value = "roleid",defaultValue = "1") int roleId){
        List list = userService.conditionsQuery(roleId);
        return list;
    }


}

package com.exercise.user.contorller;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.NotLog;
import com.exercise.user.domain.User;
import com.exercise.user.service.IuserService;
import com.exercise.util.ResponseUtil;
import com.exercise.util.verify.VerifyUpdate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
public class UserController {

    @Autowired
    private IuserService userService;

    @NotLog
    @GetMapping("/login")
    public Object user(){
        return ResponseUtil.unlogin();
    }

    @PostMapping("/doLogin")
    public Object user(@RequestParam(value = "username") String username,@RequestParam("password") String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password,false);
        Subject currentUser = SecurityUtils.getSubject();
        //调用realm的认证方法
        currentUser.login(token);
        return ResponseUtil.ok("登录成功");
    }

    @NotLog
    @GetMapping("/doLogout")
    public Object logout(){
        //使用权限管理工具进行用户的退出，跳出登录，给出提示信息
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        return ResponseUtil.ok("注销成功");
    }


    @PostMapping("/doRegister")
    public Object user(@RequestBody @Validated User user){
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        /*
         * MD5加密：
         * 使用SimpleHash类对原始密码进行加密。
         * 第一个参数代表使用MD5方式加密
         * 第二个参数为原始密码
         * 第三个参数为盐值，即用户名
         * 第四个参数为加密次数
         * 最后用toHex()方法将加密后的密码转成String
         * */
        String newPs = new SimpleHash("MD5",user.getPassword(), salt,1024).toHex();
        user.setPassword(newPs);
        int i = userService.addObject(user);
        return i>0? ResponseUtil.ok():ResponseUtil.fail();
    }

    @RequiresPermissions("user:user")
    @PutMapping("/admin/user")
    public Object updeteUser(@RequestBody @Validated(VerifyUpdate.class) User user){
        ByteSource salt = ByteSource.Util.bytes(user.getUsername());
        String newPs = new SimpleHash(user.getPassword(), salt).toHex();
        user.setPassword(newPs);
        userService.updateObjectById(user);
        return ResponseUtil.ok();
    }

    @RequiresPermissions("admin:user")
    @DeleteMapping("/admin/user/{id}")
    public Object user(@PathVariable int id){
        userService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @NotLog
    @RequiresPermissions("admin:user")
    @GetMapping("/admin/users")
    public Object user(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                       @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        if (pagenum <= 0 ){
            return ResponseUtil.badArgument("页码必须为整数");
        }
        PageDomain pageDomain = userService.pagingFindAll(pagenum, pagesize);
        return ResponseUtil.ok(pageDomain);
    }

    /**
     * 根据角色id查询
     * @param roleId
     * @return
     */
    @NotLog
    @RequiresPermissions("admin:user")
    @GetMapping("/admin/conditions")
    public Object user1(@NotNull int roleId){
        return userService.conditionsQuery(roleId);

    }
    @PutMapping("/admin/allotRole")
    @RequiresPermissions("admin:user")
    public Object allotRole(@RequestParam(value = "id" )  int id,
                             @RequestParam(value = "rid")  int rid){
        userService.allotRole(id,rid);
        return ResponseUtil.ok();
    }

}

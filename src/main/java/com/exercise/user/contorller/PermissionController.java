package com.exercise.user.contorller;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.NotLog;
import com.exercise.user.domain.Permission;
import com.exercise.user.service.IpermissionService;
import com.exercise.util.ResponseUtil;
import com.exercise.util.verify.VerifyUpdate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class PermissionController {
    
    @Autowired
    private IpermissionService permissionService;

    @PostMapping("/permission")
    @RequiresPermissions("admin:permission")
    public Object permission(@RequestBody @Validated Permission permission){
        int i = permissionService.addObject(permission);
        return i>0? ResponseUtil.ok():ResponseUtil.fail();
    }


    @DeleteMapping("/permission/{id}")
    @RequiresPermissions("admin:permission")
    public Object permission(@PathVariable int id){
        permissionService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @PutMapping("/permission")
    @RequiresPermissions("admin:permission")
    public Object updetepermission(@RequestBody @Validated(VerifyUpdate.class) Permission permission){
        permissionService.updateObjectById(permission);
        return ResponseUtil.ok();
    }

    @NotLog
    @GetMapping("/permissions")
    @RequiresPermissions("admin:permission")
    public Object permission(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                       @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        if (pagenum <= 0 ){
            return ResponseUtil.badArgument("页码必须为整数");
        }
        PageDomain pageDomain = permissionService.pagingFindAll(pagenum, pagesize);
        return ResponseUtil.ok(pageDomain);
    }
    /**
     * 给权限绑定角色
     * @param id 权限id
     * @param rid 角色id
     * @return
     */
    @PutMapping("/bindRole")
    @RequiresPermissions("admin:permission")
    public Object bindRole(@RequestParam(value = "id" )  int id,
                           @RequestParam(value = "rid")  int rid){
        permissionService.bindRole(id,rid);
        return ResponseUtil.ok();
    }

}

package com.exercise.user.contorller;

import com.exercise.domain.PageDomain;
import com.exercise.user.domain.Permission;
import com.exercise.user.service.IpermissionService;
import com.exercise.util.ResponseUtil;
import com.exercise.util.verify.VerifyUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class PermissionController {
    
    @Autowired
    private IpermissionService permissionService;

    @PostMapping("/permission")
    public Object permission(@RequestBody @Validated Permission permission){
        int i = permissionService.addObject(permission);
        return i>0? ResponseUtil.ok():ResponseUtil.fail();
    }

    @PutMapping("/permission")
    public Object updetepermission(@RequestBody @Validated(VerifyUpdate.class) Permission permission){
        permissionService.updateObjectById(permission);
        return ResponseUtil.ok();
    }

    @DeleteMapping("/permission/{id}")
    public Object permission(@PathVariable int id){
        permissionService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/permissions")
    public Object permission(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                       @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        if (pagenum <= 0 ){
            return ResponseUtil.badArgument("页码必须为整数");
        }
        PageDomain pageDomain = permissionService.pagingfindAll(pagenum, pagesize);
        return ResponseUtil.ok(pageDomain);
    }


}

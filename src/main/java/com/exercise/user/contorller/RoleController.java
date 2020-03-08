package com.exercise.user.contorller;

import com.exercise.domain.PageDomain;
import com.exercise.user.domain.Role;
import com.exercise.user.service.IroleService;
import com.exercise.util.ResponseUtil;
import com.exercise.util.verify.VerifyUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/admin")
public class RoleController {

    @Autowired
    private IroleService roleService;

    @PostMapping("/role")
    public Object role(@RequestBody @Validated Role role){
        int i = roleService.addObject(role);
        return i>0? ResponseUtil.ok():ResponseUtil.fail();
    }

    @PutMapping("/role")
    public Object updeterole(@RequestBody @Validated(VerifyUpdate.class) Role role){
        roleService.updateObjectById(role);
        return ResponseUtil.ok();
    }

    @DeleteMapping("/role/{id}")
    public Object role(@PathVariable int id){
        roleService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/roles")
    public Object role(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                       @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        if (pagenum <= 0 ){
            return ResponseUtil.badArgument("页码必须为整数");
        }
        PageDomain pageDomain = roleService.pagingfindAll(pagenum, pagesize);
        return ResponseUtil.ok(pageDomain);
    }



}

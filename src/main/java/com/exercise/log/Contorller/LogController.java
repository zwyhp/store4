package com.exercise.log.Contorller;

import com.exercise.domain.PageDomain;
import com.exercise.log.service.ILogService;
import com.exercise.util.ResponseUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class LogController {

    @Autowired
    private ILogService logService;


    @DeleteMapping("/log/{id}")
    @RequiresPermissions("admin:log")
    public Object log(@PathVariable int id){
        logService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/logs")
    @RequiresPermissions("admin:log")
    public Object log(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                       @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        if (pagenum <= 0 ){
            return ResponseUtil.badArgument("页码必须为整数");
        }
        PageDomain pageDomain = logService.pagingFindAll(pagenum, pagesize);
        return ResponseUtil.ok(pageDomain);
    }



}

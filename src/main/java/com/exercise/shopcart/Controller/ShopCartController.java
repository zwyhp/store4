package com.exercise.shopcart.Controller;

import com.exercise.interfaceI.NotLog;
import com.exercise.shopcart.domain.ShopCart;
import com.exercise.shopcart.domain.ShopCartAggregate;
import com.exercise.shopcart.service.IshopCartService;
import com.exercise.user.domain.User;
import com.exercise.user.service.IuserService;
import com.exercise.util.ResponseUtil;
import com.exercise.util.verify.VerifyUpdate;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ShopCartController {

    @Autowired
    private IuserService userService;
    @Autowired
    private IshopCartService shopCartService;

    @NotLog
    @PostMapping("/addShop")
    @RequiresPermissions("user:user")
    public Object addShopCart(@RequestBody @Validated ShopCart shopCart){
        shopCartService.addObject(shopCart);
        return ResponseUtil.ok();
    }
    @NotLog
    @RequiresPermissions("user:user")
    @PutMapping("/updateShop")
    public Object updeteUser(@RequestBody @Validated(VerifyUpdate.class) ShopCart shopCart){
        shopCartService.updateObjectById(shopCart);
        return ResponseUtil.ok();
    }
    @NotLog
    @RequiresPermissions("user:user")
    @DeleteMapping("/shopcart/{id}")
    public Object shopCart(@PathVariable int id){
        shopCartService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @NotLog
    @RequiresPermissions("user:user")
    @GetMapping("/shopcart")
    public Object shopCart(){
        User user = userService.findUserByname((String) SecurityUtils.getSubject().getPrincipal());
        ShopCartAggregate cart= shopCartService.findCartByUid(user.getId());
        return ResponseUtil.ok(cart);
    }


}

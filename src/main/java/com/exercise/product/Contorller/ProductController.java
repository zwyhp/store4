package com.exercise.product.Contorller;

import com.exercise.domain.PageDomain;
import com.exercise.product.domain.Products;
import com.exercise.product.service.IProductService;
import com.exercise.util.ResponseUtil;
import com.exercise.util.verify.VerifyUpdate;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private IProductService productService;
    private int id;

    @PostMapping("/product")
    @RequiresPermissions("admin:product")
    public Object Product(@RequestBody @Validated Products Product){
        int i = productService.addObject(Product);
        return i>0? ResponseUtil.ok():ResponseUtil.fail();
    }

    @PutMapping("/product")
    @RequiresPermissions("admin:product")
    public Object updeteProduct(@RequestBody @Validated(VerifyUpdate.class) Products Product){
        productService.updateObjectById(Product);
        return ResponseUtil.ok();
    }

    @DeleteMapping("/product/{id}")
    @RequiresPermissions("admin:product")
    public Object Product(@PathVariable int id){
        productService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/product")
    public Object Product(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                       @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        if (pagenum <= 0 ){
            return ResponseUtil.badArgument("页码必须为正数");
        }
        PageDomain pageDomain = productService.pagingfindAll(pagenum, pagesize);
        return ResponseUtil.ok(pageDomain);
    }

    @PostMapping("/updateimg")
    @RequiresPermissions("admin:product")
    public Object updateImgByid(@RequestParam("id") int id,
                                @RequestParam("file")MultipartFile file) throws IOException {
        productService.updateImgByid(id,file);
        return ResponseUtil.ok();
    }
}

package com.exercise.product.Contorller;

import com.exercise.domain.PageDomain;
import com.exercise.product.domain.Products;
import com.exercise.product.service.IProductService;
import com.exercise.util.ResponseUtil;
import com.exercise.util.verify.VerifyUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class ProductController {

    @Autowired
    private IProductService ProductService;

    @PostMapping("/product")
    public Object Product(@RequestBody @Validated Products Product){
        int i = ProductService.addObject(Product);
        return i>0? ResponseUtil.ok():ResponseUtil.fail();
    }

    @PutMapping("/product")
    public Object updeteProduct(@RequestBody @Validated(VerifyUpdate.class) Products Product){
        ProductService.updateObjectById(Product);
        return ResponseUtil.ok();
    }

    @DeleteMapping("/product/{id}")
    public Object Product(@PathVariable int id){
        ProductService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("/product")
    public Object Product(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
                       @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        if (pagenum <= 0 ){
            return ResponseUtil.badArgument("页码必须为正数");
        }
        PageDomain pageDomain = ProductService.pagingfindAll(pagenum, pagesize);
        return ResponseUtil.ok(pageDomain);
    }

}

package com.exercise.orderform.Contorller;

import com.exercise.domain.PageDomain;
import com.exercise.orderform.domain.OrderAggregate;
import com.exercise.orderform.service.IOrdersService;
import com.exercise.util.ResponseUtil;
import com.exercise.util.verify.VerifyUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@ResponseBody
@RestController
public class OrderController {
    @Autowired
    private IOrdersService ordersService;

    @PostMapping("order")
    public Object order(@RequestBody @Validated OrderAggregate orderAggregate){
        ordersService.addObject(orderAggregate);
        return ResponseUtil.ok();
    }

    @PutMapping("order")
    public Object updateorder(@RequestBody @Validated(VerifyUpdate.class) OrderAggregate orderAggregate){
        ordersService.updateObjectById(orderAggregate);
        return ResponseUtil.ok();
    }

    @DeleteMapping("order/id")
    public Object order(@PathVariable int id){
        ordersService.deleteObjectById(id);
        return ResponseUtil.ok();
    }

    @GetMapping("order")
    public Object order(@RequestParam(value = "pagenum",defaultValue = "1") int pagenum,
    @RequestParam(value = "pagesize",defaultValue = "10") int pagesize){
        if (pagenum <= 0 ){
            return ResponseUtil.badArgument("页码必须为正数");
        }
        PageDomain pageDomain = ordersService.pagingfindAll(pagenum, pagesize);
        return ResponseUtil.ok(pageDomain);
    }


    @GetMapping("payment/{id}")
    public Object paymentById(@PathVariable int id){
        boolean b = ordersService.paymentById(id);
        return  b ?  ResponseUtil.ok() : ResponseUtil.fail();
    }

}

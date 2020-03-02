package com.exercise.orderform.Contorller;

import com.exercise.orderform.domain.OrderAggregate;
import com.exercise.orderform.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.ws.rs.GET;


@ResponseBody
@RestController
public class OrderController {
    @Autowired
    private IOrdersService ordersService;

    @PostMapping("order")
    public Object order(@RequestBody OrderAggregate orderAggregate){
        ordersService.addObject(orderAggregate);
        return "success";
    }
    @GetMapping("payment")
    public Object paymentById(){
        boolean b = ordersService.paymentById(13);
        return b;
    }

}

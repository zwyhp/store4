package com.exercise.orderform.event;

import com.exercise.event.handler.DomainEventHandler;
import com.exercise.orderform.domain.OrderAggregate;
import com.exercise.orderform.domain.Orderitem;
import com.exercise.shopcart.service.IshopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CreateOrderEventHandler implements DomainEventHandler<CreateOrderEvent> {
    @Autowired
    private IshopCartService shopCartService;
    @Override
    public void handle(CreateOrderEvent event) {
        OrderAggregate source = event.source();
        List<Orderitem> orderitems = source.getOrderitem();
        /*
          商品id  数量
         */
        Map<Integer,Integer> map = new HashMap<>();
        for (Orderitem orderitem : orderitems) {
            map.put(orderitem.getProductId(),orderitem.getBuynum());
        }
        shopCartService.createOrder(source.getOrders().getuId(),map);
    }
}

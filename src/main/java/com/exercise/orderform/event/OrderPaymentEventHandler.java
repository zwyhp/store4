package com.exercise.orderform.event;


import com.exercise.event.handler.DomainEventHandler;
import com.exercise.orderform.domain.OrderAggregate;
import com.exercise.orderform.domain.Orderitem;
import com.exercise.product.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 订单付款完成处理器
 */
@Component
public class OrderPaymentEventHandler implements DomainEventHandler<OrderPaymentEvent> {
    @Autowired
    private IProductService productService;
    @Override
    public void handle(OrderPaymentEvent event) {
        OrderAggregate source = event.source();
        List<Orderitem> orderitems = source.getOrderitem();
        for (Orderitem orderitem : orderitems){
            productService.reducePnum(orderitem.getProductId(),orderitem.getBuynum());
        }
    }
}

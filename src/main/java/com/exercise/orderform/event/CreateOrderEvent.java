package com.exercise.orderform.event;

import com.exercise.event.AbstractAggregateEvent;
import com.exercise.orderform.domain.OrderAggregate;

/**
 * 创建订单完成
 */
public class CreateOrderEvent extends AbstractAggregateEvent<OrderAggregate> {
    public CreateOrderEvent(OrderAggregate orderAggregate) {
        super(orderAggregate);
    }
}

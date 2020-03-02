package com.exercise.orderform.event;

import com.exercise.event.AbstractAggregateEvent;
import com.exercise.orderform.domain.OrderAggregate;


/**
 * 订单付款完成
 */
public class OrderPaymentEvent extends AbstractAggregateEvent<OrderAggregate> {
    public OrderPaymentEvent(OrderAggregate orderAggregate) {
        super(orderAggregate);
    }
}

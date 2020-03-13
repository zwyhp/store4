package com.exercise.orderform.domain;

import com.exercise.event.AbstractAggregateEvent;
import com.exercise.event.DomainEventBusHolder;
import com.exercise.event.eventinterface.Aggregate;
import com.exercise.event.handler.DomainEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderAggregate implements Aggregate {

    private Orders orders;

    private List<Orderitem> orderitem;
    /**
     * 是否付款  1  是   0 否
     */
    private int Payment;
    public OrderAggregate() {

    }
    public OrderAggregate(Orders orders, List<Orderitem> orderitem, int payment) {
        this.orders = orders;
        this.orderitem = orderitem;
        Payment = payment;
    }

    public Orders getOrders() {
        return orders;
    }

    public void setOrders(Orders orders) {
        this.orders = orders;
    }

    public List<Orderitem> getOrderitem() {
        return orderitem;
    }

    public void setOrderitem(List<Orderitem> orderitem) {
        this.orderitem = orderitem;
    }

    public int getPayment() {
        return Payment;
    }

    public void setPayment(int payment) {
        Payment = payment;
    }

    public void enable(AbstractAggregateEvent aggregateEvent){
        DomainEventPublisher publiser = DomainEventBusHolder.getPubliser();
        publiser.publish(aggregateEvent);
    }
}

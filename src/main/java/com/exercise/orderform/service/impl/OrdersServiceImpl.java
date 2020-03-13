package com.exercise.orderform.service.impl;

import com.exercise.domain.PageDomain;
import com.exercise.orderform.domain.OrderAggregate;
import com.exercise.orderform.domain.Orderitem;
import com.exercise.orderform.domain.Orders;
import com.exercise.orderform.event.CreateOrderEvent;
import com.exercise.orderform.event.OrderPaymentEvent;
import com.exercise.orderform.repository.IOrderItemRepository;
import com.exercise.orderform.repository.IOrdersRepository;
import com.exercise.orderform.service.IordersService;
import com.exercise.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IordersService {
    @Autowired
    private IOrdersRepository ordersRepository;
    @Autowired
    private IOrderItemRepository orderItemRepository;


    @Override
    public int addObject(OrderAggregate orderAggregate) {
        Orders orders = orderAggregate.getOrders();
        orders.setPaystate(0);
        orders.setDateTime(LocalDateTime.now());
        int oid = ordersRepository.save(orders);
        List<Orderitem> orderitems = orderAggregate.getOrderitem();
        for (Orderitem orderitem : orderitems){
            orderitem.setOrderId(oid);
            orderItemRepository.save(orderitem);
        }
        orderAggregate.enable(new CreateOrderEvent(orderAggregate));

        return 0;
    }

    @Override
    public void deleteObjectById(int id) {
        Orders ordersByid = ordersRepository.findOrdersById(id);
        BussinessUtil.isNull(ordersByid,BussinessUtil.ORDER_INEXISTENCE);
        ordersRepository.deleteOrdersById(id);
        orderItemRepository.deleteOrderItemByOid(id);
    }

    @Override
    public void updateObjectById(OrderAggregate orderAggregate) {
        Orders orders = orderAggregate.getOrders();
        List<Orderitem> orderitems = orderAggregate.getOrderitem();
        Orders ordersByid = ordersRepository.findOrdersById(orders.getId());
        BussinessUtil.isNull(ordersByid,BussinessUtil.ORDER_INEXISTENCE);
        ordersByid.copy(orders);
        ordersRepository.updateOrdersById(ordersByid);
        List<Orderitem> orderitemByOid = (List<Orderitem>)orderItemRepository.findOrderItemByOid(ordersByid.getId());
        for (int i = 0; i < orderitemByOid.size(); i++) {
            Orderitem orderitem = orderitemByOid.get(i);
            orderitem.copy(orderitems.get(i));
            orderItemRepository.updateOrderItem(orderitem);
        }
    }

    @Override
    public List findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public PageDomain pagingFindAll(int total, int pageSize) {
        List pagelist = new ArrayList();
        List<Orders> orders = (List<Orders>)ordersRepository.pagingFindOrders(total, pageSize);
        for (Orders order : orders){
            OrderAggregate orderAggregate = new OrderAggregate();
            orderAggregate.setOrders(order);
            orderAggregate.setOrderitem(orderItemRepository.findOrderItemByOid(order.getId()));
            orderAggregate.setPayment(order.getPaystate());
            pagelist.add(orderAggregate);
        }
        return new PageDomain(total,pageSize,findAll().size(),pagelist);
    }

    @Override
    public OrderAggregate findObjectById(int id) {
        Orders ordersByid = ordersRepository.findOrdersById(id);
        BussinessUtil.isNull(ordersByid,BussinessUtil.ORDER_INEXISTENCE);
        List orderitemByOid = orderItemRepository.findOrderItemByOid(id);
        return new OrderAggregate(ordersByid,orderitemByOid,ordersByid.getPaystate());
    }

    public boolean paymentById(int id){
        Orders ordersByid = ordersRepository.findOrdersById(id);
        BussinessUtil.isNull(ordersByid,BussinessUtil.ORDER_INEXISTENCE);
        ordersByid.setPaystate(1);
        ordersRepository.updateOrdersById(ordersByid);
        List orderitemByOid = orderItemRepository.findOrderItemByOid(ordersByid.getId());
        OrderAggregate aggregate = new OrderAggregate(ordersByid,orderitemByOid,1);
        aggregate.enable(new OrderPaymentEvent(aggregate));
        return true;
    }
}

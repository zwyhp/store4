package com.exercise.orderform.service;

import com.exercise.domain.PageDomain;
import com.exercise.orderform.domain.OrderAggregate;
import com.exercise.orderform.domain.Orderitem;
import com.exercise.orderform.domain.Orders;
import com.exercise.orderform.repository.IOrderItemRepository;
import com.exercise.orderform.repository.IOrdersRepository;
import com.exercise.util.BussinessUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {
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
        return 0;
    }

    @Override
    public void deleteObjectById(int id) {
        Orders ordersByid = ordersRepository.findOrdersByid(id);
        BussinessUtil.isNull(ordersByid,BussinessUtil.ORDER_INEXISTENCE);
        ordersRepository.deleteOrdersByid(id);
        orderItemRepository.deleteOrderitemByOid(id);
    }

    @Override
    public void updateObjectById(OrderAggregate orderAggregate) {
        Orders orders = orderAggregate.getOrders();
        List<Orderitem> orderitems = orderAggregate.getOrderitem();
        Orders ordersByid = ordersRepository.findOrdersByid(orders.getId());
        BussinessUtil.isNull(ordersByid,BussinessUtil.ORDER_INEXISTENCE);
        ordersByid.copy(orders);
        ordersRepository.updateOrdersByid(ordersByid);
        List<Orderitem> orderitemByOid = (List<Orderitem>)orderItemRepository.findOrderitemByOid(ordersByid.getId());
        for (int i = 0; i < orderitemByOid.size(); i++) {
            Orderitem orderitem = orderitemByOid.get(i);
            orderitem.copy(orderitems.get(i));
            orderItemRepository.updateOrderitem(orderitem);
        }
    }

    @Override
    public List findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public PageDomain pagingfindAll(int total, int pagesize) {
        List pagelist = new ArrayList();
        List<Orders> orders = (List<Orders>)ordersRepository.pagingfindOrders(total, pagesize);
        for (Orders order : orders){
            OrderAggregate orderAggregate = new OrderAggregate();
            orderAggregate.setOrders(order);
            orderAggregate.setOrderitem(orderItemRepository.findOrderitemByOid(order.getId()));
            orderAggregate.setPayment(order.getPaystate());
            pagelist.add(orderAggregate);
        }
        return new PageDomain(total,pagesize,findAll().size(),pagelist);
    }

    @Override
    public OrderAggregate findObjectById(int id) {
        Orders ordersByid = ordersRepository.findOrdersByid(id);
        BussinessUtil.isNull(ordersByid,BussinessUtil.ORDER_INEXISTENCE);
        List orderitemByOid = orderItemRepository.findOrderitemByOid(id);
        return new OrderAggregate(ordersByid,orderitemByOid,ordersByid.getPaystate());
    }

    public boolean paymentById(int id){
        Orders ordersByid = ordersRepository.findOrdersByid(id);
        BussinessUtil.isNull(ordersByid,BussinessUtil.ORDER_INEXISTENCE);
        ordersByid.setPaystate(1);
        ordersRepository.updateOrdersByid(ordersByid);
        List orderitemByOid = orderItemRepository.findOrderitemByOid(ordersByid.getId());
        OrderAggregate aggregate = new OrderAggregate(ordersByid,orderitemByOid,1);
        aggregate.enable();
        return true;
    }
}

package com.exercise.orderform.service;

import com.exercise.domain.PageDomain;
import com.exercise.orderform.domain.OrderAggregate;
import com.exercise.orderform.domain.Orderitem;
import com.exercise.orderform.domain.Orders;
import com.exercise.orderform.repository.IOrderItemRepository;
import com.exercise.orderform.repository.IOrdersRepository;
import com.exercise.util.BussinessExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    }

    @Override
    public void updateObjectById(OrderAggregate orderAggregate) {

    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public PageDomain pagingfindAll(int total, int pagesize) {
        return null;
    }

    @Override
    public OrderAggregate findObjectById(int id) {
        return null;
    }

    public boolean paymentById(int id){
        Orders ordersByid = ordersRepository.findOrdersByid(id);
        BussinessExceptionUtil.isNull(ordersByid,"订单不存在");
        ordersByid.setPaystate(1);
        ordersRepository.updateOrdersByid(ordersByid);
        List orderitemByOid = orderItemRepository.findOrderitemByOid(ordersByid.getId());
        OrderAggregate aggregate = new OrderAggregate(ordersByid,orderitemByOid,1);
        aggregate.enable();
        return true;
    }
}

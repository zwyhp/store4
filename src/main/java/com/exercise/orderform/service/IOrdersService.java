package com.exercise.orderform.service;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.IService;
import com.exercise.orderform.domain.OrderAggregate;

import java.util.List;

public interface IOrdersService extends IService<OrderAggregate> {
    @Override
    int addObject(OrderAggregate orderAggregate);

    @Override
    void deleteObjectById(int id);

    @Override
    void updateObjectById(OrderAggregate orderAggregate);

    @Override
    List findAll();

    @Override
    PageDomain pagingfindAll(int total, int pagesize);

    @Override
    OrderAggregate findObjectById(int id);

    boolean paymentById(int id);
}

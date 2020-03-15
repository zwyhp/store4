package com.exercise.orderform.service;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.IService;
import com.exercise.orderform.domain.OrderAggregate;

import java.util.List;

public interface IordersService extends IService<OrderAggregate> {
    @Override
    int addObject(OrderAggregate orderAggregate);

    @Override
    void deleteObjectById(int id);

    @Override
    void updateObjectById(OrderAggregate orderAggregate);

    @Override
    List findAll();

    @Override
    PageDomain pagingFindAll(int total, int pageSize);

    PageDomain pagingFindAll(int total, int pageSize,String username,String sort);

    @Override
    OrderAggregate findObjectById(int id);

    boolean paymentById(int id);
}

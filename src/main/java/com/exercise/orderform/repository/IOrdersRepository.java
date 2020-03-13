package com.exercise.orderform.repository;

import com.exercise.orderform.domain.Orders;

import java.util.List;

public interface IOrdersRepository {
    int save(Orders orders);

    Orders findOrdersById(int id);

    void deleteOrdersById(int id);

    void updateOrdersById(Orders orders);

    List findAll();

    List pagingFindOrders(int total, int pagesize);

    List conditionsQuery(int uid);
}

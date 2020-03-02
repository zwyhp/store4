package com.exercise.orderform.repository;

import com.exercise.orderform.domain.Orders;

import java.util.List;

public interface IOrdersRepository {
    int save(Orders orders);

    Orders findOrdersByid(int id);

    void deleteOrdersByid(int id);

    void updateOrdersByid(Orders orders);

    List findAll();

    List pagingfindOrders(int total, int pagesize);

    List conditionsQuery(int uid);
}

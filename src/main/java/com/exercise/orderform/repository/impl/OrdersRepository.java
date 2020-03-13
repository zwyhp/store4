package com.exercise.orderform.repository.impl;

import com.exercise.orderform.domain.Orders;
import com.exercise.orderform.repository.IOrdersRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrdersRepository implements IOrdersRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public OrdersRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }


    @Override
    public int save(Orders orders) {
        return (int)currentSession().save(orders);
    }


    @Override
    public Orders findOrdersById(int id) {
        return currentSession().get(Orders.class, id);
    }

    @Override
    public void deleteOrdersById(int id) {
        Orders orders = currentSession().get(Orders.class, id);
        currentSession().delete(orders);
    }

    @Override
    public void updateOrdersById(Orders orders) {
        currentSession().update(orders);
    }

    @Override
    public List findAll() {
        String sql = "SELECT * FROM Orders";
        NativeQuery nativeQuery = currentSession().createNativeQuery(sql);
        nativeQuery.addEntity(Orders.class);
        return nativeQuery.getResultList();
    }

    @Override
    public List pagingFindOrders(int total, int pagesize) {
        String sql = "SELECT * FROM Orders";
        return currentSession().createNativeQuery(sql)
                .addEntity(Orders.class)
                .setFirstResult(total - 1)
                .setMaxResults(pagesize)
                .list();

    }

    @Override
    public List conditionsQuery(int uid) {
        String sql = "SELECT * FROM Orders where u_id = " +uid;
        return currentSession().createNativeQuery(sql)
                .addEntity(Orders.class)
                .list();

    }
}

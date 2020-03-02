package com.exercise.orderform.repository;

import com.exercise.orderform.domain.Orders;
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
    public Orders findOrdersByid(int id) {
        Orders orders = currentSession().get(Orders.class, id);
        return orders;
    }

    @Override
    public void deleteOrdersByid(int id) {
        Orders orders = currentSession().get(Orders.class, id);
        currentSession().delete(orders);
    }

    @Override
    public void updateOrdersByid(Orders orders) {
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
    public List pagingfindOrders(int total, int pagesize) {
        String sql = "SELECT * FROM Orders";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(Orders.class)
                .setFirstResult(total - 1)
                .setMaxResults(pagesize)
                .list();
        return list;
    }

    @Override
    public List conditionsQuery(int uid) {
        String sql = "SELECT * FROM Orders where u_id = " +uid;
        List list = currentSession().createNativeQuery(sql)
                .addEntity(Orders.class)
                .list();
        return list;
    }
}

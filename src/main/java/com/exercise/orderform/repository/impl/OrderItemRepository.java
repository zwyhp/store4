package com.exercise.orderform.repository.impl;

import com.exercise.orderform.domain.Orderitem;
import com.exercise.orderform.repository.IOrderItemRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OrderItemRepository implements IOrderItemRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public OrderItemRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int save(Orderitem orderitem) {
        return (int)currentSession().save(orderitem);
    }

    @Override
    public void deleteOrderItemByOid(int oId) {
        List orderItemByOid = findOrderItemByOid(oId);
        for (Object orderItem: orderItemByOid){
            currentSession().delete(orderItem);
        }
    }

    @Override
    public void updateOrderItem(Orderitem orderitem) {
        currentSession().update(orderitem);
    }

    @Override
    public List findOrderItemByOid(int oId) {
        String sql = "SELECT * FROM Orderitem where order_id = :o_id";
        return currentSession().createNativeQuery(sql)
                .addEntity(Orderitem.class)
                .setParameter("o_id",oId)
                .list();
    }
}

package com.exercise.orderform.repository;

import com.exercise.orderform.domain.Orderitem;
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
    public void deleteOrderitemByOid(int oId) {
        List orderitemByOid = findOrderitemByOid(oId);
        for (Object orderitem: orderitemByOid){
            currentSession().delete(orderitem);
        }
    }

    @Override
    public void updateOrderitem(Orderitem orderitem) {
        currentSession().update(orderitem);
    }

    @Override
    public List findOrderitemByOid(int oId) {
        String sql = "SELECT * FROM Orderitem where order_id = :o_id";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(Orderitem.class)
                .setParameter("o_id",oId)
                .list();
        return list;
    }
}

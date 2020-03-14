package com.exercise.shopcart.repository.impl;

import com.exercise.shopcart.domain.ShopCart;
import com.exercise.shopcart.repository.IshopCartRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ShopCartRepository implements IshopCartRepository {
    private SessionFactory sessionFactory;

    @Autowired
    public ShopCartRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public void addOrUpdateCart(ShopCart shopCart) {
        currentSession().saveOrUpdate(shopCart);
    }


    @Override
    public void reduceShop(int id) {
        ShopCart shopCart = currentSession().get(ShopCart.class, id);
        currentSession().delete(shopCart);
    }

    @Override
    public List findCartByUid(int uId) {
        String sql = "SELECT * FROM shoppingcart where u_id = :uId";
        return currentSession().createNativeQuery(sql)
                .addEntity(ShopCart.class)
                .setParameter("uId",uId)
                .list();
    }

    @Override
    public ShopCart findCartByPid(int uid, int pid) {
        String sql = "SELECT * FROM shoppingcart where u_id = :uId and p_id = :pid";
        List list = currentSession().createNativeQuery(sql)
                .addEntity(ShopCart.class)
                .setParameter("uId", uid)
                .setParameter("pid", pid)
                .list();
        return list.isEmpty() ? null :(ShopCart) list.get(0);
    }
}

package com.exercise.product.repository;

import com.exercise.product.domain.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsRepository implements IProductsRepository{

    private SessionFactory sessionFactory;

    @Autowired
    public ProductsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;

    }

    private Session currentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int save(Products product) {
        return (int)currentSession().save(product);
    }

    @Override
    public Products findProductsByname(String name) {
        return null;
    }

    @Override
    public Products findProductsById(int id) {
        return currentSession().get(Products.class,id);
    }

    @Override
    public void deleteProductsByid(int id) {
        Products productsById = findProductsById(id);
        currentSession().delete(productsById);
    }

    @Override
    public void updateProductsByid(Products product) {
        currentSession().update(product);
    }

    @Override
    public List findAll() {
        String sql = "SELECT * FROM Products";
        return currentSession().createNativeQuery(sql)
                .addEntity(Products.class)
                .list();
    }

    @Override
    public List pagingfindProducts(int total, int pagesize) {
        String sql = "SELECT * FROM Products";
        return currentSession().createNativeQuery(sql)
                .addEntity(Products.class)
                .setFirstResult(total - 1)
                .setMaxResults(pagesize)
                .list();
    }

    @Override
    public List conditionsQuery(String name) {
        String sql = "SELECT * FROM user where name like :name";
        return currentSession().createNativeQuery(sql)
                .addEntity(Products.class)
                .setParameter("name","%"+name+"%")
                .list();
    }

    @Override
    public List conditionsQuery(int uid) {
        String sql = "SELECT * FROM user where u_id like :uid";
        return currentSession().createNativeQuery(sql)
                .addEntity(Products.class)
                .setParameter("uid",uid)
                .list();

    }

    public void updateImgByid(int id, String url){
        Products productsById = findProductsById(id);
        productsById.setImgurl(url);
        updateProductsByid(productsById);
    }

    @Override
    public Products findImgByMD5Name(String MD5name) {
        String sql = "SELECT * FROM Products where imgurl like :md5name";
        return (Products)currentSession().createNativeQuery(sql)
                .addEntity(Products.class)
                .setParameter("md5name","%"+MD5name+"%")
                .list().get(0);
    }

}

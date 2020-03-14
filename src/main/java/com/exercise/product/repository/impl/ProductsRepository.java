package com.exercise.product.repository.impl;

import com.exercise.product.domain.Products;
import com.exercise.product.repository.IproductsRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductsRepository implements IproductsRepository {

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
    public Products findProductsByName(String name) {
        return null;
    }

    @Override
    public Products findProductsById(int id) {
        return currentSession().get(Products.class,id);
    }

    @Override
    public void deleteProductsById(int id) {
        Products productsById = findProductsById(id);
        currentSession().delete(productsById);
    }

    @Override
    public void updateProductsById(Products product) {
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
    public List pagingFindProducts(int total, int pagesize) {
        String sql = "SELECT * FROM Products";
        return currentSession().createNativeQuery(sql)
                .addEntity(Products.class)
                .setFirstResult(total - 1)
                .setMaxResults(pagesize)
                .list();
    }


    public List pagingFindProducts(int total, int pageSize,String categoty) {
        String sql = "SELECT * FROM Products where categoty = :categoty";
        return currentSession().createNativeQuery(sql)
                .addEntity(Products.class)
                .setParameter("categoty",categoty)
                .setFirstResult(total - 1)
                .setMaxResults(pageSize)
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


    public void updateImgById(int id, String url){
        Products productsById = findProductsById(id);
        productsById.setImgurl(url);
        updateProductsById(productsById);
    }

    @Override
    public Products findImgByMD5Name(String MD5name) {
        String sql = "SELECT * FROM Products where imgurl like :md5name";
        List md5name = currentSession().createNativeQuery(sql)
                .addEntity(Products.class)
                .setParameter("md5name", "%" + MD5name + "%")
                .list();
        return md5name.isEmpty() ? null : (Products)md5name.get(0);
    }

}

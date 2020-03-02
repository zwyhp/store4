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

    }

    @Override
    public void updateProductsByid(Products product) {
        currentSession().update(product);
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List pagingfindProducts(int total, int pagesize) {
        return null;
    }

    @Override
    public List conditionsQuery(String name) {
        return null;
    }

    @Override
    public List conditionsQuery(String name, int uid) {
        return null;
    }
}

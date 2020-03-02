package com.exercise.product.repository;

import com.exercise.product.domain.Products;

import java.util.List;

public interface IProductsRepository {
    int save(Products product);

    Products findProductsByname(String name);

    Products findProductsById(int id);

    void deleteProductsByid(int id);

    void updateProductsByid(Products product);

    List findAll();

    List pagingfindProducts(int total, int pagesize);

    List conditionsQuery(String name);

    List conditionsQuery(String name, int uid);


}

package com.exercise.product.repository;

import com.exercise.product.domain.Products;

import java.util.List;

public interface IproductsRepository {
    int save(Products product);

    Products findProductsByName(String name);

    Products findProductsById(int id);

    void deleteProductsById(int id);

    void updateProductsById(Products product);

    List findAll();

    List pagingFindProducts(int total, int pagesize);

    List pagingFindProducts(int total, int pageSize,String categoty);

    List conditionsQuery(String name);

    List conditionsQuery(int uid);

    void updateImgById(int id, String url);

    Products findImgByMD5Name(String MD5name);
}

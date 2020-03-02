package com.exercise.product.service;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.IService;
import com.exercise.product.domain.Products;

import java.util.List;

public interface IProductService extends IService<Products> {
    @Override
    int addObject(Products product);

    @Override
    void deleteObjectById(int id);

    @Override
    void updateObjectById(Products product);

    @Override
    List findAll();

    @Override
    PageDomain pagingfindAll(int total, int pagesize);

    @Override
    Products findObjectById(int id);

    boolean reducePnum(int id,int reducenum);
}

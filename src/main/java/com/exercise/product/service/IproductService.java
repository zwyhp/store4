package com.exercise.product.service;

import com.exercise.domain.PageDomain;
import com.exercise.interfaceI.IService;
import com.exercise.product.domain.Products;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IproductService extends IService<Products> {
    @Override
    int addObject(Products product);

    @Override
    void deleteObjectById(int id);

    @Override
    void updateObjectById(Products product);

    @Override
    List findAll();

    @Override
    PageDomain pagingFindAll(int total, int pageSize);

    @Override
    Products findObjectById(int id);

    boolean reducePnum(int id,int reducenum);

    void updateImgByid(int id, MultipartFile file) throws IOException;
}

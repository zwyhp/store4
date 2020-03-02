package com.exercise.product.service;

import com.exercise.domain.PageDomain;
import com.exercise.product.domain.Products;
import com.exercise.product.repository.IProductsRepository;
import com.exercise.util.BussinessExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IProductService{
    @Autowired
    private IProductsRepository productsRepository;

    @Override
    public int addObject(Products products) {
        return 0;
    }

    @Override
    public void deleteObjectById(int id) {

    }

    @Override
    public void updateObjectById(Products product) {
        findObjectById(product.getId());
        productsRepository.updateProductsByid(product);
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public PageDomain pagingfindAll(int total, int pagesize) {
        return null;
    }

    @Override
    public Products findObjectById(int id) {
        Products productsById = productsRepository.findProductsById(id);
        BussinessExceptionUtil.isNull(productsById,"商品不存在");
        return productsById;
    }

    public boolean reducePnum(int id,int reducenum){
        Products objectById = findObjectById(id);
        int newnum =  objectById.getPnum()-reducenum;
        if (newnum<=0){
            BussinessExceptionUtil.error("库存不足");
        }
        objectById.setPnum(newnum);
        productsRepository.updateProductsByid(objectById);
        return true;
    }
}

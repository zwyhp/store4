package com.exercise.product.service;

import com.exercise.domain.PageDomain;
import com.exercise.product.domain.Products;
import com.exercise.product.repository.IProductsRepository;
import com.exercise.util.BussinessUtil;
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
        Products productsByname = productsRepository.findProductsByname(products.getName());
        BussinessUtil.isNull(productsByname,BussinessUtil.PRODUCTNAME_REPETITION);
        return productsRepository.save(products);
    }

    @Override
    public void deleteObjectById(int id) {
        Products objectById = findObjectById(id);
        BussinessUtil.isNull(objectById,BussinessUtil.PRODUCT_INEXISTENCE);
        productsRepository.deleteProductsByid(id);
    }

    @Override
    public void updateObjectById(Products product) {
        Products objectById = findObjectById(product.getId());
        BussinessUtil.isNull(objectById,BussinessUtil.PRODUCT_INEXISTENCE);
        productsRepository.updateProductsByid(product);
    }

    @Override
    public List findAll() {
        return productsRepository.findAll();
    }

    @Override
    public PageDomain pagingfindAll(int total, int pagesize) {
        int size = productsRepository.findAll().size();
        List users = productsRepository.pagingfindProducts(total, pagesize);
        return new PageDomain(total, pagesize, size, users);
    }

    @Override
    public Products findObjectById(int id) {
        Products productsById = productsRepository.findProductsById(id);
        BussinessUtil.isNull(productsById,BussinessUtil.PRODUCT_INEXISTENCE);
        return productsById;
    }

    public boolean reducePnum(int id,int reducenum){
        Products objectById = findObjectById(id);
        int newnum =  objectById.getPnum()-reducenum;
        if (newnum<=0){
            BussinessUtil.error(BussinessUtil.UNDER_STOCK);
        }
        objectById.setPnum(newnum);
        productsRepository.updateProductsByid(objectById);
        return true;
    }
}

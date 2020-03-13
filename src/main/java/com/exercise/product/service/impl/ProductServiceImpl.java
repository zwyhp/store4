package com.exercise.product.service.impl;

import com.exercise.domain.PageDomain;
import com.exercise.product.domain.Products;
import com.exercise.product.repository.IproductsRepository;
import com.exercise.product.service.IproductService;
import com.exercise.util.BussinessUtil;
import com.exercise.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements IproductService {
    @Autowired
    private IproductsRepository productsRepository;

    @Override
    public int addObject(Products products) {
        Products productsByname = productsRepository.findProductsByName(products.getName());
        BussinessUtil.isnotNull(productsByname,BussinessUtil.PRODUCTNAME_REPETITION);
        return productsRepository.save(products);
    }

    @Override
    public void deleteObjectById(int id) {
        Products objectById = findObjectById(id);
        BussinessUtil.isNull(objectById,BussinessUtil.PRODUCT_INEXISTENCE);
        productsRepository.deleteProductsById(id);
    }

    @Override
    public void updateObjectById(Products product) {
        Products objectById = findObjectById(product.getId());
        BussinessUtil.isNull(objectById,BussinessUtil.PRODUCT_INEXISTENCE);
        BussinessUtil.isnotNull(productsRepository.findProductsByName(product.getName()),BussinessUtil.PRODUCTNAME_REPETITION);
        objectById.copy(product);
        productsRepository.updateProductsById(objectById);
    }

    @Override
    public List findAll() {
        return productsRepository.findAll();
    }

    @Override
    public PageDomain pagingFindAll(int total, int pageSize) {
        int size = productsRepository.findAll().size();
        BussinessUtil.pagingfind((size/ pageSize)+1 < total);
        List users = productsRepository.pagingFindProducts(total, pageSize);
        return new PageDomain(total, pageSize, size, users);
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
        productsRepository.updateProductsById(objectById);
        return true;
    }

    public void updateImgByid(int id, MultipartFile file) throws IOException {
        String fileMD5name = FileUtil.GetFileMD5name(file);
        Products product = productsRepository.findImgByMD5Name(fileMD5name);
        if (product == null){
            String url = FileUtil.uploadFile1(file);
            productsRepository.updateImgById(id,url);
        }else{
            productsRepository.updateImgById(id,product.getImgurl());
        }
    }
}

package com.exercise.shopcart.repository;

import com.exercise.shopcart.domain.ShopCart;

import java.util.List;

public interface IshopCartRepository {
    void addOrUpdateCart(ShopCart shopCart);
    void reduceShop(int id);
    List findCartByUid(int uId);
    ShopCart findCartByPid(int uid,int pid);
}

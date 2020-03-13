package com.exercise.shopcart.service;

import com.exercise.shopcart.domain.ShopCart;
import com.exercise.shopcart.domain.ShopCartAggregate;

import java.util.Map;

public interface IshopCartService {

    int addObject(ShopCart shopCart);


    void deleteObjectById(int id);


    void updateObjectById(ShopCart shopCart);

    ShopCartAggregate findCartByUid(int uid);

    void createOrder(int uid, Map<Integer,Integer> map);
}

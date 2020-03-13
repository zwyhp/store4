package com.exercise.shopcart.service.impl;

import com.exercise.product.domain.Products;
import com.exercise.product.service.IproductService;
import com.exercise.shopcart.domain.ShopCart;
import com.exercise.shopcart.domain.ShopCartAggregate;
import com.exercise.shopcart.repository.IshopCartRepository;
import com.exercise.shopcart.service.IshopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Transactional
@Service
public class ShopCartServiceImpl implements IshopCartService {

    @Autowired
    private IproductService productService;

    @Autowired
    private IshopCartRepository shopcartRepository;

    @Override
    public int addObject(ShopCart shopCart) {
        shopcartRepository.addOrUpdateCart(shopCart);
        return 1;
    }

    @Override
    public void deleteObjectById(int id) {
        shopcartRepository.reduceShop(id);
    }

    @Override
    public void updateObjectById(ShopCart shopCart) {
        addObject(shopCart);
    }

    public ShopCartAggregate findCartByUid(int uid){
        List<ShopCart> carts = (List<ShopCart>) shopcartRepository.findCartByUid(uid);
        Map<ShopCart, Products> cartMap = new HashMap<>();
        for (ShopCart shop: carts) {
            cartMap.put(shop,productService.findObjectById(shop.getpId()));
        }
        return new ShopCartAggregate(cartMap);
    }

    public void createOrder(int uid,Map<Integer,Integer> map){
        List<ShopCart> cart = (List<ShopCart>)shopcartRepository.findCartByUid(uid);
        Set<Integer> integers = map.keySet();
        for (Integer integer : integers) {
            Optional<ShopCart> first = cart.stream().filter(i -> i.getpId() == integer).findFirst();
            if (!first.isPresent()){
                continue;
            }
            ShopCart cart1 = first.get();
            if (cart1.getNum()-map.get(integer) > 0){
                cart1.setNum(cart1.getNum()-map.get(integer));
                updateObjectById(cart1);
            }else {
                deleteObjectById(cart1.getId());
            }
        }
    }
}

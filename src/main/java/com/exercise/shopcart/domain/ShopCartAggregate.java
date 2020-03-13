package com.exercise.shopcart.domain;

import com.exercise.product.domain.Products;

import java.util.Map;
import java.util.Set;


public class ShopCartAggregate  {

    /**
     *  boolean   true代表选中， false 代表没选中
     */
    private Map<ShopCart, Products> cartMap ;

    private double price;

    public ShopCartAggregate(Map<ShopCart, Products> cartMap) {
        this.cartMap = cartMap;
        Set<ShopCart> shopCarts = cartMap.keySet();
        price = 0.0;
        for (ShopCart shopCart : shopCarts) {
         price += cartMap.get(shopCart).getPrice()*shopCart.getNum();
        }
    }

    public Map<ShopCart, Products> getCartMap() {
        return cartMap;
    }



}

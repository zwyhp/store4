package com.exercise.orderform.domain;

import javax.persistence.*;

@Entity
@Table(name = "Orderitem")
public class Orderitem {
    @Id
    @GeneratedValue
    private int id;
    @Column(name = "order_id")
    private int orderId;
    @Column(name = "product_id")
    private int productId;
    @Column(name = "buynum")
    private int buynum;

    public Orderitem() {

    }
    public Orderitem(int orderId, int productId, int buynum) {
        this.orderId = orderId;
        this.productId = productId;
        this.buynum = buynum;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getBuynum() {
        return buynum;
    }

    public void setBuynum(int buynum) {
        this.buynum = buynum;
    }
}

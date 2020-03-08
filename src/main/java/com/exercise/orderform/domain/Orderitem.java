package com.exercise.orderform.domain;

import com.exercise.util.verify.VerifyError;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Orderitem")
public class Orderitem {
    @Id
    @GeneratedValue
    private int id;
    @NotNull(message = VerifyError.ORDERITEM_OID_NOT_NULL)
    @Column(name = "order_id")
    private int orderId;
    @NotNull(message = VerifyError.ORDERITEM_PID_NOT_NULL)
    @Column(name = "product_id")
    private int productId;
    @NotNull(message = VerifyError.ORDERITEM_NUM_NOT_NULL)
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

    public void copy(Orderitem orderitem){
        this.productId = orderitem.productId;
        this.buynum = orderitem.buynum;
    }
}

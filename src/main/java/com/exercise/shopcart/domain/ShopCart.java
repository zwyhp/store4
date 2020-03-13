package com.exercise.shopcart.domain;

import com.exercise.util.verify.VerifyError;
import com.exercise.util.verify.VerifyUpdate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "shoppingcart")
public class ShopCart {
    @Id
    @GeneratedValue()
    @NotNull(message = VerifyError.ID_NOT_NULL ,groups = {VerifyUpdate.class})
    @Column(name = "id")
    private int id;
    @NotNull(message = VerifyError.CART_UID_NOT_NULL)
    @Column(name = "u_id")
    private int uId;
    @NotNull(message = VerifyError.CART_PID_NOT_NULL)
    @Column(name = "p_id")
    private int pId;
    @NotNull(message = VerifyError.CART_NUM_NOT_NULL)
    @Column(name = "num")
    private int num;

    public ShopCart() {

    }

    public ShopCart(int uId, int pId, int num) {
        this.uId = uId;
        this.pId = pId;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

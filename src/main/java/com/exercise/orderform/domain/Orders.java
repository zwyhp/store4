package com.exercise.orderform.domain;

import com.exercise.util.verify.VerifyError;
import com.exercise.util.verify.VerifyUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue
    @NotNull(message = VerifyError.ID_NOT_NULL,groups = {VerifyUpdate.class})
    @Column(name = "id")
    private int id;
    @NotNull(message = VerifyError.ORDER_UID_NOT_NULL)
    @Column(name = "u_id")
    private int uId;
    @NotNull(message = VerifyError.ORDER_MONEY_NOT_NULL)
    @Column(name = "money")
    private double money;
    @NotEmpty(message = VerifyError.ORDER_ADDRESS_NOT_NULL)
    @Column(name = "receiverAddress")
    private String receiverAddress;
    @NotEmpty(message = VerifyError.ORDER_PHONE_NOT_NULL)
    @Column(name = "receiverPhone")
    private String receiverPhone;
    @Column(name = "paystate")
    private int paystate;
    @Column(name = "ordertime")
    private LocalDateTime orderTime;

    public Orders() {

    }

    public Orders(int uId, double money, String receiverAddress, String receiverPhone, int paystate, LocalDateTime ordertime) {
        this.uId = uId;
        this.money = money;
        this.receiverAddress = receiverAddress;
        this.receiverPhone = receiverPhone;
        this.paystate = paystate;
        this.orderTime = ordertime;
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

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getPaystate() {
        return paystate;
    }

    public void setPaystate(int paystate) {
        this.paystate = paystate;
    }

    public LocalDateTime getDateTime() {
        return orderTime;
    }

    public void setDateTime(LocalDateTime ordertime) {
        this.orderTime = ordertime;
    }

    public void copy(Orders orders){
        this.money = orders.money;
        this.receiverAddress = orders.receiverAddress;
        this.receiverPhone = orders.receiverPhone;
        this.paystate = orders.paystate;
    }
}

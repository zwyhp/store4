package com.exercise.orderform.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Orders {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @NotNull
    @Column(name = "u_id")
    private int uId;
    @NotNull
    @Column(name = "money")
    private double money;
    @NotNull
    @Column(name = "receiverAddress")
    private String receiverAddress;
    @NotNull
    @Column(name = "receiverPhone")
    private String receiverPhone;
    @NotNull
    @Size(max = 1)
    @Column(name = "paystate")
    private int paystate;
    @Column(name = "ordertime")
    private LocalDateTime ordertime;

    public Orders() {

    }

    public Orders(int uId, double money, String receiverAddress, String receiverPhone, int paystate, LocalDateTime ordertime) {
        this.uId = uId;
        this.money = money;
        this.receiverAddress = receiverAddress;
        this.receiverPhone = receiverPhone;
        this.paystate = paystate;
        this.ordertime = ordertime;
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
        return ordertime;
    }

    public void setDateTime(LocalDateTime ordertime) {
        this.ordertime = ordertime;
    }
}

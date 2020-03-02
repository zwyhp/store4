package com.exercise.product.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;
    @Null
    @Column(name ="u_id")
    private int uId;
    @NotNull
    @Column(name = "name")
    private String name;
    @NotNull
    @Column(name = "price")
    private double price;
    @NotNull
    @Column(name = "categoty")
    private String categoty;
    @NotNull
    @Column(name = "pnum")
    private int pnum;
    @NotNull
    @Column(name = "imgurl")
    private String imgurl;
    @NotNull
    @Column(name = "description")
    private String description;

    public Products() {

    }

    public Products(int uId, String name, double price, String categoty, int pnum, String imgurl, String description) {
        this.uId = uId;
        this.name = name;
        this.price = price;
        this.categoty = categoty;
        this.pnum = pnum;
        this.imgurl = imgurl;
        this.description = description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategoty() {
        return categoty;
    }

    public void setCategoty(String categoty) {
        this.categoty = categoty;
    }

    public int getPnum() {
        return pnum;
    }

    public void setPnum(int pnum) {
        this.pnum = pnum;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

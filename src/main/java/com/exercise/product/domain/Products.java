package com.exercise.product.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import com.exercise.util.verify.VerifyError;
import com.exercise.util.verify.VerifyUpdate;

@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue
    @NotNull(message = VerifyError.ID_NOT_NULL,groups = {VerifyUpdate.class})
    @Column(name = "id")
    private int id;

    @NotNull(message = VerifyError.PRODUCT_UID_NOT_NULL)
    @Column(name ="u_id")
    private int uId;

    @NotEmpty(message = VerifyError.PRODUCT_NAME_NOT_NULL)
    @Column(name = "name")
    private String name;

    @NotNull(message = VerifyError.PRODUCT_PRICE_NOT_NULL)
    @Column(name = "price")
    private double price;

    @NotEmpty(message = VerifyError.PRODUCT_CATEGOTY_NOT_NULL)
    @Column(name = "categoty")
    private String categoty;

    @NotNull(message = VerifyError.PRODUCT_PNUM_NOT_NULL)
    @Column(name = "pnum")
    private int pnum;

    @Column(name = "imgurl")
    private String imgurl;

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

    public void copy(Products products){
        this.uId = products.uId;
        this.name = products.name;
        this.price = products.price;
        this.categoty = products.categoty;
        this.pnum = products.pnum;
        this.imgurl = products.imgurl;
        this.description = products.description;
    }
}

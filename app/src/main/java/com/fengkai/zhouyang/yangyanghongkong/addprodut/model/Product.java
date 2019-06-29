package com.fengkai.zhouyang.yangyanghongkong.addprodut.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Product {
    @Id(autoincrement = true)
    public Long id;
    public String icon;
    public String title;
    public String price;
    public String num;
    @Transient
    public int type;

    @Generated(hash = 1652825074)
    public Product(Long id, String icon, String title, String price, String num) {
        this.id = id;
        this.icon = icon;
        this.title = title;
        this.price = price;
        this.num = num;
    }

    @Generated(hash = 1890278724)
    public Product() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIcon() {
        return this.icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return this.price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getNum() {
        return this.num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}

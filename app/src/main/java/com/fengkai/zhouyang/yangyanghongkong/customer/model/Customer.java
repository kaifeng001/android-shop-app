package com.fengkai.zhouyang.yangyanghongkong.customer.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Customer {
    public int id;
    public String name;
    public String weixin;
    public String phone;
    public String address;
    @Generated(hash = 712838223)
    public Customer(int id, String name, String weixin, String phone,
            String address) {
        this.id = id;
        this.name = name;
        this.weixin = weixin;
        this.phone = phone;
        this.address = address;
    }
    @Generated(hash = 60841032)
    public Customer() {
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getWeixin() {
        return this.weixin;
    }
    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
}

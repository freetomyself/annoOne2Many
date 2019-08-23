package com.itcast.domain;

import java.io.Serializable;

/**
 * @program: day04_eesy_annoOne2Many--com.itcast.domain
 * @author: WaHotDog 2019-08-23 16:55
 **/


public class Account implements Serializable {
    private Integer uid;
    private Integer id;
    private Double money;

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getRid() {
        return id;
    }

    public void setRid(Integer id) {
        this.id = id;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    @Override
    public String toString()  {
        return "Account{" +
                "uid=" + uid +
                ", id=" + id +
                ", money=" + money +
                '}';
    }
}

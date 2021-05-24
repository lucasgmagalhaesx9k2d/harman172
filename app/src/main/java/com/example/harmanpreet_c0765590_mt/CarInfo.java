package com.example.harmanpreet_c0765590_mt;

import java.io.Serializable;

public class CarInfo implements Serializable {

    private String carName;
    private String carRent;
    String days;
    String age;
    String amount;
    String totalAmount;

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarRent() {
        return carRent;
    }

    public void setCarRent(String carRent) {
        this.carRent = carRent;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }
}

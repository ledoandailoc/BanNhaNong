package com.example.uit.bannhanong.DTO;

import java.io.Serializable;

/**
 * Created by ledoa on 12/15/2015.
 */
public class Agricultural implements Serializable{

    public int id;
    public String name;
    public  double priceToDayDomestic;
    public  double priceYesterdayDomestic;
    public  double priceTodayInternational;
    public  double priceYesterdayInternational;
    public  String province;
    public  String status;
    public  String unit;

    public Agricultural(){

    }

    /*public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceToDayDomestic() {
        return priceToDayDomestic;
    }

    public void setPriceToDayDomestic(double priceToDayDomestic) {
        this.priceToDayDomestic = priceToDayDomestic;
    }

    public double getPriceYesterdayDomestic() {
        return priceYesterdayDomestic;
    }

    public void setPriceYesterdayDomestic(double priceYesterdayDomestic) {
        this.priceYesterdayDomestic = priceYesterdayDomestic;
    }

    public double getPriceTodayInternational() {
        return priceTodayInternational;
    }

    public void setPriceTodayInternational(double priceTodayInternational) {
        this.priceTodayInternational = priceTodayInternational;
    }

    public double getPriceYesterdayInternational() {
        return priceYesterdayInternational;
    }

    public void setPriceYesterdayInternational(double priceYesterdayInternational) {
        this.priceYesterdayInternational = priceYesterdayInternational;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }*/
}

package com.example.uit.bannhanong.DTO;

/**
 * Created by ledoa on 12/15/2015.
 */
public class Agricultural {

     String name;
     int priceDomestic;
     int priceInternational;
     String unit;
     String status;

    public int getPriceDomestic() {
        return priceDomestic;
    }

    public void setPriceDomestic(int priceDomestic) {
        this.priceDomestic = priceDomestic;
    }

    public int getPriceInternational() {
        return priceInternational;
    }

    public void setPriceInternational(int priceInternational) {
        this.priceInternational = priceInternational;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

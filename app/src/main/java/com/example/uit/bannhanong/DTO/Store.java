package com.example.uit.bannhanong.DTO;

import java.io.Serializable;

/**
 * Created by ledoa on 12/15/2015.
 */
public class Store {

    public int id;
    public String name;
    public String category;
    public double latitude;
    public double longtitude;

    public Store(String name, String category, double latitude, double longtitude){
        this.name = name;
        this.category = category;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }
}

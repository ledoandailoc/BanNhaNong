package com.example.uit.bannhanong.DTO.Preference;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

/**
 * Created by ledoa on 12/15/2015.
 */
public class AgriculturalPref implements Serializable{

    public int id;
    public String name;
    public double priceToDayDomestic;
    public double priceYesterdayDomestic;
    public double priceTodayInternational;
    public double priceYesterdayInternational;
    public String province;
    public String status;
    public String unit;

    public AgriculturalPref() {

    }

    public AgriculturalPref(JSONObject json) throws JSONException {
        if (json.has("id")) {
            this.id = json.getInt("id");
        }
        if (json.has("name")){
            this.name = json.getString("name");
        }
        if (json.has("priceToDayDomestic")) {
            this.priceToDayDomestic = json.getDouble("priceToDayDomestic");
        }
        if (json.has("priceYesterdayDomestic")) {
            this.priceYesterdayDomestic = json.getDouble("priceYesterdayDomestic");
        }
        if (json.has("priceTodayInternational")) {
            this.priceTodayInternational = json.getDouble("priceTodayInternational");
        }
        if (json.has("priceYesterdayInternational")) {
            this.priceYesterdayInternational = json.getDouble("priceYesterdayInternational");
        }
        if (json.has("province")){
            this.province = json.getString("province");
        }
        if (json.has("status")){
            this.status = json.getString("status");
        }
        if (json.has("unit")){
            this.unit = json.getString("unit");
        }
    }


}

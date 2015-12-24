package com.example.uit.bannhanong.DTO;

import com.example.uit.bannhanong.DTO.Statistic;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class Engineer implements Serializable {

    public String _id;
    public String username = "Nguyễn Văn Nông Nghiệp";
    public String phone = "0985735314";
    public String country;
    public String specialized = "Kĩ sư nông nghiệp";
    public String createdAt;
    public String avatar;
    public String token;
    public String place = "Đắc Lắk";
    public String message;
    public String latitude, longitude;

    public Engineer() {

    }

    public Engineer(JSONObject json) throws JSONException {
        if (json.has("latitude")) {
            this.latitude = json.getString("latitude");
        }
        if (json.has("longitude")) {
            this.longitude = json.getString("longitude");
        }
        if (json.has("message")) {
            this.message = json.getString("message");
        }
        if (json.has("username")) {
            this.username = json.getString("username");
        }
        if (json.has("createdAt")) {
            this.createdAt = json.getString("createdAt");
        }
        if (json.has("avatar")) {
            this.avatar = json.getString("avatar");
        }
        if (json.has("token")) {
            this.token = json.getString("token");
        }
        if (json.has("phone")) {
            this.phone = json.getString("phone");
        }
        if (json.has("country")) {
            this.country = json.getString("country");
        }
        if (json.has("place")) {
            this.place = json.getString("place");
        }
        if (json.has("specialized")) {
            this.specialized = json.getString("specialized");
        }
        if (json.has("_id")) {
            this._id = json.getString("_id");
        }
    }
}

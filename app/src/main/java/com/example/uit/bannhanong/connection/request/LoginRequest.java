package com.example.uit.bannhanong.connection.request;

import com.example.uit.bannhanong.DTO.User;
import com.example.uit.bannhanong.connection.base.BaseRequest;
import com.example.uit.bannhanong.connection.response.LoginResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public abstract class LoginRequest extends BaseRequest<LoginResponse> {

    public LoginRequest(String method, String url, HashMap<String, String> headers, HashMap<String, String> params) {
        super(method, url, headers, params);
    }

    @Override
    protected LoginResponse handleData(JSONObject json) throws JSONException {
        LoginResponse data = new LoginResponse();
        if (json.has("success")) {
            data.success = json.getBoolean("success");
        }
        if (json.has("statusCode")) {
            data.statusCode = json.getInt("statusCode");
        }
        if (json.has("message")) {
            data.message = json.getString("message");
        }
        if (data.success) {
            data.data = new User(json.getJSONObject("data"));
        }
        return data;
    }
}

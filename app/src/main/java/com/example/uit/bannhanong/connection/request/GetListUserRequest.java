package com.example.uit.bannhanong.connection.request;


import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.DTO.User;
import com.example.uit.bannhanong.connection.base.BaseRequest;
import com.example.uit.bannhanong.connection.response.ListUserResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Anhtuan_Uit on 9/15/2015.
 */
public abstract class GetListUserRequest extends BaseRequest<ListUserResponse> {
    public GetListUserRequest(String method, String url, HashMap<String, String> headers, HashMap<String, String> params) {
        super(method, url, headers, params);
    }

    @Override
    protected ListUserResponse handleData(JSONObject json) throws JSONException {

        ListUserResponse data = new ListUserResponse();
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
            data.data = new ArrayList<>();
            JSONArray jData = json.getJSONArray("data");
            for (int i = 0; i < jData.length(); i++) {
                data.data.add(new Engineer(jData.getJSONObject(i)));
            }
        }
        return data;
    }
}

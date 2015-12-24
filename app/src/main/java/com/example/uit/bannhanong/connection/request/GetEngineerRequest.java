package com.example.uit.bannhanong.connection.request;


import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.connection.base.BaseRequest;
import com.example.uit.bannhanong.connection.response.EngineerResponse;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Anhtuan_Uit on 9/15/2015.
 */
public abstract class GetEngineerRequest extends BaseRequest<EngineerResponse> {
    public GetEngineerRequest(String method, String url, HashMap<String, String> headers, HashMap<String, String> params) {
        super(method, url, headers, params);
    }

    @Override
    protected EngineerResponse handleData(JSONObject json) throws JSONException {

        EngineerResponse data = new EngineerResponse();
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
            data.data = new Engineer(json.getJSONObject("data"));
        }
        return data;
    }
}

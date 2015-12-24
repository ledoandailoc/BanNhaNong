package com.example.uit.bannhanong.connection.response;


import com.example.uit.bannhanong.DTO.Chat;
import com.example.uit.bannhanong.connection.base.BaseResponse;
import com.example.uit.bannhanong.utils.Constant;

import org.json.JSONException;
import org.json.JSONObject;

public class ChatResponse extends BaseResponse {

    public Chat data;

    public ChatResponse() {
        data = new Chat();
    }

    public ChatResponse(JSONObject json) throws JSONException {
        if (json.has("success")) {
            this.success = json.getBoolean("success");
        }
        if (json.has("statusCode")) {
            this.statusCode = json.getInt("statusCode");
        }
        if (json.has("message")) {
            this.message = json.getString("message");
        }
        if (this.success && this.statusCode == Constant.STATUS_CODE_SUCCESS) {
            JSONObject jo = json.getJSONObject("data");
            this.data = new Chat(jo);
        } else {
            this.data = new Chat();
        }
    }

    public ChatResponse(String jsonString) throws JSONException {
        this(new JSONObject(jsonString));
    }

}

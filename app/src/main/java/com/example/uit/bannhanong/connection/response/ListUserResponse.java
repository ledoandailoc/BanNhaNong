package com.example.uit.bannhanong.connection.response;

import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.connection.base.BaseResponse;

import java.util.ArrayList;
import java.util.List;

public class ListUserResponse extends BaseResponse {

    public List<Engineer> data = new ArrayList<>();

}

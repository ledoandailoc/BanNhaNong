package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.EngineerAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.connection.base.Method;
import com.example.uit.bannhanong.connection.request.GetListUserRequest;
import com.example.uit.bannhanong.connection.response.ListUserResponse;
import com.example.uit.bannhanong.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class EngineerHostFragment extends BaseMainFragment {


    public static EngineerHostFragment newInstance() {
        return new EngineerHostFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engineer_host, container, false);
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
    }

    @Override
    protected void initListener(View view) {
    }

    @Override
    protected void initData() {

    }

}
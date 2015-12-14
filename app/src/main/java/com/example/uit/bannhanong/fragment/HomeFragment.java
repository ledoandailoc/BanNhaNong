package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;

public class HomeFragment extends BaseMainFragment {

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
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
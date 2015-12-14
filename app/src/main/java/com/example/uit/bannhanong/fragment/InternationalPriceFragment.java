package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;

public class InternationalPriceFragment extends BaseMainFragment {


    public static InternationalPriceFragment newInstance() {
        return new InternationalPriceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_price_international, container, false);
        return v;
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
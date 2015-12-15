package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;

public class NewsFragment extends BaseMainFragment {

    ImageView mIvShowEngineerFragment;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news, container, false);
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
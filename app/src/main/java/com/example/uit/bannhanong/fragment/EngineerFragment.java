package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.listener.WorkshopListener;
import com.example.uit.bannhanong.utils.CommonUtils;

public class EngineerFragment extends BaseMainFragment {

    ImageView mIvShowSeminarFragment;
    WorkshopListener workshopListener;

    public static EngineerFragment newInstance() {
        return new EngineerFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engineer, container, false);
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        mIvShowSeminarFragment = (ImageView) CommonUtils.findViewById(view, R.id.iv_show_seminar_fragment);
    }

    @Override
    protected void initListener(View view) {
        mIvShowSeminarFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workshopListener.showSeminarFragment();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
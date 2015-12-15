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

public class SeminarFragment extends BaseMainFragment {

    ImageView mIvShowEngineerFragment;
    WorkshopListener workshopListener;

    public static SeminarFragment newInstance() {
        return new SeminarFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_seminar, container, false);
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        mIvShowEngineerFragment = (ImageView)CommonUtils.findViewById(view, R.id.iv_show_engineer_fragment);
        workshopListener = new WorkshopFragment();
    }

    @Override
    protected void initListener(View view) {
        mIvShowEngineerFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workshopListener.showEngineerFragment();
            }
        });
    }

    @Override
    protected void initData() {

    }
}
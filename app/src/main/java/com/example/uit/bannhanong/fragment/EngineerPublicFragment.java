package com.example.uit.bannhanong.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.caches.ImageLoaderUtil;
import com.example.uit.bannhanong.utils.CommonUtils;
import com.example.uit.bannhanong.view.CircleImageView;

public class EngineerPublicFragment extends BaseMainFragment {
    @Override
    protected boolean isViewBottomBarView() {
        return false;
    }

    RelativeLayout mRlPhoneNumber, rl_message;
    CircleImageView mCivAvatar;
    TextView mTvName, mTvPhoneNumber, mTvSpecialized, mTvCountry;

    Engineer engineer;

    public static EngineerPublicFragment newInstance() {
        return new EngineerPublicFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engineer_public, container, false);

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
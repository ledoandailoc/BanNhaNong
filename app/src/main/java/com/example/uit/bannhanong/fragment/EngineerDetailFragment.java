package com.example.uit.bannhanong.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

public class EngineerDetailFragment extends BaseMainFragment {
    @Override
    protected boolean isViewBottomBarView() {
        return false;
    }

    RelativeLayout mRlPhoneNumber, rl_message;

    public static EngineerDetailFragment newInstance() {
        return new EngineerDetailFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engineer_detail, container, false);
    }


    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        mRlPhoneNumber = CommonUtils.findViewById(view, R.id.rl_phone_number);
        rl_message = CommonUtils.findViewById(view, R.id.rl_message);
    }

    @Override
    protected void initListener(View view) {
        mRlPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:01659528808"));
                startActivity(callIntent);
            }
        });
        rl_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatFragment engineerDetailFragment = ChatFragment.newInstance();
                showFragmentWithClearStackMode(engineerDetailFragment);
            }
        });
    }

    @Override
    protected void initData() {

    }


}
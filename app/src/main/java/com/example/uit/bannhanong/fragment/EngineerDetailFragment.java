package com.example.uit.bannhanong.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

public class EngineerDetailFragment extends BaseMainFragment {

    @Override
    protected boolean isViewBottomBarView() {
        return false;
    }

    RelativeLayout mRlPhoneNumber, rl_message;
    CircleImageView mCivAvatar;
    TextView mTvName, mTvPhoneNumber, mTvSpecialized, mTvCountry;

    Engineer engineer;

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
        mCivAvatar = CommonUtils.findViewById(view, R.id.civ_avatar);
        mTvName = CommonUtils.findViewById(view, R.id.tv_name);
        mTvPhoneNumber = CommonUtils.findViewById(view, R.id.tv_phone_number);
        mTvCountry = CommonUtils.findViewById(view, R.id.tv_country);
        mTvSpecialized = CommonUtils.findViewById(view, R.id.tv_specialized);

        engineer = (Engineer) getArguments().getSerializable("1");
    }

    @Override
    protected void initListener(View view) {
        mRlPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + engineer.phone));
                startActivity(callIntent);
            }
        });
        rl_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChatFragment engineerDetailFragment = ChatFragment.newInstance();
                Bundle bundle =new Bundle(); bundle.putString("2", engineer._id);
                engineerDetailFragment.setArguments(bundle);
                showFragmentWithClearStackMode(engineerDetailFragment);
            }
        });
    }

    @Override
    protected void initData() {
        ImageLoaderUtil.display(engineer.avatar, mCivAvatar);
        mTvName.setText(engineer.username);
        mTvCountry.setText(engineer.country);
        mTvPhoneNumber.setText(engineer.phone);
        mTvSpecialized.setText(engineer.specialized);
    }


}
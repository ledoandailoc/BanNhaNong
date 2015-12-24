package com.example.uit.bannhanong.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.view.ViewCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.example.uit.bannhanong.DTO.Agricultural;

import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.MainApplication;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseFragmentActivity;
import com.example.uit.bannhanong.fragment.EngineerHostFragment;
import com.example.uit.bannhanong.fragment.EngineerPublicFragment;
import com.example.uit.bannhanong.fragment.HomeFragment;
import com.example.uit.bannhanong.fragment.LocationFragment;
import com.example.uit.bannhanong.fragment.WorkshopFragment;

import com.example.uit.bannhanong.listener.MainListener;
import com.example.uit.bannhanong.utils.AnimationUtils;

import com.example.uit.bannhanong.socketio.MySocket;

import com.example.uit.bannhanong.utils.CommonUtils;
import com.example.uit.bannhanong.utils.UserPref;
import com.example.uit.bannhanong.view.BottomMenuView;

import java.util.ArrayList;

public class MainActivity extends BaseFragmentActivity implements
        View.OnClickListener, BottomMenuView.BottomMenuListener, MainListener{

    private View mBottomView;
    private RelativeLayout mRlTabPrice, mRlTabMarket, mRlTabWorkshop, mRlTabMap, mRlBg;
    private ImageView mIvTabPrice, mIvTabMarket, mIvTabWorkshop, mIvTabMap, mIvLogout;
    private TextView mTvTabPrice, mTvTabMarket, mTvTabWorkshop, mTvTabMap;
    private BottomMenuView mBottomMenuView;
    private View mMenuBtn;
    private boolean mIsViewBottomMenu = false;

    private ImageView ivMenuVitualFence, ivMenuVitualLeash;

    @Override
    protected Fragment onCreateMainFragment(Bundle savedInstanceState) {
        return HomeFragment.newInstance();
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.main_container;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle bundle = getIntent().getExtras();
        MainApplication.setMySocket(new MySocket(this));
        MainApplication.getMySocket().connectSocket();
        attachTab();
    }

    private void attachTab() {
        mBottomView = CommonUtils.findViewById(this, R.id.main_bottom_tab_container);
        mBottomMenuView = CommonUtils.findViewById(this, R.id.main_bottom_menu_view);
        mMenuBtn = CommonUtils.findViewById(this, R.id.main_menu_btn);

        mRlTabPrice = CommonUtils.findViewById(this, R.id.main_home_tab);
        mRlTabMarket = CommonUtils.findViewById(this, R.id.main_activity_tab);
        mRlTabWorkshop = CommonUtils.findViewById(this, R.id.main_training_tab);
        mRlTabMap = CommonUtils.findViewById(this, R.id.main_map_tab);
        mRlBg = CommonUtils.findViewById(this, R.id.rl_bg);

        mIvTabPrice = CommonUtils.findViewById(this, R.id.tab_iv_home);
        mIvTabMarket = CommonUtils.findViewById(this, R.id.tab_iv_activity);
        mIvTabWorkshop = CommonUtils.findViewById(this, R.id.tab_iv_training);
        mIvTabMap = CommonUtils.findViewById(this, R.id.tab_iv_map);
        mIvLogout = CommonUtils.findViewById(this, R.id.iv_logout);

        mTvTabPrice = CommonUtils.findViewById(this, R.id.tab_tv_home);
        mTvTabMarket = CommonUtils.findViewById(this, R.id.tab_tv_activity);
        mTvTabWorkshop = CommonUtils.findViewById(this, R.id.tab_tv_training);
        mTvTabMap = CommonUtils.findViewById(this, R.id.tab_tv_map);

        ivMenuVitualFence = CommonUtils.findViewById(this, R.id.menu_virtual_fence_iv);
        ivMenuVitualLeash = CommonUtils.findViewById(this, R.id.menu_virtual_leash_iv);

        mIvTabPrice.setImageResource(R.drawable.icon_tab_price_active);
        mTvTabPrice.setTextColor(getResources().getColor(R.color.tab_text_active_color));
        mTvTabPrice.setTypeface(null, Typeface.BOLD);
        HomeFragment homeFragment = HomeFragment.newInstance();
        showFragmentWithClearStackMode(homeFragment);

        mRlTabPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mIvTabPrice.setImageResource(R.drawable.icon_tab_price_active);
                mTvTabPrice.setTextColor(getResources().getColor(R.color.tab_text_active_color));
                mTvTabPrice.setTypeface(null, Typeface.BOLD);
                HomeFragment homeFragment = HomeFragment.newInstance();
                showFragmentWithClearStackMode(homeFragment);
            }
        });
        mRlTabMarket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mIvTabMarket.setImageResource(R.drawable.icon_tab_market_active);
                mTvTabMarket.setTextColor(getResources().getColor(R.color.tab_text_active_color));
                mTvTabMarket.setTypeface(null, Typeface.BOLD);
                WorkshopFragment workshopFragment = WorkshopFragment.newInstance();
                showFragmentWithClearStackMode(workshopFragment);
            }
        });
        mRlTabWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mIvTabWorkshop.setImageResource(R.drawable.icon_tab_workshop_active);
                mTvTabWorkshop.setTextColor(getResources().getColor(R.color.tab_text_active_color));
                mTvTabWorkshop.setTypeface(null, Typeface.BOLD);
                WorkshopFragment workshopFragment = WorkshopFragment.newInstance();
                showFragmentWithClearStackMode(workshopFragment);
            }
        });
        mRlTabMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mIvTabMap.setImageResource(R.drawable.icon_tab_map_active);
                mTvTabMap.setTextColor(getResources().getColor(R.color.tab_text_active_color));
                mTvTabMap.setTypeface(null, Typeface.BOLD);
                LocationFragment locationFragment = LocationFragment.newInstance();
                showFragmentWithClearStackMode(locationFragment);
            }
        });

        mMenuBtn.setOnClickListener(this);
        ivMenuVitualFence.setOnClickListener(this);
        mBottomMenuView.setBottomMenuListener(this);
        mIvLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserPref userPref = new UserPref();
                userPref.setUser(null);
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        ivMenuVitualLeash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mBottomMenuView.setVisibility(View.GONE);
                EngineerHostFragment host = new EngineerHostFragment();
                showFragmentWithClearStackMode(host);
            }
        });



    }


    private void unSelectAllTab() {

        mIvTabPrice.setImageResource(R.drawable.icon_tab_price);
        mTvTabPrice.setTextColor(getResources().getColor(R.color.tab_text_color));
        mTvTabPrice.setTypeface(null, Typeface.NORMAL);
        mIvTabMarket.setImageResource(R.drawable.icon_tab_market);
        mTvTabMarket.setTextColor(getResources().getColor(R.color.tab_text_color));
        mTvTabMarket.setTypeface(null, Typeface.NORMAL);
        mIvTabWorkshop.setImageResource(R.drawable.icon_tab_workshop);
        mTvTabWorkshop.setTextColor(getResources().getColor(R.color.tab_text_color));
        mTvTabWorkshop.setTypeface(null, Typeface.NORMAL);
        mIvTabMap.setImageResource(R.drawable.icon_tab_map);
        mTvTabMap.setTextColor(getResources().getColor(R.color.tab_text_color));
        mTvTabMap.setTypeface(null, Typeface.NORMAL);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_menu_btn:
                if (!mIsViewBottomMenu) {
                    ViewCompat.animate(this.mBottomMenuView).translationY(0.0F).
                            setInterpolator(AnimationUtils.DECELERATE_CUBIC_INTERPOLATOR)
                           .setDuration(400L).setStartDelay(100).start();
                    this.mBottomMenuView.setVisibility(View.VISIBLE);
                } else {

                }
                mIsViewBottomMenu = !mIsViewBottomMenu;
                break;
            case R.id.menu_virtual_fence_iv:
                this.mBottomMenuView.setVisibility(View.GONE);
                EngineerPublicFragment engineerPublicFragment = new EngineerPublicFragment();
                showFragmentWithClearStackMode(engineerPublicFragment);
            default:
                break;
        }
    }

    @Override
    public void onBottomMenuHidden() {
        ViewCompat.animate(this.mBottomMenuView).translationY(mBottomMenuView.getHeight()).
                setInterpolator(AnimationUtils.DECELERATE_CUBIC_INTERPOLATOR)
                .setDuration(400L).setStartDelay(100).start();
        mIsViewBottomMenu = !mIsViewBottomMenu;
        this.mBottomMenuView.setVisibility(View.GONE);
    }

    @Override
    public void setViewBottomBar(boolean needToView) {
        if (needToView) {
            mBottomView.setVisibility(View.VISIBLE);
            mMenuBtn.setVisibility(View.VISIBLE);
        } else {
            mBottomView.setVisibility(View.GONE);
            mMenuBtn.setVisibility(View.GONE);
        }
        }

}

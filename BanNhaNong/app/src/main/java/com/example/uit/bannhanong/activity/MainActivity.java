package com.example.uit.bannhanong.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseFragmentActivity;
import com.example.uit.bannhanong.fragment.HomeFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

public class MainActivity extends BaseFragmentActivity {

    private RelativeLayout mRlTabPrice, mRlTabMarket, mRlTabWorkshop, mRlTabMap;
    private ImageView mIvTabPrice, mIvTabMarket, mIvTabWorkshop, mIvTabMap;
    private TextView mTvTabPrice, mTvTabMarket, mTvTabWorkshop, mTvTabMap;

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

        attachTab();
    }

    private void attachTab() {

        mRlTabPrice = CommonUtils.findViewById(this, R.id.main_home_tab);
        mRlTabMarket = CommonUtils.findViewById(this, R.id.main_activity_tab);
        mRlTabWorkshop = CommonUtils.findViewById(this, R.id.main_training_tab);
        mRlTabMap = CommonUtils.findViewById(this, R.id.main_map_tab);

        mIvTabPrice = CommonUtils.findViewById(this, R.id.tab_iv_home);
        mIvTabMarket = CommonUtils.findViewById(this, R.id.tab_iv_activity);
        mIvTabWorkshop = CommonUtils.findViewById(this, R.id.tab_iv_training);
        mIvTabMap = CommonUtils.findViewById(this, R.id.tab_iv_map);

        mTvTabPrice = CommonUtils.findViewById(this, R.id.tab_tv_home);
        mTvTabMarket = CommonUtils.findViewById(this, R.id.tab_tv_activity);
        mTvTabWorkshop = CommonUtils.findViewById(this, R.id.tab_tv_training);
        mTvTabMap = CommonUtils.findViewById(this, R.id.tab_tv_map);

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
                HomeFragment homeFragment = HomeFragment.newInstance();
                showFragmentWithClearStackMode(homeFragment);
            }
        });
        mRlTabWorkshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mIvTabWorkshop.setImageResource(R.drawable.icon_tab_workshop_active);
                mTvTabWorkshop.setTextColor(getResources().getColor(R.color.tab_text_active_color));
                mTvTabWorkshop.setTypeface(null, Typeface.BOLD);
                HomeFragment homeFragment = HomeFragment.newInstance();
                showFragmentWithClearStackMode(homeFragment);
            }
        });
        mRlTabMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mIvTabMap.setImageResource(R.drawable.icon_tab_map_active);
                mTvTabMap.setTextColor(getResources().getColor(R.color.tab_text_active_color));
                mTvTabMap.setTypeface(null, Typeface.BOLD);
                HomeFragment homeFragment = HomeFragment.newInstance();
                showFragmentWithClearStackMode(homeFragment);
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

}

package com.example.uit.bannhanong.fragment;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

import static android.content.res.Resources.*;

public class HomeFragment extends BaseMainFragment {

    private RelativeLayout mRlTabDomestic, mRlTabInternational;
    private TextView mTvTabDomestic, mTvTabInternational;
    private LinearLayout mLnTabDomestic, mLnTabInternational;
    
    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        return v;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        attachTab(view);
    }

    @Override
    protected void initListener(View view) {

    }

    @Override
    protected void initData() {

    }

    private void attachTab(View v) {

        mRlTabDomestic = CommonUtils.findViewById(v, R.id.rl_tab_domestic);
        mRlTabInternational = CommonUtils.findViewById(v, R.id.rl_tab_international);

        mTvTabDomestic = CommonUtils.findViewById(v, R.id.tv_tab_market_domestic);
        mTvTabInternational = CommonUtils.findViewById(v, R.id.tv_tab_market_international);

        mLnTabDomestic = CommonUtils.findViewById(v, R.id.tab_domestic_divider);
        mLnTabInternational = CommonUtils.findViewById(v, R.id.tab_international_divider);

        DomesticPriceFragment domesticPriceFragment = new DomesticPriceFragment().newInstance();
        showFragmentWithNewContainer(domesticPriceFragment,R.id.price_container);

        mLnTabDomestic.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color_active));

        mRlTabDomestic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mLnTabDomestic.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color_active));
                DomesticPriceFragment domesticPriceFragment = new DomesticPriceFragment().newInstance();
                showFragmentWithNewContainer(domesticPriceFragment, R.id.price_container);
            }
        });
        mRlTabInternational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mLnTabInternational.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color_active));
                InternationalPriceFragment internationalPriceFragment = new InternationalPriceFragment().newInstance();
                showFragmentWithNewContainer(internationalPriceFragment, R.id.price_container);
            }
        });

    }


    private void unSelectAllTab() {
        mLnTabDomestic.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color));
        mLnTabInternational.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color));
    }
}
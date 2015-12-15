package com.example.uit.bannhanong.fragment;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.AgriculturePricePagerAdapter;
import com.example.uit.bannhanong.adapter.WorkshopPagerAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

import static android.content.res.Resources.*;

public class HomeFragment extends BaseMainFragment {

    private RelativeLayout mRlTabDomestic, mRlTabInternational;
    private TextView mTvTabDomestic, mTvTabInternational;
    private LinearLayout mLnTabDomestic, mLnTabInternational;

    AgriculturePricePagerAdapter mPagerAdapter;
    ViewPager viewPager;


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
        viewPager = CommonUtils.findViewById(view, R.id.vp_agricultural_price);
        attachTab(view);
    }

    @Override
    protected void initListener(View view) {

    }

    @Override
    protected void initData() {
        mPagerAdapter = new AgriculturePricePagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    private void attachTab(View v) {

        mRlTabDomestic = CommonUtils.findViewById(v, R.id.rl_tab_domestic);
        mRlTabInternational = CommonUtils.findViewById(v, R.id.rl_tab_international);

        mTvTabDomestic = CommonUtils.findViewById(v, R.id.tv_tab_market_domestic);
        mTvTabInternational = CommonUtils.findViewById(v, R.id.tv_tab_market_international);

        mLnTabDomestic = CommonUtils.findViewById(v, R.id.tab_domestic_divider);
        mLnTabInternational = CommonUtils.findViewById(v, R.id.tab_international_divider);

        mLnTabDomestic.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color_active));

        mRlTabDomestic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mLnTabDomestic.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color_active));
                viewPager.setCurrentItem(0);
            }
        });
        mRlTabInternational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mLnTabInternational.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color_active));
                viewPager.setCurrentItem(1);
            }
        });

    }


    private void unSelectAllTab() {
        mLnTabDomestic.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color));
        mLnTabInternational.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color));
    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();
            if (position < -1) {
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else {
                view.setAlpha(0);
            }
        }
    }
}
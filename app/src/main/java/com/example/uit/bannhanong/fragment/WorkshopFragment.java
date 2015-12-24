package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.WorkshopPagerAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.AnimationUtils;
import com.example.uit.bannhanong.utils.CommonUtils;
import com.example.uit.bannhanong.view.SearchView;

public class WorkshopFragment extends BaseMainFragment implements SearchView.BottomMenuListener, View.OnClickListener{

    ViewPager viewPager;
    WorkshopPagerAdapter mPagerAdapter;

    private RelativeLayout mRlTabSeminar, mRlTabEngineer, mRlTabNews;
    private LinearLayout mLnTabSeminar, mLnTabEngineer, mLnTabNews;
     ImageView mIvSearch;

    private boolean mIsViewBottomMenu = false;
    private SearchView mSearchView;

    public static WorkshopFragment newInstance() {
        return new WorkshopFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_workshop, container, false);
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        viewPager = CommonUtils.findViewById(view, R.id.vp_workshop);
        mIvSearch = CommonUtils.findViewById(view, R.id.actionbar_iv_search);
    }

    @Override
    protected void initListener(View view) {
        attachTab(view);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    protected void initData() {
        mPagerAdapter = new WorkshopPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(mPagerAdapter);
        viewPager.setPageTransformer(true, new ZoomOutPageTransformer());

        selectTab(1);
        viewPager.setCurrentItem(1);
    }

    private void attachTab (View v){
        mSearchView = CommonUtils.findViewById(v, R.id.searchView);
        mIvSearch.setOnClickListener(this);
        mSearchView.setBottomMenuListener(this);


        mRlTabSeminar = CommonUtils.findViewById(v, R.id.rl_tab_seminar);
        mRlTabEngineer = CommonUtils.findViewById(v, R.id.rl_tab_engineer);
        mRlTabNews = CommonUtils.findViewById(v, R.id.rl_tab_news);

        mLnTabSeminar = CommonUtils.findViewById(v, R.id.tab_seminar_divider);
        mLnTabEngineer = CommonUtils.findViewById(v, R.id.tab_engineer_divider);
        mLnTabNews = CommonUtils.findViewById(v, R.id.tab_news_divider);

        mRlTabSeminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(0);
                viewPager.setCurrentItem(0);
            }
        });

        mRlTabEngineer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(1);
                viewPager.setCurrentItem(1);
            }
        });

        mRlTabNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(2);
                viewPager.setCurrentItem(2);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.actionbar_iv_search:
                if (!mIsViewBottomMenu) {
                    ViewCompat.animate(this.mSearchView).translationY(0.0F).
                            setInterpolator(AnimationUtils.DECELERATE_CUBIC_INTERPOLATOR)
                            .setDuration(800L).setStartDelay(100).start();
                    this.mSearchView.setVisibility(View.VISIBLE);
                } else {

                }
                mIsViewBottomMenu = !mIsViewBottomMenu;
                break;
            default:
                break;
        }
    }

    @Override
    public void onBottomMenuHidden() {
        ViewCompat.animate(this.mSearchView).translationY(mSearchView.getHeight()).
                setInterpolator(AnimationUtils.DECELERATE_CUBIC_INTERPOLATOR)
                .setDuration(400L).setStartDelay(100).start();
        mIsViewBottomMenu = !mIsViewBottomMenu;
        this.mSearchView.setVisibility(View.GONE);
    }

    private void unSelectAllTab() {
        mLnTabSeminar.setBackgroundColor(getResources().getColor(R.color.tab_workshop_divider_color));
        mLnTabEngineer.setBackgroundColor(getResources().getColor(R.color.tab_workshop_divider_color));
        mLnTabNews.setBackgroundColor(getResources().getColor(R.color.tab_workshop_divider_color));
    }

    private void selectTab(int position){
        unSelectAllTab();
        if(position == 1){
            mLnTabEngineer.setBackgroundColor(getResources().getColor(R.color.tab_workshop_divider_color_active));
        } else
        if(position == 2){
            mLnTabNews.setBackgroundColor(getResources().getColor(R.color.tab_workshop_divider_color_active));
        } else mLnTabSeminar.setBackgroundColor(getResources().getColor(R.color.tab_workshop_divider_color_active));
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
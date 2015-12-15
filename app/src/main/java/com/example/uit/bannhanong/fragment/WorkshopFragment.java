package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.WorkshopPagerAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.listener.WorkshopListener;
import com.example.uit.bannhanong.utils.CommonUtils;

public class WorkshopFragment extends BaseMainFragment implements WorkshopListener{

    ViewPager pager;
    WorkshopPagerAdapter mPagerAdapter;
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
        pager = (ViewPager) CommonUtils.findViewById(view, R.id.viewPager);
    }

    @Override
    protected void initListener(View view) {

    }

    @Override
    protected void initData() {

        mPagerAdapter = new WorkshopPagerAdapter(getChildFragmentManager());
        pager.setAdapter(mPagerAdapter);
        pager.setPageTransformer(true, new ZoomOutPageTransformer());
    }

    @Override
    public void showEngineerFragment() {
    }

    @Override
    public void showSeminarFragment() {

    }

    @Override
    public void showSearchEngineer() {

    }

    public class ZoomOutPageTransformer implements ViewPager.PageTransformer {
        private static final float MIN_SCALE = 0.85f;
        private static final float MIN_ALPHA = 0.5f;

        @Override
        public void transformPage(View view, float position) {
            int pageWidth = view.getWidth();
            int pageHeight = view.getHeight();

            if (position < -1) { // [-Infinity,-1)
                // This page is way off-screen to the left.
                view.setAlpha(0);

            } else if (position <= 1) { // [-1,1]
                // Modify the default slide transition to shrink the page as well
                float scaleFactor = Math.max(MIN_SCALE, 1 - Math.abs(position));
                float vertMargin = pageHeight * (1 - scaleFactor) / 2;
                float horzMargin = pageWidth * (1 - scaleFactor) / 2;
                if (position < 0) {
                    view.setTranslationX(horzMargin - vertMargin / 2);
                } else {
                    view.setTranslationX(-horzMargin + vertMargin / 2);
                }

                // Scale the page down (between MIN_SCALE and 1)
                view.setScaleX(scaleFactor);
                view.setScaleY(scaleFactor);

                // Fade the page relative to its size.
                view.setAlpha(MIN_ALPHA +
                        (scaleFactor - MIN_SCALE) /
                                (1 - MIN_SCALE) * (1 - MIN_ALPHA));

            } else { // (1,+Infinity]
                // This page is way off-screen to the right.
                view.setAlpha(0);
            }
        }
    }
}
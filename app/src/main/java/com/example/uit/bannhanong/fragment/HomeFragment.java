package com.example.uit.bannhanong.fragment;

import android.app.Dialog;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.uit.bannhanong.DTO.Agricultural;
import com.example.uit.bannhanong.DTO.Preference.AgriculturalPref;
import com.example.uit.bannhanong.DTO.Preference.ListAgriculturalPref;
import com.example.uit.bannhanong.DTO.Preference.ListSearchAgriculturalPref;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.AgriculturalPriceAdapter;
import com.example.uit.bannhanong.adapter.AgriculturePricePagerAdapter;
import com.example.uit.bannhanong.adapter.WorkshopPagerAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

import static android.content.res.Resources.*;

public class HomeFragment extends BaseMainFragment {

    private RelativeLayout mRlTabDomestic, mRlTabInternational;
    private TextView mTvTabDomestic, mTvTabInternational;
    private LinearLayout mLnTabDomestic, mLnTabInternational;



    AgriculturePricePagerAdapter mPagerAdapter;
    ViewPager viewPager;
    ListAgriculturalPref listAgriculturalPref = new ListAgriculturalPref();
    ListSearchAgriculturalPref listSearchAgriculturalPref = new ListSearchAgriculturalPref();


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
        return "Giá cả nông sản";
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



    /*public void attackSearchView(){

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filter(newText.toString().toLowerCase(Locale.getDefault()));
                Toast.makeText(getActivity(), newText.toString().toLowerCase(Locale.getDefault()), Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }

    public void filter(String charSearching) {
        mAgricultualListSearching = new ArrayList<>();
        charSearching = charSearching.toLowerCase(Locale.getDefault());
        mAgricultualListSearching.clear();
        if (charSearching.length() == 0) {
            this.mAgricultualListSearching.addAll(listAgricultual);
        } else {
            for (int i = 0; i < listAgricultual.size(); i++) {
                if ((listAgricultual.get(i)).getName().toLowerCase(Locale.getDefault()).contains(charSearching)) {
                    mAgricultualListSearching.add(listAgricultual.get(i));
                }
            }
        }

        AgriculturalPriceAdapter adapter = new AgriculturalPriceAdapter(getActivity(), R.layout.item_price_agricultural, mAgricultualListSearching);
        mLvAgricultural.setAdapter(adapter);
    }


            public ArrayList<String> getProvinceList(ArrayList<AgriculturalPref> agriculturals) {
                ArrayList<String> provinceList = new ArrayList<>();
                provinceList.add("Tất cả các tỉnh");
                for (AgriculturalPref agricultural : agriculturals) {
                    if (!provinceList.contains(agricultural.province))
                        provinceList.add(agricultural.province);

                }
                return provinceList;
            }

            public ArrayList<AgriculturalPref> getAgriculturalListByProvince(ArrayList<AgriculturalPref> list, String province) {
                ArrayList<AgriculturalPref> listAgricultural = new ArrayList<>();
                for (AgriculturalPref agricultural : list) {
                    if (agricultural.province.equals(province)) listAgricultural.add(agricultural);
                }
                return listAgricultural;
            }
        */

    private void attachTab(View v) {

        mRlTabDomestic = CommonUtils.findViewById(v, R.id.rl_tab_domestic);
        mRlTabInternational = CommonUtils.findViewById(v, R.id.rl_tab_international);

        mTvTabDomestic = CommonUtils.findViewById(v, R.id.tv_tab_market_domestic);
        mTvTabInternational = CommonUtils.findViewById(v, R.id.tv_tab_market_international);

        mLnTabDomestic = CommonUtils.findViewById(v, R.id.tab_domestic_divider);
        mLnTabInternational = CommonUtils.findViewById(v, R.id.tab_international_divider);

        mLnTabDomestic.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color_active));
        mTvTabDomestic.setTextColor(getResources().getColor(R.color.tab_market_title_text_color_active));


        mRlTabDomestic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(0);
                viewPager.setCurrentItem(0);
            }
        });
        mRlTabInternational.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectTab(1);
                viewPager.setCurrentItem(1);
            }
        });

    }


    private void unSelectAllTab() {
        mLnTabDomestic.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color));
        mLnTabInternational.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color));
        mTvTabDomestic.setTextColor(getResources().getColor(R.color.tab_market_title_text_color));
        mTvTabInternational.setTextColor(getResources().getColor(R.color.tab_market_title_text_color));
    }

    private void selectTab(int position){
        unSelectAllTab();
        if(position == 0){
            mLnTabDomestic.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color_active));
            mTvTabDomestic.setTextColor(getResources().getColor(R.color.tab_market_title_text_color_active));
        }
        else if(position == 1){
            mLnTabInternational.setBackgroundColor(getResources().getColor(R.color.tab_market_divider_color_active));
            mTvTabInternational.setTextColor(getResources().getColor(R.color.tab_market_title_text_color_active));
        }
    }

    class ZoomOutPageTransformer implements ViewPager.PageTransformer {
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
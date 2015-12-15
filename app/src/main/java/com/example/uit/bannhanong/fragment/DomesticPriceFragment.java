package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.uit.bannhanong.DTO.Agricultural;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.AgriculturalPriceAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class DomesticPriceFragment extends BaseMainFragment {

    SearchView searchView;
    private ListView mLvAgricultural;
    ArrayList<Agricultural> listAgricultual = new ArrayList<>();
    String[] name = {"Cà phê","Cao su","Điều","Tiêu", "Lúa"};
    int[] priceDo = {70000,100000,30000,200000,8000};
    int[] priceIn = {12,34,53,34,55};
    String[] unit = {"1kg","1kg","1kg","1kg", "1kg"};
    String[] status = {"increase","increase","decrease","increase", "decrease"};
    public static DomesticPriceFragment newInstance() {
        return new DomesticPriceFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_price_domestic, container, false);
        return v;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        mLvAgricultural = CommonUtils.findViewById(view, R.id.lv_agricultural);
    }

    @Override
    protected void initListener(View view) {

    }

    @Override
    protected void initData() {

        // Test

        for (int n = 0; n < name.length; n++)
        {
            Agricultural agricultural = new Agricultural();
            agricultural.setName(name[n]);
            agricultural.setPriceDomestic(priceDo[n]);
            agricultural.setPriceInternational(priceIn[n]);
            agricultural.setUnit(unit[n]);
            agricultural.setStatus(status[n]);

            listAgricultual.add(agricultural);
        }

        // end test

        AgriculturalPriceAdapter adapter = new AgriculturalPriceAdapter(getContext(), R.layout.item_price_agricultural, listAgricultual);
        mLvAgricultural.setAdapter(adapter);
    }


}
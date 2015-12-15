package com.example.uit.bannhanong.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.uit.bannhanong.fragment.DomesticPriceFragment;
import com.example.uit.bannhanong.fragment.EngineerFragment;
import com.example.uit.bannhanong.fragment.InternationalPriceFragment;
import com.example.uit.bannhanong.fragment.NewsFragment;
import com.example.uit.bannhanong.fragment.SeminarFragment;

public class AgriculturePricePagerAdapter extends FragmentStatePagerAdapter {


    public AgriculturePricePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0: return DomesticPriceFragment.newInstance();
            case 1: return InternationalPriceFragment.newInstance();
            default: return DomesticPriceFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}

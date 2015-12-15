package com.example.uit.bannhanong.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.uit.bannhanong.fragment.EngineerFragment;
import com.example.uit.bannhanong.fragment.HomeFragment;
import com.example.uit.bannhanong.fragment.NewsFragment;
import com.example.uit.bannhanong.fragment.SeminarFragment;

public class WorkshopPagerAdapter extends FragmentStatePagerAdapter {


    public WorkshopPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int pos) {
        switch(pos) {
            case 0: return SeminarFragment.newInstance();
            case 1: return EngineerFragment.newInstance();
            case 2: return NewsFragment.newInstance();
            default: return SeminarFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}

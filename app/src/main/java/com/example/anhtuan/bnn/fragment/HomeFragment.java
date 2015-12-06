package com.example.anhtuan.bnn.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.anhtuan.bnn.R;
import com.example.anhtuan.bnn.base.BaseMainFragment;


public class HomeFragment extends BaseMainFragment implements View.OnClickListener {


    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {

    }

    @Override
    protected void initListener(View view) {

    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {

    }
}

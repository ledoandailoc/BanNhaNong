package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EngineerHostFragment extends BaseMainFragment {

    @Override
    protected boolean isViewBottomBarView() {
        return false;
    }

    ImageView mIvBack;

    public static EngineerHostFragment newInstance() {
        return new EngineerHostFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engineer_host, container, false);
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        TextView title = CommonUtils.findViewById(view, R.id.title);
        title.setText("Lịch sử thoại");

        mIvBack = CommonUtils.findViewById(view, R.id.iv_back);
    }

    @Override
    protected void initListener(View view) {
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkshopFragment workshopFragment = new WorkshopFragment();
                showFragmentWithClearStackMode(workshopFragment);
            }
        });
    }

    @Override
    protected void initData() {

    }

}
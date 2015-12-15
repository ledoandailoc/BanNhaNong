package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.EngineerAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class EngineerFragment extends BaseMainFragment {

    private ListView mLvEngineer;
    List<Engineer> listEngineer;


    public static EngineerFragment newInstance() {
        return new EngineerFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engineer, container, false);
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        mLvEngineer = CommonUtils.findViewById(view, R.id.lv_engineer);
    }

    @Override
    protected void initListener(View view) {
    }

    @Override
    protected void initData() {
        listEngineer = new ArrayList<Engineer>();
        Engineer engineer = new Engineer();
        listEngineer.add(engineer);
        listEngineer.add(engineer);
        listEngineer.add(engineer);
        listEngineer.add(engineer);
        listEngineer.add(engineer);
        listEngineer.add(engineer);
        listEngineer.add(engineer);
        listEngineer.add(engineer);
        listEngineer.add(engineer);


        EngineerAdapter engineerAdapter = new EngineerAdapter(getActivity(), R.layout.item_engineer, listEngineer);
        mLvEngineer.setAdapter(engineerAdapter);
    }
}
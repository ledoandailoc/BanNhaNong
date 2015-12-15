package com.example.uit.bannhanong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.utils.CommonUtils;

public class EngineerAdapter extends ArrayAdapter<Engineer> {
    private TextView mTvName, mTvContact, mTvTime, mTvType;
    private ImageView mIvAvatar;

    Context context;
    int resource;
    List<Engineer> list;

    public EngineerAdapter(Context context, int resource, List<Engineer> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @Override
    public View getView(int vitri, View v, ViewGroup viewGroup){
        View view = View.inflate(context, resource, null);

        mTvName = CommonUtils.findViewById(view, R.id.tv_name_engineer);
        mTvContact = CommonUtils.findViewById(view, R.id.tv_contact_engineer);
        mTvTime = CommonUtils.findViewById(view, R.id.tv_time_engineer);
        mTvType = CommonUtils.findViewById(view, R.id.tv_type_engineer);

        Engineer medication = list.get(vitri);

        return view;
    }

}

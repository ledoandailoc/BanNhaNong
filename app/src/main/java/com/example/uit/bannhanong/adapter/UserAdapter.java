package com.example.uit.bannhanong.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.caches.ImageLoaderUtil;
import com.example.uit.bannhanong.utils.CommonUtils;

import java.util.List;

public class UserAdapter extends ArrayAdapter<Engineer> {
    private TextView mTvName, mTvPlace, mTvTime, mTvSpecialized;
    private ImageView mIvAvatar;

    Context context;
    int resource;
    List<Engineer> list;

    public UserAdapter(Context context, int resource, List<Engineer> objects) {
        super(context, resource, objects);

        this.context = context;
        this.resource = resource;
        this.list = objects;
    }

    @Override
    public View getView(int vitri, View v, ViewGroup viewGroup){
        View view = View.inflate(context, resource, null);
        Engineer engineer = list.get(vitri);
        mTvName = CommonUtils.findViewById(view, R.id.tv_name_engineer);
        mTvTime = CommonUtils.findViewById(view, R.id.tv_time_engineer);
        mIvAvatar = CommonUtils.findViewById(view, R.id.iv_avatar_engineer);

        if(list.get(vitri).avatar != null) {
            ImageLoaderUtil.display(engineer.avatar, mIvAvatar);
        }
        mTvName.setText(engineer.username);
        mTvTime.setText("Tin cũ: " + engineer.message);

        return view;
    }

}

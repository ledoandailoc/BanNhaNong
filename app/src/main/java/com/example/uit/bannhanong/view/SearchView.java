package com.example.uit.bannhanong.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.utils.CommonUtils;

public class SearchView extends RelativeLayout {


    private TextView mOutBtn;
    private RelativeLayout mRlBackground;
    private BottomMenuListener mCallBack;

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.search_view, null);
        mOutBtn = CommonUtils.findViewById(view, R.id.tv_back);

        mOutBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onBottomMenuHidden();
            }
        });

        LayoutParams param = new
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        addView(view, param);
    }

    public void setBottomMenuListener(BottomMenuListener callback) {
        this.mCallBack = callback;
    }

    public SearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public SearchView(Context context) {
        super(context);
    }

    public interface BottomMenuListener {
        void onBottomMenuHidden();
    }
}

package com.example.uit.bannhanong.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.utils.CommonUtils;


public class MainRightMenuView extends RelativeLayout {
    private RelativeLayout mRlTabCollar;
    private RelativeLayout mRlTabDog;
    private RelativeLayout mRlTabOwner;
    private TextView mTvTabOwner;
    private TextView mTvTabCollar;
    private TextView mTvTabDog;


    public MainRightMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.profile_view, null);

        initViews(view);
        attachTab();

        LayoutParams param = new
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        addView(view, param);
    }


    public MainRightMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public MainRightMenuView(Context context) {
        super(context);
    }

    public interface BottomMenuListener {
        void onHidden();
    }

    protected void initViews(View view) {
        mRlTabOwner = CommonUtils.findViewById(view, R.id.tab_owner_rl);
        mRlTabDog = CommonUtils.findViewById(view, R.id.tab_dog_rl);
        mRlTabCollar = CommonUtils.findViewById(view, R.id.tab_collar_rl);
        mTvTabOwner = CommonUtils.findViewById(view, R.id.tab_owner_tv);
        mTvTabDog = CommonUtils.findViewById(view, R.id.tab_dog_tv);
        mTvTabCollar = CommonUtils.findViewById(view, R.id.tab_collar_tv);

    }

    public void attachTab(){

        mTvTabOwner.setTextColor(getResources().getColor(R.color.tab_record_text_active_color));


        mRlTabOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mTvTabOwner.setTextColor(getResources().getColor(R.color.tab_record_text_active_color));
            }
        });
        mRlTabDog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mTvTabDog.setTextColor(getResources().getColor(R.color.tab_record_text_active_color));
            }
        });
        mRlTabCollar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unSelectAllTab();
                mTvTabCollar.setTextColor(getResources().getColor(R.color.tab_record_text_active_color));
            }
        });
    }


    public void unSelectAllTab(){
        mTvTabOwner.setTextColor(getResources().getColor(R.color.tab_record_text_color));
        mTvTabDog.setTextColor(getResources().getColor(R.color.tab_record_text_color));
        mTvTabCollar.setTextColor(getResources().getColor(R.color.tab_record_text_color));
    }
}

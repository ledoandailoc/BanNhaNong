package com.example.uit.bannhanong.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.utils.CommonUtils;

public class BottomMenuView extends RelativeLayout {

    private ImageView mVirtualFenceBtn;
    private ImageView mVirtualLeashBtn;
    private ImageView mLostModeBtn;
    private ImageView mOutBtn;
    private RelativeLayout mBackgroundRl;
    private BottomMenuListener mCallBack;

    public BottomMenuView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View view = LayoutInflater.from(context).inflate(R.layout.main_bottom_menu_view, null);
        mVirtualFenceBtn = CommonUtils.findViewById(view, R.id.menu_virtual_fence_iv);
        mVirtualLeashBtn = CommonUtils.findViewById(view, R.id.menu_virtual_leash_iv);
        mLostModeBtn = CommonUtils.findViewById(view, R.id.menu_lost_mode_iv);
        mOutBtn = CommonUtils.findViewById(view, R.id.main_menu_btn);
        mBackgroundRl = CommonUtils.findViewById(view, R.id.rl_bg);

        mVirtualFenceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVirtualFenceBtn.setImageResource(R.drawable.icon_menu_virtual_fence_active);
            }
        });

        mVirtualLeashBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mVirtualLeashBtn.setImageResource(R.drawable.icon_menu_virtual_leash_active);
            }
        });

        mLostModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLostModeBtn.setImageResource(R.drawable.icon_menu_lost_mode_active);
            }
        });

        mOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onBottomMenuHidden();
            }
        });

        mBackgroundRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onBottomMenuHidden();
            }
        });

        RelativeLayout.LayoutParams param = new
                LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        addView(view, param);
    }

    public void setBottomMenuListener(BottomMenuListener callback) {
        this.mCallBack = callback;
    }

    public BottomMenuView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public BottomMenuView(Context context) {
        super(context);
    }

    public interface BottomMenuListener {
        void onBottomMenuHidden();
    }
}

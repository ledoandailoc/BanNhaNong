package com.example.anhtuan.bnn.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anhtuan.bnn.MainActivity;


abstract public class BaseFragment extends Fragment {

    protected Context mContext;
    //protected PairingListener mPairingListener;
    protected Toast mToast;

    public enum TopBarStyle {
        NONE_TOP_BAR, BACK_KEY_TITLE, MAIN, SKIP_KEY_TITLE, TRAINING_STYLE;
    }

    protected boolean isViewBottomBarView() {
        // Default is not display the bottom bar view.
        return false;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //((MainActivity) (getActivity())).setViewBottomBar(isViewBottomBarView());
        initTitleBar(view);
        initContentViews(view);
        initListener(view);
        initData();
    }

    protected int getTopBarStyle() {
        int type = TopBarStyle.BACK_KEY_TITLE.ordinal();
        return type;
    }


    protected int getInAnimation() {
        return -1;
    }

    protected int getOutAnimation() {
        return -1;
    }

    protected abstract String getScreenTitle();

    protected abstract void initContentViews(View view);

    protected void initTitleBar(View view) {
       /* try {
            int topBarType = getTopBarStyle();
            if (topBarType == TopBarStyle.BACK_KEY_TITLE.ordinal()) {
                TextView screenTitleTv = CommonUtils.findViewById(view, R.id.top_bar_style_1_title_tv);

                if (!getScreenTitle().equals("")) {
                    screenTitleTv.setText(getScreenTitle());
                } else {
                    throw new NullPointerException("Please implement " +
                            "method getScreenTitle() for [" + this.getClass() + "].");
                }

                FrameLayout backFr = CommonUtils.findViewById(view, R.id.top_bar_style_1_back_fr);
                backFr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().onBackPressed();
                    }
                });
            } else if (topBarType == TopBarStyle.MAIN.ordinal()) {
                TextView screenTitleTv = CommonUtils.findViewById(view, R.id.top_bar_main_type_title_tv);
                if (!getScreenTitle().equals("")) {
                    screenTitleTv.setText(getScreenTitle());
                } else {
                    throw new NullPointerException("Please implement " +
                            "method getScreenTitle() for [" + this.getClass() + "].");
                }

                FrameLayout settingFr = CommonUtils.findViewById(view, R.id.top_bar_main_type_setting_fr);
                settingFr.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToastMsg("Coming soon!");
                    }
                });
            } else if (topBarType == TopBarStyle.SKIP_KEY_TITLE.ordinal()) {
                TextView screenTitleTv = CommonUtils.findViewById(view, R.id.top_bar_skip_type_title_tv);
                if (!getScreenTitle().equals("")) {
                    screenTitleTv.setText(getScreenTitle());
                } else {
                    throw new NullPointerException("Please implement " +
                            "method getScreenTitle() for [" + this.getClass() + "].");
                }
                RelativeLayout skipRl = CommonUtils.findViewById(view, R.id.top_bar_skip_type_skip_rl);
                skipRl.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToastMsg("Skip");
                    }
                });
            } else if (topBarType == TopBarStyle.TRAINING_STYLE.ordinal()) {
                TextView screenTitleTv = CommonUtils.findViewById(view, R.id.top_bar_style_training_title_tv);
                if (!getScreenTitle().equals("")) {
                    screenTitleTv.setText(getScreenTitle());
                } else {
                    throw new NullPointerException("Please implement " +
                            "method getScreenTitle() for [" + this.getClass() + "].");
                }
                LinearLayout backBtn = CommonUtils.findViewById(view, R.id.top_bar_style_training_back_btn);
                backBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().onBackPressed();
                    }
                });
                ImageButton infoBtn = CommonUtils.findViewById(view, R.id.top_bar_style_training_info_btn);
                infoBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        showToastMsg("info pressed");
                    }
                });
            }
        } catch (NullPointerException e) {
            // Don't care this case
        }*/
    }

    protected abstract void initListener(View view);

    protected abstract void initData();

    protected void showToastMsg(int messageID) {
        String message = getString(messageID);
        if (getActivity() instanceof BaseFragmentActivity)
            showToastMsg(message);
    }

    protected void showToastMsg(String message) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        mToast.show();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
       /* try {
            if (activity instanceof MainActivity) {
            } else if (activity instanceof PairingActivity) {
                mPairingListener = (PairingListener) activity;
            }
        } catch (ClassCastException e) {
            // Not importance exception
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void showFragment(Fragment fragment) {

        if (!(getActivity() instanceof MainActivity)) {
            throw new ClassCastException("Fragment " + getClass()
                    + " is not belong to " + MainActivity.class);
        }

        ((MainActivity) getActivity()).showFragment(fragment);
    }

    public void showFragmentWithClearStackMode(Fragment fragment) {

        if (!(getActivity() instanceof MainActivity)) {
            throw new ClassCastException("Fragment " + getClass()
                    + " is not belong to " + MainActivity.class);
        }
        ((MainActivity) getActivity()).showFragmentWithClearStackMode(fragment);
    }

    public void showFragmentWithClearTopMode(Fragment fragment) {

        if (!(getActivity() instanceof MainActivity)) {
            throw new ClassCastException("Fragment " + getClass()
                    + " is not belong to " + MainActivity.class);
        }

        ((MainActivity) getActivity()).showFragmentWithClearTopMode(fragment);
    }

    public void showFragmentWithNewContainer(Fragment fragment, int container) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(container, fragment, null).addToBackStack(null)
                .commit();
    }

}

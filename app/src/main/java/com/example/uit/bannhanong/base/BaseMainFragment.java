package com.example.uit.bannhanong.base;

abstract public class BaseMainFragment extends BaseFragment {


    @Override
    protected int getTopBarStyle() {
        return TopBarStyle.MAIN.ordinal();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    protected boolean isViewBottomBarView() {
        return true;
    }
}

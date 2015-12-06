package com.example.anhtuan.bnn.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.anhtuan.bnn.R;
import com.example.anhtuan.bnn.utils.LogUtils;

import java.util.Stack;

abstract public class BaseFragmentActivity extends FragmentActivity
        implements FragmentManager.OnBackStackChangedListener {

    protected final Stack<String> mFragmentTagStack = new Stack<>();

    abstract protected Fragment onCreateMainFragment(Bundle savedInstanceState);

    abstract protected int getFragmentContainerId();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showFragment(onCreateMainFragment(null));
        getSupportFragmentManager().addOnBackStackChangedListener(this);
    }

    protected void showFragmentWithClearStackMode(Fragment f) {
        getSupportFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        mFragmentTagStack.clear();
        showFragment(f);
    }

    protected void showFragmentWithClearTopMode(Fragment f) {
        if (!(f instanceof BaseFragment)) {
            throw new ClassCastException("Fragment [" + f.getClass() + "] " +
                    "is not instance of BaseFragment! Crash...");
        }

        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();

        boolean finishPop = false;

        while (!finishPop) {
            String tag = mFragmentTagStack.pop();
            onBackPressed();
            if (tag.contains(f.getClass().getName())) {
                finishPop = true;
            }
        }
        showFragment(f);
    }

    protected void showFragment(Fragment f) {

        if (!(f instanceof BaseFragment)) {
            throw new ClassCastException("Fragment [" + f.getClass() + "] " +
                    "is not instance of BaseFragment! Crash...");
        }

        String tag = f.getClass().getName() + getNextPositionOfFragment(f.getClass().getName());
        final FragmentManager fm = getSupportFragmentManager();
        final FragmentTransaction ft = fm.beginTransaction();

        int inAni = ((BaseFragment) f).getInAnimation();


        if (inAni != -1) {
            //ft.setCustomAnimations(inAni, R.anim.fragment_no_ani);
        }

        if (mFragmentTagStack.size() > 0) {
            final Fragment ff = fm.findFragmentByTag(mFragmentTagStack.peek());
            // TODO

//            int outAni = ((BaseFragment) ff).getOutAnimation();
//            if (outAni != -1) {
//                ft.setCustomAnimations(R.anim.fragment_no_ani, outAni);
//            }
            ft.hide(ff);

            LogUtils.logDebug("hide fragment: " + ff.getTag());
        }
        ft.add(getFragmentContainerId(), f, tag);
        ft.show(f);
        LogUtils.logDebug("show fragment: " + tag);
        ft.addToBackStack(tag);
        ft.commit();
        mFragmentTagStack.add(tag);
    }

    @Override
    public void onBackStackChanged() {
        FragmentManager fm = getSupportFragmentManager();

        // TODO ???? Still not clear this line of code -> Review later :)))))
        if ((fm.getBackStackEntryCount() - 1) == mFragmentTagStack.size()) {
            return;
        }

        if (mFragmentTagStack.size() > 1) {
            FragmentTransaction ft = fm.beginTransaction();
            String tag = mFragmentTagStack.pop();

            Fragment hideFragment = fm.findFragmentByTag(tag);

            if (hideFragment != null) {
                if (!(hideFragment instanceof BaseFragment)) {
                    throw new ClassCastException("Fragment [" + hideFragment.getClass() + "] " +
                            "is not instance of BaseFragment! Crash...");
                }
//                int outAni = ((BaseFragment) hideFragment).getOutAnimation();
//                if (outAni != -1) {
//                    ft.setCustomAnimations(R.anim.fragment_no_ani, outAni);
//                }
                ft.remove(hideFragment);
            }
            ft.commit();
        } else {
            // TODO
        }
    }

    private int getNextPositionOfFragment(String tag) {
        int pos = 0;
        if (mFragmentTagStack.size() > 0) {
            for (String stackTag : mFragmentTagStack) {
                if (stackTag.contains(tag)) {
                    pos++;
                }
            }
        }
        return pos;
    }
}

package com.example.anhtuan.bnn;

import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.example.anhtuan.bnn.base.BaseFragmentActivity;
import com.example.anhtuan.bnn.fragment.HomeFragment;

public class MainActivity extends BaseFragmentActivity {


    @Override
    protected Fragment onCreateMainFragment(Bundle savedInstanceState) {

        return HomeFragment.newInstance();
    }

    @Override
    protected int getFragmentContainerId() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

package com.example.uit.bannhanong.activity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.connection.base.Method;
import com.example.uit.bannhanong.connection.request.LoginRequest;
import com.example.uit.bannhanong.connection.response.LoginResponse;
import com.example.uit.bannhanong.utils.UserPref;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgressBarActivity extends Activity {

    private TextView mTvSignup, mTvTrangThai;
    private ProgressBar progressBar;
    private RelativeLayout mRlProgressBar;
    int progressbar = 0;
    private LoginRequest mLoginRequest;
    Handler handler;
    int i = 1, time = 300;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_bar);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mRlProgressBar = (RelativeLayout) findViewById(R.id.rl_progressbar);
        mTvTrangThai = (TextView) findViewById(R.id.tv_trang_thai);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (i <= 100) {
                    progressBar.setProgress(i);
                    mTvTrangThai.setText(String.valueOf(i) + "%");
                    if (i == 100)
                        mTvTrangThai.setText("Finish");

                    if (i > 50 && i < 77) {
                        i += 3;
                        time = 4000;
                    } else if (i <= 77)
                        i += 7;
                    else if(i<93){
                        i += 15;
                        time = 3000;
                    } else {
                        i+=7;
                    }
                } else if(i <= 107){
                    i+=5;
                    Intent intent = new Intent(ProgressBarActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
                handler = new Handler();
                handler.postDelayed(this, time);
            }
        }, time);

    }



}

package com.example.uit.bannhanong.activity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uit.bannhanong.DTO.Agricultural;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.connection.ApiLink;
import com.example.uit.bannhanong.connection.base.Method;
import com.example.uit.bannhanong.connection.request.LoginRequest;
import com.example.uit.bannhanong.connection.response.LoginResponse;
import com.example.uit.bannhanong.utils.UserPref;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {

    private EditText mEdtEmail, mEditPassword;
    private Button mBtnLogin;
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
        setContentView(R.layout.activity_login);

        mEdtEmail = (EditText) findViewById(R.id.edtEmail);
        mEditPassword = (EditText) findViewById(R.id.edtPassword);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mTvSignup = (TextView) findViewById(R.id.tvSignup);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        mRlProgressBar = (RelativeLayout) findViewById(R.id.rl_progressbar);
        mTvTrangThai = (TextView) findViewById(R.id.tv_trang_thai);


        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(i<=100) {
                    progressBar.setProgress(i);
                    mTvTrangThai.setText(String.valueOf(i)+ "%");
                    if(i == 100)
                        mTvTrangThai.setText("Finish");

                    if(i > 50 && i <77){
                        i+=3;
                        time = 4000;
                    }
                    else if(i<=77)
                        i+=7;
                    else if (i <93)
                    {
                        i+=15;
                        time = 3000;
                    } else {
                        i+=7;
                    }
                } else {
                    mRlProgressBar.setVisibility(View.GONE);
                }
                handler = new Handler();
                handler.postDelayed(this, time);
            }
        }, time);


        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
                /*Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);*/
            }
        });
        mTvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i);
            }
        });

    }

    private void signIn() {
        if (mEdtEmail.getText().toString().trim().length() == 0) {
            Toast.makeText(LoginActivity.this, "Email can not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!checkEmail(mEdtEmail.getText().toString())) {
            Toast.makeText(LoginActivity.this, "Email incorrect", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEditPassword.getText().toString().trim().length() == 0) {
            Toast.makeText(LoginActivity.this, "Password can not empty", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Signing ...");
        progressDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("username", mEdtEmail.getText().toString().trim());
        params.put("password", mEditPassword.getText().toString().trim());
        String api = "http://192.168.38.1:3000/users/login";
        mLoginRequest = new LoginRequest(Method.POST, api, null, params) {

            @Override
            protected void onStart() {

            }

            @Override
            protected void onSuccess(LoginResponse entity, int statusCode, String message) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();

                }
                UserPref userPref = new UserPref();
                userPref.setUser(entity.data);
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
                finish();


            }

            @Override
            protected void onError(int statusCode, String message) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();

                }
                Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        };
        mLoginRequest.execute();
    }

    private boolean checkEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    public class ThreadX extends Thread {

        public ThreadX(String name)
        {
            super(name);
        }
        public void run()
        {
            System.out.println("Tên luồng:" + getName());
            for (int  i = 0; i <= 100; i+=5){
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                }
                progressBar.setProgress(i);
            }
        }

    }

}

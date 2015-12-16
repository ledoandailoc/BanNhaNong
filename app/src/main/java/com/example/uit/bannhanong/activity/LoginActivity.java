package com.example.uit.bannhanong.activity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.connection.ApiLink;
import com.example.uit.bannhanong.connection.base.Method;
import com.example.uit.bannhanong.connection.request.LoginRequest;
import com.example.uit.bannhanong.connection.response.LoginResponse;
import com.example.uit.bannhanong.utils.UserPref;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends Activity {

    private EditText mEdtEmail, mEditPassword;
    private Button mBtnLogin;
    private TextView mTvSignup;

    private LoginRequest mLoginRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEdtEmail = (EditText) findViewById(R.id.edtEmail);
        mEditPassword = (EditText) findViewById(R.id.edtPassword);
        mBtnLogin = (Button) findViewById(R.id.btnLogin);
        mTvSignup = (TextView) findViewById(R.id.tvSignup);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*signIn();*/
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(i);
            }
        });
        mTvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LoginActivity.this, MainActivity.class);
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
        String api = "http://192.168.198.1:3000/users/login";
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
    
}

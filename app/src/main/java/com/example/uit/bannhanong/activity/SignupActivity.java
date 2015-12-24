package com.example.uit.bannhanong.activity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseFragmentActivity;
import com.example.uit.bannhanong.connection.base.Method;
import com.example.uit.bannhanong.connection.request.SignupRequest;
import com.example.uit.bannhanong.connection.response.SignupResponse;
import com.example.uit.bannhanong.utils.UserPref;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends Activity {

    private EditText mEdtUsername, mEdtEmail, mEditPassword, mEdtPhoneNumber, mEdtCountry;
    private RadioGroup mRgGender;
    private RadioButton mRbNone, mRbMale, mRbFemale;
    private Button mBtnSignUp;
    private TextView mTvSignup;;

    SignupRequest mSignupRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup);

        mEdtUsername = (EditText) findViewById(R.id.edtUsername);
        mEdtEmail = (EditText) findViewById(R.id.edtEmail);
        mEditPassword = (EditText) findViewById(R.id.edtPassword);
        mEdtPhoneNumber = (EditText) findViewById(R.id.edtPhoneNumber);
        mEdtCountry = (EditText) findViewById(R.id.edtCountry);
        mRgGender = (RadioGroup) findViewById(R.id.rgGender);
        mRbNone = (RadioButton) findViewById(R.id.rbNone);
        mRbMale = (RadioButton) findViewById(R.id.rbMale);
        mRbFemale = (RadioButton) findViewById(R.id.rbFemale);
        mBtnSignUp = (Button) findViewById(R.id.btnSignUp);
        mTvSignup = (TextView) findViewById(R.id.tvSignIn);

        mBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
        mTvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    private void signUp() {
        if (mEdtUsername.getText().toString().trim().length() == 0) {
            Toast.makeText(SignupActivity.this, getResources().getString(R.string.signup_username_empty), Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEdtEmail.getText().toString().trim().length() == 0) {
            Toast.makeText(SignupActivity.this, "Email can not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!checkEmail(mEdtEmail.getText().toString())) {
            Toast.makeText(SignupActivity.this, "Email incorrect", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEditPassword.getText().toString().trim().length() == 0) {
            Toast.makeText(SignupActivity.this, "Password can not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (checkPassword() == false) {
            Toast.makeText(SignupActivity.this, "Password is too short", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEdtPhoneNumber.getText().toString().trim().length() == 0) {
            Toast.makeText(SignupActivity.this, "Phone number can not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        if (mEdtCountry.getText().toString().trim().length() == 0) {
            Toast.makeText(SignupActivity.this, "Country can not empty", Toast.LENGTH_SHORT).show();
            return;
        }
        int gender = 1;
        if (mRgGender.getCheckedRadioButtonId() == mRbNone.getId()) {
            Toast.makeText(SignupActivity.this, "Please select a gender", Toast.LENGTH_SHORT).show();
            return;
        } else if (mRgGender.getCheckedRadioButtonId() == mRbMale.getId()) {
            gender = 1;
        } else if (mRgGender.getCheckedRadioButtonId() == mRbFemale.getId()) {
            gender = 2;
        }

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this);
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Registering ...");
        progressDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("username", mEdtUsername.getText().toString().trim());
        params.put("email", mEdtEmail.getText().toString().trim());
        params.put("password", mEditPassword.getText().toString().trim());
        params.put("gender", String.valueOf(gender));
        params.put("phone", mEdtPhoneNumber.getText().toString().trim());
        params.put("country", mEdtCountry.getText().toString().trim());
        String api = "http://192.168.38.1:3000/users/signup";
        mSignupRequest = new SignupRequest(Method.POST, api, null, params) {

            @Override
            protected void onStart() {

            }

            @Override
            protected void onSuccess(SignupResponse entity, int statusCode, String message) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                UserPref userPref = new UserPref();
                userPref.setUser(entity.data);
            }

            @Override
            protected void onError(int statusCode, String message) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        };
        mSignupRequest.execute();
    }

    private boolean checkEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    private boolean checkPassword() {
        if (mEditPassword.getText().toString().trim().length() <= 4) {
            return false;
        }
        return true;
    }
    
}

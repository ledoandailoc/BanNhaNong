package com.example.uit.bannhanong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.example.uit.bannhanong.utils.UserPref;

public class RootActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UserPref userPref = new UserPref();

        if (userPref.getUser() != null) {
            Intent i = new Intent(RootActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        } else {
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
            finish();
        }
    }



}

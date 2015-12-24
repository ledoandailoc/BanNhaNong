package com.example.uit.bannhanong.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import com.example.uit.bannhanong.DTO.Agricultural;
import com.example.uit.bannhanong.DTO.Preference.AgriculturalPref;
import com.example.uit.bannhanong.DTO.Preference.ListAgriculturalPref;
import com.example.uit.bannhanong.utils.UserPref;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


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

package com.example.uit.bannhanong.DTO.Preference;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

import com.example.uit.bannhanong.MainApplication;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ListSearchAgriculturalPref {

    private SharedPreferences mPref;
    private Editor mEditor;
    List<AgriculturalPref> listMonAnPref;
    private static final String KEY = "MonAnPref";

    public ListSearchAgriculturalPref() {
        mPref = PreferenceManager.getDefaultSharedPreferences(MainApplication.getContext());
        mEditor = mPref.edit();
        mEditor.apply();
    }

    public void addMonAn(AgriculturalPref monAnPref) {
        listMonAnPref = getListMonAnPref();
        listMonAnPref.add(monAnPref);
        String jsonUser = "";
        for (AgriculturalPref monAnPref1 : listMonAnPref) {
            Gson gson = new Gson();
            String u = gson.toJson(monAnPref1);
            jsonUser += (u + "zxc");
        }
        mEditor.putString(KEY, jsonUser);
        mEditor.commit();

    }

    public List<AgriculturalPref> getListMonAnPref() {
        String listMonAnPref = mPref.getString(KEY, null);
        if (listMonAnPref != null) {
            listMonAnPref.trim();
            String[] listUser = listMonAnPref.toString().split("zxc");
            List<AgriculturalPref> list = new ArrayList<AgriculturalPref>();
            for (String jsonUser: listUser) {
                AgriculturalPref s = parseUser(jsonUser);
                list.add(s);
            }
            return list;
        } else {
            return new ArrayList<>();
        }
    }

    public List<AgriculturalPref> getListMonAnPrefTheoBuoi(String buoi) {
        String listMonAnPref = mPref.getString(KEY, null);
        if (listMonAnPref != null) {
            listMonAnPref.trim();
            String[] listUser = listMonAnPref.toString().split("zxc");
            List<AgriculturalPref> list = new ArrayList<AgriculturalPref>();
            for (String jsonUser: listUser) {
                AgriculturalPref s = parseUser(jsonUser);
            }
            return list;
        } else {
            return new ArrayList<>();
        }
    }


    private AgriculturalPref parseUser(String jsonUser) {
        try {
            return new AgriculturalPref(new JSONObject(jsonUser));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}

package com.example.uit.bannhanong.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.uit.bannhanong.DTO.Agricultural;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.AgriculturalPriceAdapter;

import java.util.ArrayList;

public class SaleActivity extends AppCompatActivity {
    Spinner spLoaiSanPham;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale);
        spLoaiSanPham = (Spinner) findViewById(R.id.spiner_danh_muc);

        ArrayList<String> provinceList = new ArrayList<>();
        provinceList.add("Xe");provinceList.add("Nhà");provinceList.add("Điện Thoại");provinceList.add("Tivi");

        ArrayAdapter arrayadapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, provinceList);
        arrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spLoaiSanPham.setAdapter(arrayadapter);
        spLoaiSanPham.setOnItemSelectedListener(new android.widget.AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

}

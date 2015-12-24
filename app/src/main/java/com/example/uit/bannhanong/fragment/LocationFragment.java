package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.uit.bannhanong.DTO.Store;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.utils.CommonUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class LocationFragment extends BaseMainFragment {

    private TextView mTvActiobarTille;
    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;

    // Static LatLng Dummy
    LatLng myPosition = new LatLng(10.868406, 106.802996);

    ArrayList<Store> arrayListStore = new ArrayList<>();
    ArrayList<LatLng> listLatLng = new ArrayList<>();


    public static LocationFragment newInstance() {
        return new LocationFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_map, container, false);
        return v;
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
    }

    @Override
    protected void initListener(View view) {
        mTvActiobarTille = CommonUtils.findViewById(view, R.id.tv_actionbar_title);

    }

    @Override
    protected void initData() {
        mTvActiobarTille.setText("Địa điểm");
        }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FragmentManager fm = getChildFragmentManager();
        mMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (mMapFragment == null) {
            mMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, mMapFragment).commit();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMap == null) {
            mMap = mMapFragment.getMap();
            mMap.setMyLocationEnabled(true);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(15));

            //Dummy data
            Store store1 = new Store("Cỏ 2 Lá","Cửa hàng phân thuốc",10.868406,106.802996);
            arrayListStore.add(store1);
            Store store2 = new Store("Cỏ 3 Lá","Cửa hàng gạo",10.874484,106.800224);
            arrayListStore.add(store2);
            Store store3 = new Store("Cỏ 4 Lá","Cửa hàng cà phê",10.870499,106.797418);
            arrayListStore.add(store3);
            //end Create Dummy

            for (Store store: arrayListStore) {
                LatLng latLng = new LatLng(store.latitude, store.longtitude);
                MarkerOptions startPoint = new MarkerOptions();
                startPoint.position(latLng);
                startPoint.title(store.name + " - " + store.category);
                startPoint.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_store));
                mMap.addMarker(startPoint);
            }

        }
    }

}
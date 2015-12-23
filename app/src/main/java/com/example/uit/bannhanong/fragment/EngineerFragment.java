package com.example.uit.bannhanong.fragment;

import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.EngineerAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.connection.ApiLink;
import com.example.uit.bannhanong.connection.base.Method;
import com.example.uit.bannhanong.connection.request.GetListUserRequest;
import com.example.uit.bannhanong.connection.response.ListUserResponse;
import com.example.uit.bannhanong.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

public class EngineerFragment extends BaseMainFragment {

    private ListView mLvEngineer;
    List<Engineer> listEngineer;
    GetListUserRequest mGetListUserRequest;
    EngineerAdapter engineerAdapter;


    public static EngineerFragment newInstance() {
        return new EngineerFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engineer, container, false);
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        mLvEngineer = CommonUtils.findViewById(view, R.id.lv_engineer);
    }

    @Override
    protected void initListener(View view) {
    }

    @Override
    protected void initData() {
        listEngineer = new ArrayList<Engineer>();
        engineerAdapter = new EngineerAdapter(getActivity(), R.layout.item_engineer, listEngineer);

        getListUsers();

        mLvEngineer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EngineerDetailFragment engineerDetailFragment = EngineerDetailFragment.newInstance();
                Bundle bundle =new Bundle(); bundle.putSerializable("1", listEngineer.get(position));
                engineerDetailFragment.setArguments(bundle);
                showFragmentWithClearStackMode(engineerDetailFragment);
            }
        });
    }

    private void getListUsers() {
        String api = "http://192.168.38.1:3000/users/getAllFriends";
        mGetListUserRequest = new GetListUserRequest(Method.GET, /*ApiLink.getContactLink()*/api, null, null) {
            @Override
            protected void onStart() {
            }

            @Override
            protected void onSuccess(ListUserResponse entity, int statusCode, String message) {
                listEngineer.clear();
                listEngineer.addAll(entity.data);
                engineerAdapter.notifyDataSetChanged();
                mLvEngineer.setAdapter(engineerAdapter);
            }

            @Override
            protected void onError(int statusCode, String message) {
                Toast.makeText(getActivity(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
            }
        };
        mGetListUserRequest.execute();
    }
}
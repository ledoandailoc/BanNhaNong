package com.example.uit.bannhanong.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uit.bannhanong.MainApplication;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.connection.base.Method;
import com.example.uit.bannhanong.connection.request.UpdateRequest;
import com.example.uit.bannhanong.connection.response.UpdateResponse;
import com.example.uit.bannhanong.utils.CommonUtils;
import com.example.uit.bannhanong.utils.UserPref;

import java.util.HashMap;

public class EngineerPublicFragment extends BaseMainFragment {
    @Override
    protected boolean isViewBottomBarView() {
        return false;
    }

    TextView mEdtPhoneNumber, mEdtSpecialized, mEdtPlace;
    UserPref userPref;
    UpdateRequest mUpdateRequest;
    Button mBtnApply;
    ImageView mIvBack;

    public static EngineerPublicFragment newInstance() {
        return new EngineerPublicFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engineer_public, container, false);

    }


    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        mEdtPhoneNumber = CommonUtils.findViewById(view, R.id.edt_phone_number);
        mEdtSpecialized = CommonUtils.findViewById(view, R.id.edt_specialized);
        mEdtPlace = CommonUtils.findViewById(view, R.id.edt_place);
        mBtnApply = CommonUtils.findViewById(view, R.id.btn_apply);
        mIvBack = CommonUtils.findViewById(view, R.id.iv_back);

        userPref = new UserPref();
    }

    @Override
    protected void initListener(View view) {
        mBtnApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                update();
            }
        });
        mIvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WorkshopFragment workshopFragment = new WorkshopFragment();
                showFragmentWithClearStackMode(workshopFragment);
            }
        });
    }

    @Override
    protected void initData() {
    }

    private void update() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCanceledOnTouchOutside(true);
        progressDialog.setCancelable(true);
        progressDialog.setMessage("Registering ...");
        progressDialog.show();

        HashMap<String, String> params = new HashMap<>();
        params.put("phone", mEdtPhoneNumber.getText().toString().trim());
        params.put("place", mEdtPlace.getText().toString().trim());
        params.put("specialized", mEdtSpecialized.getText().toString().trim());
        params.put("type_public", "2");
        String api = "http://192.168.38.1:3000/users/update/";
        mUpdateRequest = new UpdateRequest(Method.PUT, api + userPref.getUser()._id, null, params) {

            @Override
            protected void onStart() {

            }

            @Override
            protected void onSuccess(UpdateResponse entity, int statusCode, String message) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            protected void onError(int statusCode, String message) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(MainApplication.getContext(), message, Toast.LENGTH_SHORT).show();
            }
        };
        mUpdateRequest.execute();
    }
}
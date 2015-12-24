package com.example.uit.bannhanong.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uit.bannhanong.DTO.Engineer;
import com.example.uit.bannhanong.MainApplication;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.EngineerAdapter;
import com.example.uit.bannhanong.adapter.UserAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.connection.base.Method;
import com.example.uit.bannhanong.connection.request.GetListUserRequest;
import com.example.uit.bannhanong.connection.response.ListUserResponse;
import com.example.uit.bannhanong.utils.CommonUtils;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class EngineerHostFragment extends BaseMainFragment {

    @Override
    protected boolean isViewBottomBarView() {
        return false;
    }

    ImageView mIvBack;
    UserAdapter userAdapter;
    GetListUserRequest mGetListUserRequest;
    ListView mLvEngineer;
    List<Engineer> listEngineer;

    public static EngineerHostFragment newInstance() {
        return new EngineerHostFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_engineer_host, container, false);
    }

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        TextView title = CommonUtils.findViewById(view, R.id.title);
        title.setText("Lịch sử thoại");

        mIvBack = CommonUtils.findViewById(view, R.id.iv_back);
        mLvEngineer = CommonUtils.findViewById(view, R.id.lv_engineer);
    }

    @Override
    protected void initListener(View view) {
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
        listEngineer = new ArrayList<Engineer>();
        Engineer engineer = new Engineer();
        userAdapter = new UserAdapter(getActivity(), R.layout.item_user, listEngineer);
        mLvEngineer.setAdapter(userAdapter);

        getListUsers();

        mLvEngineer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ChatFragment engineerDetailFragment = ChatFragment.newInstance();
                Bundle bundle =new Bundle(); bundle.putString("2", listEngineer.get(position)._id);
                engineerDetailFragment.setArguments(bundle);
                showFragmentWithClearStackMode(engineerDetailFragment);
            }
        });
    }

    private void getListUsers() {
        String api = "http://192.168.38.1:3000/messages/getMessageHistory";
        mGetListUserRequest = new GetListUserRequest(Method.GET, /*ApiLink.getContactLink()*/api, null, null) {
            @Override
            protected void onStart() {
            }

            @Override
            protected void onSuccess(ListUserResponse entity, int statusCode, String message) {
                listEngineer.clear();
                listEngineer.addAll(entity.data);
                userAdapter.notifyDataSetChanged();
                mLvEngineer.setAdapter(userAdapter);
            }

            @Override
            protected void onError(int statusCode, String message) {
                Toast.makeText(MainApplication.getContext(), "Get failed with error: " + message, Toast.LENGTH_SHORT).show();
            }
        };
        mGetListUserRequest.execute();

    }
}
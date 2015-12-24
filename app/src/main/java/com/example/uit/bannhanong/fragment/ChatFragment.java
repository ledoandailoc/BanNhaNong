package com.example.uit.bannhanong.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.uit.bannhanong.DTO.Chat;
import com.example.uit.bannhanong.DTO.Data;
import com.example.uit.bannhanong.DTO.User;
import com.example.uit.bannhanong.MainApplication;
import com.example.uit.bannhanong.R;
import com.example.uit.bannhanong.adapter.MessageAdapter;
import com.example.uit.bannhanong.base.BaseMainFragment;
import com.example.uit.bannhanong.connection.ApiLink;
import com.example.uit.bannhanong.connection.base.Method;
import com.example.uit.bannhanong.connection.request.GetListMessageByUserIdRequest;
import com.example.uit.bannhanong.connection.response.ChatResponse;
import com.example.uit.bannhanong.connection.response.ListMessageResponse;
import com.example.uit.bannhanong.utils.CommonUtils;
import com.example.uit.bannhanong.utils.Constant;
import com.example.uit.bannhanong.utils.UserPref;
import com.github.nkzawa.socketio.client.Socket;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class ChatFragment extends BaseMainFragment {
    List<User> mListUser;
    private EditText mEdtMessage;
    private User mCurrentUser;
    private Socket mSocket;
    private ArrayList<Chat> mList = new ArrayList<>();
    ImageView mBtnSend;
    String receiverId;
    ListView mLvMessage;
    private MessageAdapter mAdapter;
    private String userIDrequest;
    UserPref userPref;
    private GetListMessageByUserIdRequest mGetListMessageByUserIdRequest;

    public static ChatFragment newInstance() {
        return new ChatFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.activity_chat, container, false);
    }

    private EventBus mBus = EventBus.getDefault();

    @Override
    protected String getScreenTitle() {
        return null;
    }

    @Override
    protected void initContentViews(View view) {
        TextView title  = CommonUtils.findViewById(view,R.id.actionbar_tvTitile);
        title.setText("Tin nhắn");

        receiverId = getArguments().getString("2");

        mBtnSend = CommonUtils.findViewById(view, R.id.mBtnSend);
        mLvMessage = CommonUtils.findViewById(view,R.id.listView);
        mAdapter = new MessageAdapter(getActivity(), mList);
        mEdtMessage = CommonUtils.findViewById(view, R.id.edt_chat_message);

        mLvMessage.setAdapter(mAdapter);
        userPref = new UserPref();
        mCurrentUser = userPref.getUser();

        mBtnSend.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                sendTextMessage();
            }
        });
        getMessageHistoryByUserId();

        mBus.register(this);
    }

    @Override
    protected void initListener(View view) {

    }

    @Override
    protected void initData() {

    }

    private void getMessageHistoryByUserId() {

        userIDrequest = receiverId;
        String api = "http://192.168.38.1:3000/messages/getRoomMessagesByUserId/";
        mGetListMessageByUserIdRequest = new GetListMessageByUserIdRequest(Method.GET, /*ApiLink.getRoomMessageByUserIdLink()*/api + userIDrequest, null, null) {

            @Override
            protected void onStart() {

            }

            @Override
            protected void onSuccess(ListMessageResponse entity, int statusCode, String message) {
                mList.clear();
                //Collections.reverse(entity.data);
                mList.addAll(entity.data);
                buildTypeDisplay();
                mAdapter.notifyDataSetChanged();

                        //mLvMessage.smoothScrollToPosition(mList.size());

                mLvMessage.setSelection(mList.size()-1);
            }

            @Override
            protected void onError(int statusCode, String message) {
                Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
            }
        };
        mGetListMessageByUserIdRequest.execute();
    }

    public void onEvent(ChatResponse chatResponse) {

        Chat data = chatResponse.data;

        mList.add(data);
        buildTypeDisplay();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mAdapter.notifyDataSetChanged();
                mLvMessage.smoothScrollToPosition(mList.size());
            }
        });

    }


    private void buildTypeDisplay() {
        if(mList.size() < 1)
            Toast.makeText(getActivity(), "New room", Toast.LENGTH_SHORT).show();

        for (int i = 0; i < mList.size(); i++) {

            if (true) {
                if (i == 0) {
                    if (mList.get(i).user._id.equals(mCurrentUser._id)) {
                        mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR;
                    } else {
                        mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR;
                    }
                } else {
                    if (mList.get(i).user._id.equals(mCurrentUser._id)) {
                        if (mList.get(i).user._id.equals(mList.get(i - 1).user._id)) {
                            mList.get(i - 1).message.type = Constant.CHAT_TYPE_TEXT_RIGHT_WITHOUT_AVATAR;
                            mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR;
                        } else {
                            mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_RIGHT_WITH_AVATAR;
                        }
                    } else {
                        if (mList.get(i).user._id.equals(mList.get(i - 1).user._id)) {
                            mList.get(i - 1).message.type = Constant.CHAT_TYPE_TEXT_LEFT_WITHOUT_AVATAR;
                            mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR;
                        } else {
                            mList.get(i).message.type = Constant.CHAT_TYPE_TEXT_LEFT_WITH_AVATAR;
                        }
                    }
                }
            }
        }
    }
    private void sendTextMessage() {
        mListUser =  new ArrayList<User>();


        mCurrentUser = userPref.getUser();
        mSocket = MainApplication.getMySocket().getSocket();

        if (mEdtMessage.getText().toString().trim().length() == 0) {
            Toast.makeText(getActivity(), "Please type your message", Toast.LENGTH_SHORT).show();
        } else {
            JSONObject messageObject = new JSONObject();
            try {
                // send chat to server by socket
                messageObject.put("userId", mCurrentUser._id);

                messageObject.put("message", mEdtMessage.getText().toString().trim());

                messageObject.put("senderName", mCurrentUser.username);
                messageObject.put("targetId", receiverId);



                //String sequence = String.valueOf(CommonUtils.getCurrentTimeMillis());


                mSocket.emit(Constant.SOCKET_EVENT_CHAT, messageObject);
                // add item into list
                Data data = new Data();

                data.message = mEdtMessage.getText().toString().trim();
                data.userId = mCurrentUser._id;
                //data.sequence = sequence;
              /*  mList.add(data);
                buildTypeDisplay();
                this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        mLvMessage.smoothScrollToPosition(mList.size());
                    }
                });*/
                // reset text in edit text
                mEdtMessage.setText(null);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

}

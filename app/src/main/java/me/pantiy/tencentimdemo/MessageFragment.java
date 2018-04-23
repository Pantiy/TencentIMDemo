package me.pantiy.tencentimdemo;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.imsdk.TIMConversation;
import com.tencent.imsdk.TIMConversationType;
import com.tencent.imsdk.TIMElem;
import com.tencent.imsdk.TIMElemType;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMMessage;
import com.tencent.imsdk.TIMMessageListener;
import com.tencent.imsdk.TIMTextElem;
import com.tencent.imsdk.TIMValueCallBack;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.pantiy.tencentimdemo.utils.ToastUtil;

/**
 * Created by Pantiy on 2018/4/22.
 * Copyright © 2016 All rights Reserved by Pantiy
 */
public class MessageFragment extends Fragment {

    @BindView(R.id.aimUserId_editText) EditText mAimUserIdEt;
    @BindView(R.id.messageContent_editText) EditText mMessageContentEt;
    @BindView(R.id.sendMessage_button) Button mSendMessageBtn;
    @BindView(R.id.receiveMessage_textView) TextView mReceiveMessageTv;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        ButterKnife.bind(this, view);
        setTIMMessageListener();
        return view;
    }

    @OnClick(R.id.sendMessage_button)
    void onSendMessageBtnClick() {
        TIMConversation timConversation = TIMManager.getInstance().getConversation(
                TIMConversationType.C2C, mAimUserIdEt.getText().toString());
        TIMMessage timMessage = new TIMMessage();
        TIMTextElem timTextElem = new TIMTextElem();
        timTextElem.setText(mMessageContentEt.getText().toString());
        timMessage.addElement(timTextElem);
        timConversation.sendMessage(timMessage, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                ToastUtil.shortToast(getContext(), "发送失败");
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                ToastUtil.shortToast(getContext(), "发送成功");
            }
        });
    }

    private void setTIMMessageListener() {
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                for (TIMMessage timMessage : list) {
                    long elementCount = timMessage.getElementCount();
                    for (int i = 0; i < elementCount; i++) {
                        TIMElem timElem = timMessage.getElement(i);
                        if (timElem.getType() == TIMElemType.Text) {
                            mReceiveMessageTv.append(((TIMTextElem)timElem).getText());
                        }
                    }
                }
                return false;
            }
        });
    }
}
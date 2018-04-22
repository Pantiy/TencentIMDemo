package me.pantiy.tencentimdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tencent.imsdk.TIMCallBack;
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

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.userId_editText) EditText mUserIdEt;
    @BindView(R.id.login_button) Button mLoginBtn;
    @BindView(R.id.aimUserId_editText) EditText mAimUserIdEt;
    @BindView(R.id.messageContent_editText) EditText mMessageContentEt;
    @BindView(R.id.sendMessage_button) Button mSendMessageBtn;
    @BindView(R.id.receiveMessage_textView) TextView mReceiveMessageTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        onReceiveMessage();
    }

    @OnClick(R.id.login_button)
    void login() {
        String userId = mUserIdEt.getText().toString();
        TIMManager.getInstance().login(userId, SigUtil.touch().getUserSig(userId), new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                ToastUtil.shortToast(MainActivity.this, "登陆失败");
            }

            @Override
            public void onSuccess() {
                ToastUtil.shortToast(MainActivity.this, "登陆成功");
            }
        });
    }

    @OnClick(R.id.sendMessage_button)
    void sendMessage() {
        TIMConversation timConversation = TIMManager.getInstance().getConversation(
                TIMConversationType.C2C, mAimUserIdEt.getText().toString());
        TIMMessage timMessage = new TIMMessage();
        TIMTextElem timTextElem = new TIMTextElem();
        timTextElem.setText(mMessageContentEt.getText().toString());
        timMessage.addElement(timTextElem);
        timConversation.sendMessage(timMessage, new TIMValueCallBack<TIMMessage>() {
            @Override
            public void onError(int i, String s) {
                ToastUtil.shortToast(MainActivity.this, "发送失败");
            }

            @Override
            public void onSuccess(TIMMessage timMessage) {
                ToastUtil.shortToast(MainActivity.this, "发送成功");
            }
        });
    }

    private void onReceiveMessage() {
        TIMManager.getInstance().addMessageListener(new TIMMessageListener() {
            @Override
            public boolean onNewMessages(List<TIMMessage> list) {
                ToastUtil.shortToast(MainActivity.this, "收到消息");
                for (TIMMessage timMessage : list) {
                    for (int i = 0; i < timMessage.getElementCount(); i++) {
                        TIMElem elem = timMessage.getElement(i);
                        TIMElemType elemType = elem.getType();
                        if (elemType == TIMElemType.Text) {
                            TIMTextElem timTextElem = (TIMTextElem) elem;
                            mReceiveMessageTv.append(timTextElem.getText() + '\n');
                        }
                    }
                }
                return false;
            }
        });
    }
}

package me.pantiy.tencentimdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;

import me.pantiy.tencentimdemo.MessageFragment;
import me.pantiy.tencentimdemo.global.Config;
import me.pantiy.tencentimdemo.utils.SigUtil;
import me.pantiy.tencentimdemo.utils.ToastUtil;

/**
 * Created by Pantiy on 2018/4/22.
 * Copyright © 2016 All rights Reserved by Pantiy
 */
public class MessageActivity extends SingleFragmentActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String userId = getIntent().getStringExtra(Config.EXTRA_USER_ID);
        TIMManager.getInstance().login(userId, SigUtil.touch().getUserSig(userId), new TIMCallBack() {
            @Override
            public void onError(int i, String s) {
                Intent intent = new Intent(MessageActivity.this, LoginActivity.class);
                intent.putExtra(Config.EXTRA_LOGIN_SUCCESS, false);
                startActivity(intent);
                finish();
            }

            @Override
            public void onSuccess() {
                ToastUtil.shortToast(MessageActivity.this, "登陆成功");
            }
        });
    }

    @Override
    protected Fragment createFragment() {
        return new MessageFragment();
    }
}

package me.pantiy.tencentimdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.pantiy.tencentimdemo.R;
import me.pantiy.tencentimdemo.global.Config;
import me.pantiy.tencentimdemo.utils.ToastUtil;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.userId_editText) EditText mUserIdEt;
    @BindView(R.id.login_button) Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        boolean loginSuccess = getIntent().getBooleanExtra(Config.EXTRA_LOGIN_SUCCESS, true);
        if (!loginSuccess) {
            ToastUtil.shortToast(this, "登录失败");
        }
    }

    @OnClick(R.id.login_button)
    void login() {
        Intent intent = new Intent(this, MessageActivity.class);
        intent.putExtra(Config.EXTRA_USER_ID, mUserIdEt.getText().toString());
        startActivity(intent);
        finish();
    }
}

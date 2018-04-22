package me.pantiy.tencentimdemo;

import android.app.Application;

import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;

/**
 * Created by Pantiy on 2018/4/20.
 * Copyright © 2016 All rights Reserved by Pantiy
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initTim();
    }

    private void initTim() {
        TIMSdkConfig timSdkConfig = new TIMSdkConfig(Config.SDK_APP_ID)
                .enableCrashReport(false)
                .enableLogPrint(true);
        TIMManager.getInstance().init(getApplicationContext(), timSdkConfig);
    }
}

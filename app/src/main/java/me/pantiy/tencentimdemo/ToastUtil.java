package me.pantiy.tencentimdemo;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Pantiy on 2018/4/20.
 * Copyright © 2016 All rights Reserved by Pantiy
 */
public class ToastUtil {

    public static void shortToast(Context context, String content) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show();
    }
}

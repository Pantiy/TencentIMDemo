package me.pantiy.tencentimdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import me.pantiy.tencentimdemo.R;

/**
 * Created by Pantiy on 2018/4/22.
 * Copyright © 2016 All rights Reserved by Pantiy
 */
public abstract class SingleFragmentActivity extends BaseActivity {

    protected Fragment mFragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (mFragment == null) {
            mFragment = createFragment();
        }
        fragmentManager.beginTransaction().add(R.id.fragment_container, mFragment).commit();
    }

    protected abstract Fragment createFragment();

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_single_fragment;
    }
}

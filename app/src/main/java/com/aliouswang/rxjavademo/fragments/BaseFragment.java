package com.aliouswang.rxjavademo.fragments;

import android.support.v4.app.Fragment;

import com.aliouswang.rxjavademo.MyApp;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by aliouswang on 16/2/26.
 */
public class BaseFragment extends Fragment{

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = MyApp.getRefWatcher();
        refWatcher.watch(this);
    }
}

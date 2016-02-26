package com.aliouswang.rxjavademo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Button;

import com.aliouswang.rxjavademo.fragments.MainFragment;

public class MainActivity extends FragmentActivity {

    public static final String TAG = "rxjava-test";

    private Button btn_rx_create;
    private Button btn_just;
    private Button btn_rx_flat_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(android.R.id.content, new MainFragment(), this.toString())
                    .commit();
        }
    }


}

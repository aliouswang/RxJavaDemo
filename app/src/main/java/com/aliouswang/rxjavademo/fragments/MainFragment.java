package com.aliouswang.rxjavademo.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aliouswang.rxjavademo.R;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aliouswang on 16/2/26.
 */
public class MainFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, layout);
        return layout;
    }

    @OnClick(R.id.btn_demo_schedulers)
    void demoConcurrencyWithSchedulers() {
        clickedOn(new ConcurrencyWithSchedulersDemoFragment());
    }

    private void clickedOn(Fragment fragment) {
        final String tag = fragment.getClass().toString();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(tag)
                .replace(android.R.id.content, fragment, tag)
                .commit();
    }
}

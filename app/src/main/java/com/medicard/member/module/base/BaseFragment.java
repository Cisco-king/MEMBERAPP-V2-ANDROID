package com.medicard.member.module.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public abstract class BaseFragment extends Fragment {


    protected Unbinder unbinder;
    protected Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResource(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initComponents(view, savedInstanceState);
    }

    protected void initComponents(View view, Bundle savedInstanceState) {
        // detach the binder to Fragment#onDetach
        unbinder = ButterKnife.bind(this, view);
        context = getActivity();

    }

    public abstract int getLayoutResource();
}

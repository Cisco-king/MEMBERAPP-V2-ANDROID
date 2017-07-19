package com.medicard.member.module.test;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import services.model.Test;

/**
 * Created by casjohnpaul on 6/17/2017.
 */

public class TestPresenter implements TestMvp.Presenter {


    private TestMvp.View testView;
    private Context context;

    public TestPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(TestMvp.View view) {
        testView = view;
    }

    @Override
    public void detachView() {
        testView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }





}

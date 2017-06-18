package com.medicard.member.module.newprocedure;

import android.content.Context;

/**
 * Created by casjohnpaul on 6/17/2017.
 */

public class NewTestProcedurePresenter implements NewTestMvp.Presenter {


    private Context context;
    private NewTestMvp.View newTestView;

    public NewTestProcedurePresenter(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(NewTestMvp.View view) {
        newTestView = view;
    }

    @Override
    public void detachView() {
        newTestView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }
}

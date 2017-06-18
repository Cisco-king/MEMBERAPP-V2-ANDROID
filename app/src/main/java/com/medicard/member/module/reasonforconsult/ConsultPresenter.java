package com.medicard.member.module.reasonforconsult;

import android.content.Context;

import com.medicard.member.R;

import utilities.StringUtilities;
import utilities.ViewUtilities;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public class ConsultPresenter implements ConsultMvp.Presenter {


    private Context context;
    private ConsultMvp.View consultView;

    public ConsultPresenter(Context context) {
        this.context = context;
    }

    @Override
    public void attachView(ConsultMvp.View view) {
        consultView = view;
    }

    @Override
    public void detachView() {
        consultView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void validateReason(String reason) {
        if (StringUtilities.isNotEmpty(reason)) {
            consultView.proceedSuccess();
        } else {
            consultView.proceedError(context.getString(R.string.field_required));
        }
    }
}

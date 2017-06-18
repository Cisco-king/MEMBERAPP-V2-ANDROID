package com.medicard.member.module.newprocedure;

import modules.base.Mvp;

/**
 * Created by casjohnpaul on 6/17/2017.
 */

public interface NewTestMvp {

    interface View extends Mvp.View {

        void proceedDoctorActivity();

    }

    interface Presenter extends Mvp.Presenter<NewTestMvp.View> {

    }

}

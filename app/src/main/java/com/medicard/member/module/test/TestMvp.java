package com.medicard.member.module.test;

import modules.base.Mvp;

/**
 * Created by casjohnpaul on 6/17/2017.
 */

public interface TestMvp {

    interface View extends Mvp.View {

    }

    interface Presenter extends Mvp.Presenter<TestMvp.View> {

    }

}

package com.medicard.member.module.test;

import java.util.List;

import modules.base.Mvp;
import services.model.Test;

/**
 * Created by casjohnpaul on 6/17/2017.
 */

public interface TestMvp {

    interface View extends Mvp.View {



    }

    interface Presenter extends Mvp.Presenter<TestMvp.View> {


    }

}

package com.medicard.member.module.reasonforconsult;

import modules.base.Mvp;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public interface ConsultMvp {

    interface View extends Mvp.View {

        void proceedSuccess();

        void proceedError(String message);

    }

    interface Presenter extends Mvp.Presenter<ConsultMvp.View> {

        void validateReason(String reason);

    }

}

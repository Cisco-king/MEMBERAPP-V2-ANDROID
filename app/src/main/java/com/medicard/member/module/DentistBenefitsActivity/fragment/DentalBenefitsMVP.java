package com.medicard.member.module.DentistBenefitsActivity.fragment;

import android.view.View;

import modules.base.Mvp;

/**
 * Created by macbookpro on 7/31/17.
 */

public interface DentalBenefitsMVP {

    interface View extends Mvp.View{

        void onDisplayMessage(String message);

    }

    interface Presenter extends Mvp.Presenter<View>{

        void loadDentalBenefits(String memberCode, DentalBenefitsPresenter.DentalBenefitCallback callback   );


    }
}

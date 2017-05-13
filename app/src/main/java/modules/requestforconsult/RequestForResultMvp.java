package modules.requestforconsult;

import modules.base.Mvp;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public interface RequestForResultMvp {

    interface View extends Mvp.View {

        void onProceedSuccess();

        void onProceedError(String message);

    }

    interface Presenter extends Mvp.Presenter<View> {

        void proceedToRequestDoctorActivity(String reasonForConsult);

    }

}

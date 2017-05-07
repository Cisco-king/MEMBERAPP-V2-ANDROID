package modules.requestforconsult;

import modules.requestforconsult.RequestForResultMvp;
import utilities.StringUtilities;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public class RequestForResultPresenter implements RequestForResultMvp.Presenter {


    RequestForResultMvp.View requestForResultView;

    public RequestForResultPresenter() {

    }

    @Override
    public void attachView(RequestForResultMvp.View view) {
        requestForResultView = view;
    }

    @Override
    public void detachView() {
        requestForResultView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void proceedToRequestDoctorActivity(String reasonForConsult) {
        if (StringUtilities.isNotEmpty(reasonForConsult)) {
            requestForResultView.onProceedSuccess();
        } else {
            requestForResultView.onProceedError("This field is required");
        }
    }

}

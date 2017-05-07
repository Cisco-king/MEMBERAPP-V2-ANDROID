package modules.requestforconsult2;

import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;

import com.medicard.member.R;

import butterknife.BindView;
import butterknife.OnClick;
import modules.base.activities.TestTrackableActivity;
import modules.requestforconsult.RequestForResultMvp;
import modules.requestforconsult.RequestForResultPresenter;
import utilities.SharedPref;
import utilities.ViewUtilities;
import v2.HospitalListAcitivity;

public class RequestForConsultActivity extends TestTrackableActivity
        implements RequestForResultMvp.View {


    @BindView(R.id.btnReasonForConsultOk) Button btnReasonForConsultOk;

    @BindView(R.id.etReasonForConsult) EditText etReasonForConsult;

    private RequestForResultMvp.Presenter requestForResultPresenter;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_request_for_consult;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");

        requestForResultPresenter = new RequestForResultPresenter();
        requestForResultPresenter.attachView(this);

        etReasonForConsult.setOnKeyListener(onKeyDisableEnterListener);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        requestForResultPresenter.detachView();
    }

    @OnClick(R.id.btnReasonForConsultOk)
    public void startRequestingDoctor() {
        requestForResultPresenter.proceedToRequestDoctorActivity(ViewUtilities.getEditValue(etReasonForConsult));
    }

    @Override
    public void onProceedSuccess() {
        // set the reason for consult with key {$SharedPref#KEY_REASON_FOR_CONSULT}
        SharedPref.setAppPreference(
                this,
                SharedPref.KEY_REASON_FOR_CONSULT,
                ViewUtilities.getEditValue(etReasonForConsult));

        Intent requestDoctorActivity = new Intent(this, HospitalListAcitivity.class);
        startActivity(requestDoctorActivity);
    }

    @Override
    public void onProceedError(String message) {
        etReasonForConsult.requestFocus();
        etReasonForConsult.setError(message);
    }

}

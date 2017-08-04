package modules.requestforconsult;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.medicard.member.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import modules.newtest.NewTestActivity;
import modules.newtest.NewTestMvp;
import timber.log.Timber;
import utilities.SharedPref;
import utilities.ViewUtilities;


public class RequestForConsultFragment extends Fragment implements RequestForResultMvp.View  {


    @BindView(R.id.etReasonForConsult) EditText etReasonForConsult;

    @BindView(R.id.btnReasonForConsultOk) Button btnReasonForConsultOk;

    private NewTestMvp.View newTestNavigator;
    private RequestForResultMvp.Presenter presenter;

    public RequestForConsultFragment() {

    }

    public static RequestForConsultFragment newInstance() {
        
        Bundle args = new Bundle();
        RequestForConsultFragment fragment = new RequestForConsultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NewTestMvp.View) {
            newTestNavigator = (NewTestMvp.View) context;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reason_for_consult, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initViews(view);
    }

    private void initViews(View view) {
        presenter = new RequestForResultPresenter();
        presenter.attachView(this);
    }

    @OnClick(R.id.btnReasonForConsultOk)
    public void startRequestingDoctor() {
        System.out.println("InsideProceed");
        System.out.println("============================== BAKA DITO PUMASOK");
        presenter.proceedToRequestDoctorActivity(
                ViewUtilities.getEditValue(etReasonForConsult));
    }

    @Override
    public void onProceedSuccess() {
        SharedPref.setAppPreference(
                getContext(),
                SharedPref.KEY_REASON_FOR_CONSULT,
                ViewUtilities.getEditValue(etReasonForConsult));

        newTestNavigator.displayDoctorView();

        Timber.d("proceed success");
    }

    @Override
    public void onProceedError(String message) {
        etReasonForConsult.requestFocus();
        etReasonForConsult.setError(message);
    }

}

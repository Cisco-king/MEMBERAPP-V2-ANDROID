package modules.diagnosis;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.callback.RecyclerViewOnClickListener;
import modules.diagnosis.adapter.DiagnosisAdapter;
import services.model.Diagnosis;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.Loader;

public class DiagnosisFragment extends Fragment implements DiagnosisMvp.View, RecyclerViewOnClickListener {


    @BindView(R.id.edSearchDiagnosis) TextView edSearchDiagnosis;
    @BindView(R.id.rvHospitalDiagnosis) RecyclerView rvHospitalDiagnosis;

    DiagnosisAdapter diagnosisAdapter;

    private DiagnosisMvp.Presenter presenter;

    private Loader loader;

    private AlertDialogCustom notificationMessage;

    public DiagnosisFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_diagnosis, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        rvHospitalDiagnosis.setLayoutManager(new LinearLayoutManager(getContext()));

        notificationMessage = new AlertDialogCustom();
        loader = new Loader(getContext());

        initComponents(view);
    }

    private void initComponents(View view) {
        presenter = new DiagnosisPresenter();
        presenter.attachView(this);

        loader.startLad();
        loader.setMessage("Loading resources...");
        presenter.loadAllDiagnosis();

        edSearchDiagnosis.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

    @Override
    public void onItemClick(int position) {

    }

    @Override
    public void onDisplayErrorDialog(String message) {
        loader.stopLoad();
        notificationMessage.showMe(
                getContext(),
                notificationMessage.HOLD_ON_title,
                ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onDisplayDiagnosis(List<Diagnosis> diagnosisList) {
        loader.stopLoad();
        diagnosisAdapter = new DiagnosisAdapter(getContext(), diagnosisList, this);
        rvHospitalDiagnosis.setAdapter(diagnosisAdapter);
    }

}

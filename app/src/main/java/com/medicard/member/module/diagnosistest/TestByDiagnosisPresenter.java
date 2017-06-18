package com.medicard.member.module.diagnosistest;

import android.content.Context;

import java.util.List;

import database.dao.TestDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.ServiceGenerator;
import services.client.ProcedureClient;
import services.response.ProcedureByDiagnosisCodeResponse;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class TestByDiagnosisPresenter implements TestByDiagnosisMvp.Presenter {


    private Context context;
    private TestByDiagnosisMvp.View testView;

    private ProcedureClient procedureClient;

    private TestDao testDao;

    public TestByDiagnosisPresenter(Context context) {
        this.context = context;
        procedureClient = ServiceGenerator.createApiService(ProcedureClient.class);

        testDao = new TestDao(context);
    }

    @Override
    public void attachView(TestByDiagnosisMvp.View view) {
        testView = view;
    }

    @Override
    public void detachView() {
        testView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void loadTestProcedureByDiagnosisCode(String diagnosisCode) {
        procedureClient.getProceduresByDiagnosisCode(diagnosisCode)
                .enqueue(new Callback<ProcedureByDiagnosisCodeResponse>() {
                    @Override
                    public void onResponse(Call<ProcedureByDiagnosisCodeResponse> call, Response<ProcedureByDiagnosisCodeResponse> response) {
                        if (response.isSuccessful()) {
                            List<String> procedureCode = response.body().getProcedures();
                        }
                    }

                    @Override
                    public void onFailure(Call<ProcedureByDiagnosisCodeResponse> call, Throwable t) {

                    }
                });
    }

}

package com.medicard.member.module.diagnosis.fragment;

import android.app.ProgressDialog;
import android.content.Context;

import com.medicard.member.module.diagnosis.fragment.DiagnosisMvp;

import java.util.ArrayList;
import java.util.List;

import database.dao.TestDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import services.ServiceGenerator;
import services.client.DiagnosisClient;
import services.client.TestClient;
import services.model.Diagnosis;
import services.model.Test;
import services.response.DiagnosisResponse;
import services.response.TestResponse;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class DiagnosisPresenter implements DiagnosisMvp.Presenter {


    private DiagnosisMvp.View diagnosisView;
    private DiagnosisClient diagnosisClient;

    private TestDao testDao;
    private TestClient testClient;

    public DiagnosisPresenter(Context context) {
//        diagnosisClient = AppService.createApiService(DiagnosisClient.class, AppInterface.ENDPOINT);
        diagnosisClient = ServiceGenerator.createApiService(DiagnosisClient.class);
        testClient = ServiceGenerator.createApiService(TestClient.class);

        testDao = new TestDao(context);
    }

    @Override
    public void attachView(DiagnosisMvp.View view) {
        this.diagnosisView = view;
    }

    @Override
    public void detachView() {
        this.diagnosisView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void loadAllDiagnosis() {
        diagnosisClient.getDiagnosisList()
                .enqueue(new Callback<DiagnosisResponse>() {
                    @Override
                    public void onResponse(Call<DiagnosisResponse> call, Response<DiagnosisResponse> response) {
                        Timber.d("response raw %s", response.raw().toString());
                        if (response.isSuccessful()) {
                            DiagnosisResponse diagnosisResponse = response.body();
                            loadAllTest(diagnosisResponse);
//                            diagnosisView.onDisplayDiagnosis(diagnosisResponse.getDiagnosisList());
                        } else {
                            diagnosisView.onDisplayErrorDialog("An error occured.");
                        }
                    }

                    @Override
                    public void onFailure(Call<DiagnosisResponse> call, Throwable t) {
                            Timber.d("error message : %s", t.toString());
                        diagnosisView.onDisplayErrorDialog(t.toString());
                    }
                });
    }




    public void loadAllTest(final DiagnosisResponse diagnosisResponse) {
        testClient.getAllTest()
                .enqueue(new Callback<TestResponse>() {
                    @Override
                    public void onResponse(Call<TestResponse> call, Response<TestResponse> response) {
                        if (response.isSuccessful()) {
                            List<Test> tests = response.body().getTestsList();
                            testDao.deleteAll();
                            Boolean success = testDao.bulkinsert(tests);
                            if (success) {
                                diagnosisView.onDisplayDiagnosis(diagnosisResponse.getDiagnosisList());
                            } else {
                                diagnosisView.onDisplayErrorDialog("An error occured.");
                            }

                        } else {
                            diagnosisView.onDisplayErrorDialog("An error occured.");
                        }
                    }

                    @Override
                    public void onFailure(Call<TestResponse> call, Throwable t) {
                        diagnosisView.onDisplayErrorDialog(t.toString());
                    }
                });
    }

    @Override
    public void filterDianosis(List<Diagnosis> diagnosisList, String query) {
        try {
            query = query.toLowerCase();
            List<Diagnosis> diagnosises = new ArrayList<>();
            for (Diagnosis diagnosis : diagnosisList) {
                String daignosisDescription =
                        diagnosis.getDiagDesc() != null ? diagnosis.getDiagDesc().toLowerCase() : "";

                if (daignosisDescription.contains(query)) {
                    diagnosises.add(diagnosis);
                }
            }

            diagnosisView.displayFilteredDiagnosis(diagnosises);
        } catch (Exception e) {
            Timber.d("error message %s", e.toString());
        }
    }



}

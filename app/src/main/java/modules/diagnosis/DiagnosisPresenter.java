package modules.diagnosis;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import services.client.DiagnosisClient;
import services.model.Diagnosis;
import services.response.DiagnosisResponse;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class DiagnosisPresenter implements DiagnosisMvp.Presenter {


    private DiagnosisMvp.View diagnosisView;
    private DiagnosisClient diagnosisClient;



    public DiagnosisPresenter() {
        diagnosisClient = AppService.createApiService(DiagnosisClient.class, AppInterface.ENDPOINT);
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
                            diagnosisView.onDisplayDiagnosis(diagnosisResponse.getDiagnosisList());
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

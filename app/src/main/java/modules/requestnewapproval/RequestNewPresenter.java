package modules.requestnewapproval;

import android.content.Context;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import database.dao.DoctorDao;
import database.dao.HospitalDao;
import database.dao.ProcedureDao;
import model.newtest.DiagnosisDetails;
import model.newtest.DiagnosisProcedure;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import services.client.DiagnosisClient;
import services.model.Diagnosis;
import services.model.HospitalsToDoctor;
import services.model.Procedure;
import services.response.DiagnosisResponse;
import timber.log.Timber;
import utilities.DiagnosisUtils;

/**
 * Created by casjohnpaul on 5/30/2017.
 */

public class RequestNewPresenter implements RequestNewMvp.Presenter {

    private DiagnosisClient diagnosisClient;

    private HospitalDao hospitalDao;
    private ProcedureDao procedureDao;
    private RequestNewMvp.View requestNewView;

    public RequestNewPresenter(Context context) {
        hospitalDao = new HospitalDao(context);
        procedureDao = new ProcedureDao(context);
        diagnosisClient = AppService.createApiService(DiagnosisClient.class, AppInterface.ENDPOINT);
    }

    @Override
    public void attachView(RequestNewMvp.View view) {
        this.requestNewView = view;
    }

    @Override
    public void detachView() {
        this.requestNewView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void loadDoctorDetails(HospitalsToDoctor doctor) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(doctor.getFullName())
                .append("\n")
                .append("")
                .append(doctor.getSpecDesc());
    }

    @Override
    public void loadDiagnosisTest(final List<DiagnosisProcedure> diagnosisProcedures) {
        diagnosisClient.getDiagnosisList()
                .enqueue(new Callback<DiagnosisResponse>() {
                    @Override
                    public void onResponse(Call<DiagnosisResponse> call, Response<DiagnosisResponse> response) {
                        if (response.isSuccessful()) {

                            Gson gson = new Gson();

                            List<DiagnosisDetails> diagnosisDetails = new ArrayList<>();

                            DiagnosisResponse diagnosisResponse = response.body();
                            List<Diagnosis> diagnosisList = diagnosisResponse.getDiagnosisList();
                            List<String> allOriginalDiagnosisCode = DiagnosisUtils.getAllOriginalDiagnosisCode(diagnosisProcedures);

                            List<Diagnosis> allDiagnosisByCode =
                                    DiagnosisUtils.getAllDignosisByCode(diagnosisList, allOriginalDiagnosisCode);

                            /*for (DiagnosisProcedure dp : diagnosisProcedures) {
                                for (Diagnosis diagnosis : allDignosisByCode) {
                                    if (dp.getDiagnosisCode().equalsIgnoreCase(diagnosis.getDiagCode())) {

                                    }
                                }
                            }*/
                            for (Diagnosis diagnosis : allDiagnosisByCode) {
                                List<Procedure> procedures = new ArrayList<Procedure>();
                                for (DiagnosisProcedure diagnosisProcedure : diagnosisProcedures) {
                                    if (diagnosis.getDiagCode().equals(diagnosisProcedure.getDiagnosisCode())) {
                                        Procedure procedure = procedureDao.find(diagnosisProcedure.getProcedureCode());
                                        procedures.add(procedure);
                                    }
                                }
                                diagnosisDetails.add(new DiagnosisDetails(diagnosis, procedures));
                            }

                            /*for (DiagnosisDetails details : diagnosisDetails) {
                                Timber.d("diagnosis description : %s", details.getDiagnosis().getDiagDesc());
                                Set<Procedure> procedures = new LinkedHashSet<Procedure>(details.getProcedures());
                                for (Procedure procedure : procedures) {
                                    Timber.i("procedure %s, procedureCode %s", procedure.getProcedureDesc(), procedure.getProcedureCode());
                                }
                            }*/
                            Set<DiagnosisDetails> uniqueDetails = new LinkedHashSet<>(diagnosisDetails);
                            requestNewView.displayDiagnosisDetails(new ArrayList<>(uniqueDetails));

                        }
                    }

                    @Override
                    public void onFailure(Call<DiagnosisResponse> call, Throwable t) {
                        Timber.e("errorMessage#%s", t.toString());
                    }
                });

    }



}

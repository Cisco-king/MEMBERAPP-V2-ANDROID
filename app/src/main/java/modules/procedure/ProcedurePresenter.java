package modules.procedure;

import android.content.Context;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import database.dao.ProcedureDao;
import model.newtest.DiagnosisProcedure;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import services.client.ProcedureClient;
import services.model.Procedure;
import services.response.ProcedureByDiagnosisCodeResponse;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/13/2017.
 */
public class ProcedurePresenter implements ProcedureMvp.Presenter {


    private ProcedureClient procedureClient;
    private ProcedureDao procedureDao;

    private ProcedureMvp.View procedureView;


    public ProcedurePresenter(Context context) {
        procedureClient = AppService.createApiService(ProcedureClient.class, AppInterface.ENDPOINT);
        procedureDao = new ProcedureDao(context);
    }

    @Override
    public void attachView(ProcedureMvp.View view) {
        procedureView = view;
    }

    @Override
    public void detachView() {
        procedureView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void loadProcedureByDiagnosisCode(final String diagnosisCode, final List<DiagnosisProcedure> diagnosisProcedures) {

        procedureClient.getProceduresByDiagnosisCode(diagnosisCode)
                .enqueue(new Callback<ProcedureByDiagnosisCodeResponse>() {
                    @Override
                    public void onResponse(Call<ProcedureByDiagnosisCodeResponse> call, Response<ProcedureByDiagnosisCodeResponse> response) {
                        Timber.d("response %s", response.raw().toString());

                        List<Procedure> all = procedureDao.findAll();
                        Timber.d("all procedures %s", all.size());

                        List<Procedure> procedures = new ArrayList<>();
                        if (response.isSuccessful()) {
                            Timber.d("response success");

                            List<String> proceduresCode = response.body().getProcedures();
                            for (String code : proceduresCode) {
                                Procedure procedure = procedureDao.find(code);
                                if (procedure != null) {
                                    Timber.d("id %s procedureDesc %s isSelected %s", procedure.getId(), procedure.getProcedureDesc(), procedure.isSelected());
                                    procedures.add(procedure);
                                } else {
                                    Timber.d("procedures is block");
                                }
                            }
                            Timber.d("procedures %s", procedures.size());
//                            procedureView.displayProcedureByCodeResult(procedures);

                            List<DiagnosisProcedure> filteredDiagnosisProcedures =
                                    filterDiagnosisProcedureByDiagnosisCode(diagnosisProcedures, diagnosisCode);

                            List<Procedure> setableProcedureList = getSetableProcedureList(filteredDiagnosisProcedures, procedures);

                            procedureView.displayProcedureByCodeResult(setableProcedureList);
                        }
                    }

                    @Override
                    public void onFailure(Call<ProcedureByDiagnosisCodeResponse> call, Throwable t) {
                        Timber.d("error message %s", t.toString());
                    }
                });
    }

    @Override
    public void loadAllProcedures() {
        List<Procedure> all = procedureDao.findAll();
        procedureView.displayProcedureByCodeResult(all);
    }

    private List<DiagnosisProcedure> filterDiagnosisProcedureByDiagnosisCode(List<DiagnosisProcedure> diagnosisProcedures, String diagnosisCode) {
        List<DiagnosisProcedure> diagnosisProceduresByCode = new ArrayList<>();

        Timber.d("the size of diagnosisprocedure %s", diagnosisProcedures.size());
        for (DiagnosisProcedure diagnosisProcedure : diagnosisProcedures) {
            if (diagnosisProcedure.getDiagnosisCode().contains(diagnosisCode)) {
                diagnosisProceduresByCode.add(diagnosisProcedure);
            }
        }
        return diagnosisProceduresByCode;
    }

    private List<Procedure> getSetableProcedureList(List<DiagnosisProcedure> diagnosisProcedures, List<Procedure> procedures) {

        Set<Procedure> setableObjects = new LinkedHashSet<>();

        // search for same Procedure in the diagnosisProcedure if exist set the selectable to true
        for (Procedure procedure : procedures) {
            for (DiagnosisProcedure diagnosisProcedure : diagnosisProcedures) {
                Timber.i("diagnosisProcedure %s == %s", diagnosisProcedure.getProcedureCode(), procedure.getProcedureCode());
                if (diagnosisProcedure.getProcedureCode().equalsIgnoreCase(procedure.getProcedureCode())) {
                    procedure.setSelected(true);
                }
            }
            setableObjects.add(procedure);
        }

        return new ArrayList<>(setableObjects);
    }

    @Override
    public void updateProcedureSelectStatus(List<Procedure> procedures) {
        /*for (Procedure procedure : procedures) {
            procedureDao.update(procedure);
        }*/
    }

}

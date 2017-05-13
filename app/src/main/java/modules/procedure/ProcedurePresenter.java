package modules.procedure;

import android.content.Context;

import database.dao.ProcedureDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import services.client.ProcedureClient;
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
    public void loadProcedureByDiagnosisCode(String diagnosisCode) {

        procedureClient.getProceduresByDiagnosisCode(diagnosisCode)
                .enqueue(new Callback<ProcedureByDiagnosisCodeResponse>() {
                    @Override
                    public void onResponse(Call<ProcedureByDiagnosisCodeResponse> call, Response<ProcedureByDiagnosisCodeResponse> response) {
                        Timber.d("response %s", response.raw().toString());
                        if (response.isSuccessful()) {
                            Timber.d("response success");
                            Timber.d("response result number %s", response.body().getProcedures().get(0));
                        }
                    }

                    @Override
                    public void onFailure(Call<ProcedureByDiagnosisCodeResponse> call, Throwable t) {
                        Timber.d("error message %s", t.toString());
                    }
                });

    }

}

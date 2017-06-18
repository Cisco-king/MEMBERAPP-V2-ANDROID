package modules.procedure;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.medicard.member.R;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.newtest.DiagnosisProcedure;
import modules.base.activities.BaseActivity;
import modules.prescriptionattachment.PrescriptionAttachmentActivity;
import modules.procedure.adapter.ProcedureAdapter;
import services.model.Diagnosis;
import services.model.Procedure;
import timber.log.Timber;
import utilities.SharedPref;
import utilities.ViewUtilities;

public class ProcedureActivity extends BaseActivity implements ProcedureMvp.View {

    public static final String KEY_DIAGNOSIS = "diagnosisKey";
    public static final String KEY_DIAGNOSIS_LIST = "diagnosisKeyList";
    public static final String KEY_SELECTED_DIAGNOSIS = "selectedDiagnosisKey";

    public static final String KEY_DISPLAY_ALL = "displayAll";

    public static final String KEY_DONE = "procedureDone";


    @BindView(R.id.rvProcedures) RecyclerView rvProcedures;
    @BindView(R.id.edSearchProcedures) EditText edSearchProcedures;

    @BindView(R.id.fbDone) FancyButton fbDone;
    @BindView(R.id.fbAddMoreDiagnosis) FancyButton fbAddMoreDiagnosis;

    @BindView(R.id.tvNoProcedures) TextView tvNoProcedures;

    private Diagnosis diagnosis;
    private ProcedureMvp.Presenter presenter;

    private List<Procedure> procedures;
    private List<DiagnosisProcedure> diagnosisProcedures;

    Gson gson;

    private ProcedureAdapter procedureAdapter;

    private boolean displayAll;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_procedure;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");

        gson = new Gson();
        diagnosisProcedures = new ArrayList<>();

        Type founderListType = new TypeToken<ArrayList<DiagnosisProcedure>>(){}.getType();

        displayAll = getIntent().getBooleanExtra(KEY_DISPLAY_ALL, false);

        // determine if gonna show all diagnosis or not
        String diagnosisJson = getIntent().getStringExtra(KEY_DIAGNOSIS_LIST);
        if (diagnosisJson != null && !diagnosisJson.equals("") && diagnosisJson.length() > 0) {
            diagnosisProcedures = gson.fromJson(diagnosisJson, founderListType);
        }

        if (!displayAll) {
            diagnosis = getIntent().getParcelableExtra(KEY_DIAGNOSIS);

            if (diagnosis == null) {
                Timber.d("finish this activity");
            }
        }

        presenter = new ProcedurePresenter(this);
        presenter.attachView(this);

        procedures = new ArrayList<>();

        rvProcedures.setLayoutManager(new LinearLayoutManager(this));

        if (displayAll) {
            presenter.loadAllProcedures();
        } else {
            presenter.loadProcedureByDiagnosisCode(diagnosis.getDiagCode(), diagnosisProcedures);
        }
    }

    @OnClick(R.id.fbDone)
    public void done() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(KEY_SELECTED_DIAGNOSIS, getSelectedProcedures());
        intent.putExtra(KEY_DIAGNOSIS, diagnosis);
        intent.putExtra(KEY_DONE, true);
        intent.putExtra(KEY_DISPLAY_ALL, displayAll);

        setResult(RESULT_OK, intent);
        finish();
    }

    @OnClick(R.id.fbAddMoreDiagnosis)
    public void addMoreDiagnosis() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(KEY_SELECTED_DIAGNOSIS, getSelectedProcedures());
        intent.putExtra(KEY_DIAGNOSIS, diagnosis);
        intent.putExtra(KEY_DONE, false);
        intent.putExtra(KEY_DISPLAY_ALL, displayAll);
//        presenter.updateProcedureSelectStatus(this.procedures);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void displayProcedureByCodeResult(List<Procedure> procedures) {
        for (Procedure procedure : procedures) {
            Timber.d("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
            Timber.d("name : %s isSelected %s", procedure.getProcedureDesc(), procedure.isSelected());
        }
        if (procedures != null && procedures.size() > 0) {
            ViewUtilities.showView(rvProcedures);
            ViewUtilities.hideView(tvNoProcedures);

            this.procedures = procedures;

            procedureAdapter = new ProcedureAdapter(this.procedures);
            rvProcedures.setAdapter(procedureAdapter);
        } else {
            ViewUtilities.hideView(rvProcedures);
            ViewUtilities.showView(tvNoProcedures);

            tvNoProcedures.setText("No Procedure(s) find on ");
            tvNoProcedures.append(diagnosis.getDiagDesc());
            tvNoProcedures.append(" Diagnosis");
        }
    }

    public ArrayList<Procedure> getSelectedProcedures() {
        ArrayList<Procedure> selectedProcedures = new ArrayList<>();
        for (Procedure procedure : procedures) {
            if (procedure.isSelected()) {
                selectedProcedures.add(procedure);
            }
        }

        return selectedProcedures;
    }

}

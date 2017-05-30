package modules.procedure;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import modules.base.activities.BaseActivity;
import modules.prescriptionattachment.PrescriptionAttachmentActivity;
import modules.procedure.adapter.ProcedureAdapter;
import services.model.Diagnosis;
import services.model.Procedure;
import timber.log.Timber;

public class ProcedureActivity extends BaseActivity implements ProcedureMvp.View {

    public static final String KEY_DIAGNOSIS = "diagnosisKey";
    public static final String KEY_SELECTED_DIAGNOSIS = "selectedDiagnosisKey";


    @BindView(R.id.rvProcedures) RecyclerView rvProcedures;
    @BindView(R.id.edSearchProcedures) EditText edSearchProcedures;

    @BindView(R.id.fbDone) FancyButton fbDone;
    @BindView(R.id.fbAddMoreDiagnosis) FancyButton fbAddMoreDiagnosis;

    private Diagnosis diagnosis;
    private ProcedureMvp.Presenter presenter;

    private List<Procedure> procedures;

    private ProcedureAdapter procedureAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_procedure;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");

        diagnosis = getIntent().getParcelableExtra(KEY_DIAGNOSIS);
        if (diagnosis == null) { finish(); Timber.d("finish this activity"); }

        presenter = new ProcedurePresenter(this);
        presenter.attachView(this);

        procedures = new ArrayList<>();

        rvProcedures.setLayoutManager(new LinearLayoutManager(this));

        presenter.loadProcedureByDiagnosisCode(diagnosis.getDiagCode());

    }

    @OnClick(R.id.fbDone)
    public void done() {
        startActivity(new Intent(this, PrescriptionAttachmentActivity.class));
        finish();
    }

    @OnClick(R.id.fbAddMoreDiagnosis)
    public void addMoreDiagnosis() {
        Intent intent = new Intent();
        intent.putParcelableArrayListExtra(KEY_SELECTED_DIAGNOSIS, getSelectedProcedures());
        presenter.updateProcedureSelectStatus(this.procedures);

        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void displayProcedureByCodeResult(List<Procedure> procedures) {
        this.procedures = procedures;
        procedureAdapter = new ProcedureAdapter(this.procedures);
        rvProcedures.setAdapter(procedureAdapter);
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

package modules.procedure;

import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import modules.base.activities.BaseActivity;
import services.model.Diagnosis;
import services.model.Procedure;
import timber.log.Timber;

public class ProcedureActivity extends BaseActivity implements ProcedureMvp.View {

    public static final String KEY_DIAGNOSIS = "diagnosisKey";


    @BindView(R.id.rvProcedures) RecyclerView rvProcedures;
    @BindView(R.id.edSearchProcedures) EditText edSearchProcedures;

    @BindView(R.id.fbDone) FancyButton fbDone;
    @BindView(R.id.fbAddMoreDiagnosis) FancyButton fbAddMoreDiagnosis;

    Diagnosis diagnosis;
    ProcedureMvp.Presenter presenter;

    List<Procedure> procedures;

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
        procedures = new ArrayList<>();

        presenter.loadProcedureByDiagnosisCode(diagnosis.getDiagCode());

    }

    @OnClick(R.id.fbDone)
    public void done() {

    }

    @OnClick(R.id.fbAddMoreDiagnosis)
    public void addMoreDiagnosis() {
        setResult(RESULT_OK);
        finish();
    }

    @Override
    public void displayProcedureByCodeResult(List<Procedure> procedures) {
        this.procedures = procedures;
    }

}

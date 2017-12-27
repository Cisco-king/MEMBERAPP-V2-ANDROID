package modules.newtest;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;

import com.google.gson.Gson;
import com.medicard.member.R;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import database.table.Table;
import mehdi.sakout.fancybuttons.FancyButton;
import model.HospitalList;
import model.newtest.DiagnosisProcedure;
import model.newtest.NewTestRequest;
import modules.base.activities.BaseActivity;
import modules.diagnosis.DiagnosisFragment;
import modules.doctor.DoctorFragment;
import modules.hospital.HospitalFragment;
import modules.prescriptionattachment.PrescriptionAttachmentActivity;
import modules.procedure.ProcedureActivity;
import modules.requestforconsult.RequestForConsultFragment;
import services.model.Diagnosis;
import services.model.HospitalsToDoctor;
import services.model.Procedure;
import timber.log.Timber;
import utilities.SharedPref;
import utilities.ViewUtilities;

public class NewTestActivity extends BaseActivity implements NewTestMvp.View {

    
    public static final int REQUEST_PROCEDURE_CODE = 110; 

    public static final String CONTENT = "content";

    public static final int REQUEST_FOR_CONSULT = 0;
    public static final int DOCTOR = 1;

    @BindView(R.id.fbSkip) FancyButton fbSkip;
    @BindView(R.id.fbDone) FancyButton fbDone;

    public Set<DiagnosisProcedure> diagnosisProcedures;

    private Fragment[] fragmentLayout = {
            RequestForConsultFragment.newInstance(),
            DoctorFragment.newInstance()
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_new_test;
    }

    @Override
    protected void initViews() {
        super.initViews();
        initComponents();
    }

    private void initComponents() {
        diagnosisProcedures = new LinkedHashSet<>();

        int defaultLayout = getIntent().getIntExtra(CONTENT, REQUEST_FOR_CONSULT);
        displayLayout(defaultLayout);

        fbSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayAllDiagnosisProcedure();
            }
        });
    }

    private void displayLayout(int layoutPosition) {

        Fragment fragment = fragmentLayout[layoutPosition];
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flNewTest, fragment, null)
                .commit();

    }

    @Override
    public void displayDoctorView() {
        displayLayout(DOCTOR);
    }

    @Override
    public void displayHospitalView(HospitalsToDoctor doctor) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flNewTest, HospitalFragment.newInstance(doctor), null)
                .commit();
    }

    @Override
    public void displayDiagnosis(HospitalList hospitalList) {
        ViewUtilities.showView(fbSkip);
        ViewUtilities.hideView(fbDone);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flNewTest, DiagnosisFragment.newInstance(hospitalList), null)
                .commit();
    }

    @Override
    public void displayAllDiagnosisProcedure() {
        Intent intent = new Intent(this, ProcedureActivity.class);
        intent.putExtra(ProcedureActivity.KEY_DISPLAY_ALL, true);
        startActivityForResult(intent, REQUEST_PROCEDURE_CODE);
    }

    @Override
    public void onStartDiagnosisProcedure(Diagnosis diagnosis) {
        Gson gson = new Gson();
        String diagnosisProceduresJson = "";

        if (diagnosisProcedures.size() > 0) {
            Timber.d("diagnosis procedures : %s", gson.toJson(diagnosisProcedures));
            diagnosisProceduresJson = gson.toJson(diagnosisProcedures);
        }
        Intent intent = new Intent(this, ProcedureActivity.class);
        intent.putExtra(ProcedureActivity.KEY_DISPLAY_ALL, false);
        intent.putExtra(ProcedureActivity.KEY_DIAGNOSIS, diagnosis);
        intent.putExtra(ProcedureActivity.KEY_DIAGNOSIS_LIST, diagnosisProceduresJson);
        startActivityForResult(intent, REQUEST_PROCEDURE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PROCEDURE_CODE && resultCode == RESULT_OK) {
            ArrayList<Procedure> selectedDiagnosis = data.getParcelableArrayListExtra(ProcedureActivity.KEY_SELECTED_DIAGNOSIS);

            Diagnosis diagnosis = data.getParcelableExtra(ProcedureActivity.KEY_DIAGNOSIS);
            boolean isProcedureDone = data.getBooleanExtra(ProcedureActivity.KEY_DONE, false);
            boolean displayAll = data.getBooleanExtra(ProcedureActivity.KEY_DISPLAY_ALL, false);

            Timber.d("##########################");
            if (selectedDiagnosis != null && selectedDiagnosis.size() > 0) {
                for (Procedure procedure : selectedDiagnosis) {
                    String diagnosisCode = (diagnosis == null) ? "998" : diagnosis.getDiagCode();
                    Timber.d("the diagnosis code is %s", diagnosisCode);

                    DiagnosisProcedure diagnosisProcedure =
                            new DiagnosisProcedure(procedure.getProcedureAmount(), diagnosisCode, procedure.getProcedureCode(), 2);
                    diagnosisProcedures.add(diagnosisProcedure);

                    Timber.d("procedure %s", procedure.getProcedureDesc());
                    Timber.d("diagnosis %s", diagnosisCode);
                }
            }

            if (isProcedureDone) {
                Gson gson = new Gson();
                String diagnosisProceduresJson = "";

                if (diagnosisProcedures.size() > 0) {
                    Timber.d("diagnosis procedures : %s", gson.toJson(diagnosisProcedures));
                    diagnosisProceduresJson = gson.toJson(diagnosisProcedures);
                }

                Timber.d("submitted diagnosisProcedureJson#%s", diagnosisProceduresJson);
                SharedPref.setAppPreference(context, SharedPref.KEY_PROCEDURE_DIAGNOSIS, diagnosisProceduresJson);
                SharedPref.setBoolValue(context, SharedPref.KEY_DISPLAY_ALL_PROCEDURE, displayAll);
//                SharedPref.setAppPreference(context, "");
                startActivity(new Intent(this, PrescriptionAttachmentActivity.class));
            }
        }
    }
}

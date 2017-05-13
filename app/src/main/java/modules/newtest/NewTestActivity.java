package modules.newtest;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.medicard.member.R;

import model.HospitalList;
import modules.base.activities.BaseActivity;
import modules.diagnosis.DiagnosisFragment;
import modules.doctor.DoctorFragment;
import modules.hospital.HospitalFragment;
import modules.procedure.ProcedureActivity;
import modules.requestforconsult.RequestForConsultFragment;
import services.model.Diagnosis;
import services.model.HospitalsToDoctor;
import timber.log.Timber;

public class NewTestActivity extends BaseActivity implements NewTestMvp.View {

    
    public static final int REQUEST_PROCEDURE_CODE = 110; 

    public static final String CONTENT = "content";

    public static final int REQUEST_FOR_CONSULT = 0;
    public static final int DOCTOR = 1;

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
        int defaultLayout = getIntent().getIntExtra(CONTENT, REQUEST_FOR_CONSULT);
        displayLayout(defaultLayout);
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
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flNewTest, DiagnosisFragment.newInstance(hospitalList), null)
                .commit();
    }

    @Override
    public void onStartDiagnosisProcedure(Diagnosis diagnosis) {
        Intent intent = new Intent(this, ProcedureActivity.class);
        intent.putExtra(ProcedureActivity.KEY_DIAGNOSIS, diagnosis);
        startActivityForResult(intent, REQUEST_PROCEDURE_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PROCEDURE_CODE && resultCode == RESULT_OK) {
            Timber.d("request is okay");
        }
    }
}

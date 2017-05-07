package modules.newtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.medicard.member.R;

import database.entity.Doctor;
import modules.base.activities.BaseActivity;
import modules.doctor.DoctorFragment;
import modules.hospital.HospitalFragment;
import modules.requestforconsult.RequestForConsultFragment;

public class NewTestActivity extends BaseActivity implements NewTestMvp.View {


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
    public void displayHospitalView(Doctor doctor) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.flNewTest, HospitalFragment.newInstance(doctor), null)
                .commit();
    }

}

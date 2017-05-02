package modules.selecthospital;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import adapter.HospitalClinicAdapter;
import butterknife.BindView;
import model.HospitalClinic;
import modules.base.activities.TestTrackableActivity;
import modules.diagnosis.DiagnosisActivity;
import modules.requestdoctor.RequestDoctorActivity;
import modules.selecttest.SelectTestActivity;
import timber.log.Timber;

public class SelectHospitalActivity extends TestTrackableActivity {

    public static final String TAG =
            SelectHospitalActivity.class.getSimpleName();

    private boolean fromRequestDoctor = false;


    @BindView(R.id.rvHospitalClickForAvailment) RecyclerView rvHospitalClickForAvailment;

    private List<HospitalClinic> hospitalClinics;
    private HospitalClinicAdapter hospitalClinicAdapter;

    private HospitalClinicAdapter.OnClickListener listener =
            new HospitalClinicAdapter.OnClickListener() {
        @Override
        public void onItemClick(int position) {
            onItemViewClick(position);
        }
    };


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_select_hospital;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");

        fromRequestDoctor = getIntent().getBooleanExtra(RequestDoctorActivity.REQUEST_DOCTOR, false);

        hospitalClinics = new ArrayList<>();
        hospitalClinics = dummies();

        hospitalClinicAdapter = new HospitalClinicAdapter(this, hospitalClinics, listener);

        rvHospitalClickForAvailment.setLayoutManager(new LinearLayoutManager(this));
        rvHospitalClickForAvailment.setAdapter(hospitalClinicAdapter);
    }

    private void onItemViewClick(int position) {
        Timber.d("The button position that click %s", position);
        if (fromRequestDoctor) {
            startActivity(new Intent(this, DiagnosisActivity.class));
        } else {
            startActivity(new Intent(this, SelectTestActivity.class));
        }
    }

    public List<HospitalClinic> dummies() {
        List<HospitalClinic> dummies = new ArrayList<>();
        for (int i = 1; i <= 25; i++) {
            dummies.add(
                    new HospitalClinic.Builder()
                            .name("Medicard Hospital " + i)
                            .build());
        }
        return dummies;
    }

}

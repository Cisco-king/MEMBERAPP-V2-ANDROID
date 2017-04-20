package modules.selecthospital;

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

public class SelectHospitalActivity extends TestTrackableActivity {

    public static final String TAG =
            SelectHospitalActivity.class.getSimpleName();


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

        hospitalClinics = new ArrayList<>();
        hospitalClinics = dummies();

        hospitalClinicAdapter = new HospitalClinicAdapter(this, hospitalClinics, listener);

        rvHospitalClickForAvailment.setLayoutManager(new LinearLayoutManager(this));
        rvHospitalClickForAvailment.setAdapter(hospitalClinicAdapter);
    }

    private void onItemViewClick(int position) {
        Log.d(TAG, "onItemViewClick: " + hospitalClinics.get(position).getName());
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

package modules.selecthospital;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import adapter.HospitalClinicAdapter;
import butterknife.BindView;
import model.HospitalClinic;
import model.HospitalList;
import modules.base.activities.TestTrackableActivity;
import modules.diagnosis2.DiagnosisActivity;
import modules.hospital.HospitalMvp;
import modules.hospital.HospitalPresenter;
import modules.requestdoctor2.RequestDoctorActivity;
import modules.selecttest.SelectTestActivity;
import timber.log.Timber;
import utilities.AlertDialogCustom;

/**
 * This is used activities
 */
public class SelectHospitalActivity extends TestTrackableActivity implements HospitalMvp.View {

    public static final String TAG =
            SelectHospitalActivity.class.getSimpleName();

    private boolean fromRequestDoctor = false;

    @BindView(R.id.rvHospitalClickForAvailment) RecyclerView rvHospitalClickForAvailment;

    private HospitalClinicAdapter hospitalClinicAdapter;

    /**
     * see the implementation {@link modules.hospital.HospitalPresenter}
     */
    private HospitalMvp.Presenter presenter;

    List<HospitalList> hospitals;

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

        presenter = new HospitalPresenter(this);
        presenter.attachView(this);

        hospitals = new ArrayList<>();

        fromRequestDoctor = getIntent().getBooleanExtra(RequestDoctorActivity.REQUEST_DOCTOR, false);

        hospitalClinicAdapter = new HospitalClinicAdapter(this, hospitals, listener);

        rvHospitalClickForAvailment.setLayoutManager(new LinearLayoutManager(this));
        rvHospitalClickForAvailment.setAdapter(hospitalClinicAdapter);

        presenter.loadHospitalClinic();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void onItemViewClick(int position) {
        Timber.d("The button position that click %s", position);
        AlertDialogCustom.alertNotification(this, getString(R.string.warning), R.string.mc_06, new AlertDialogCustom.OnDialogClickListener() {
            @Override
            public void onOkClick() {
                startActivity(new Intent(SelectHospitalActivity.this, DiagnosisActivity.class));
            }

            @Override
            public void onCancelClick() {
                Timber.d("dimiss was clicked.");
            }
        });
        /*if (fromRequestDoctor) {
            startActivity(new Intent(this, DiagnosisActivity.class));
        } else {
            startActivity(new Intent(this, SelectTestActivity.class));
        }*/
    }


    @Override
    public void displayHospitalClinic(List<HospitalList> hospitals) {
        this.hospitals = hospitals;
        hospitalClinicAdapter.update(hospitals);
    }

    @Override
    public void displayFilterHospitalClinics(List<HospitalList> hospitalLists) {

    }

}

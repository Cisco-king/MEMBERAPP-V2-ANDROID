package modules.requestdoctor2;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RequestDoctorAdapter;
import butterknife.BindView;
import modules.base.activities.TestTrackableActivity;
import modules.selecthospital.SelectHospitalActivity;
import timber.log.Timber;

public class RequestDoctorActivity extends TestTrackableActivity implements RequestDoctorAdapter.OnItemClickListener {


    public static final String REQUEST_DOCTOR = "requestDoctor";

    @BindView(R.id.rvSelectDoctors) RecyclerView rvSelectDoctors;

    private List<String> doctors;
    private RequestDoctorAdapter doctorAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_request_doctor;
    }

    @Override
    protected void initViews() {
        super.initViews();

        doctors = new ArrayList<>();
        doctors = dummyDoctors();

        doctorAdapter = new RequestDoctorAdapter(context, doctors, this);
        Timber.d("doctor count %s", doctorAdapter.getItemCount());

        rvSelectDoctors.setLayoutManager(new LinearLayoutManager(this));
        rvSelectDoctors.setAdapter(doctorAdapter);
    }

    public List<String> dummyDoctors() {
        List<String> dummys = new ArrayList<>();
        for (int n = 1; n <= 10; n++) dummys.add("Doctor Name " + n);

        return dummys;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, SelectHospitalActivity.class);
        intent.putExtra(REQUEST_DOCTOR, true);
        startActivity(intent);
    }
}

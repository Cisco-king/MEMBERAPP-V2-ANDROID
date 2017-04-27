package modules.requestdoctor;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RequestDoctorAdapter;
import butterknife.BindView;
import model.Doctor;
import modules.base.activities.TestTrackableActivity;

public class RequestDoctorActivity extends TestTrackableActivity {


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

        doctors = dummyDoctors();
        doctorAdapter = new RequestDoctorAdapter(this, doctors);

        rvSelectDoctors.setLayoutManager(new LinearLayoutManager(this));
        rvSelectDoctors.setAdapter(doctorAdapter);
    }

    public List<String> dummyDoctors() {
        List<String> dummys = new ArrayList<>();
        for (int n = 1; n <= 10; n++) dummys.add("Doctor Name " + n);

        return dummys;
    }

}

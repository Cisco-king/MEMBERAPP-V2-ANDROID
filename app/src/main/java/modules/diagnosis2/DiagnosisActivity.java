package modules.diagnosis2;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import adapter.RequestDoctorAdapter;
import butterknife.BindView;
import modules.base.activities.TestTrackableActivity;
import modules.diagnosistest.DiagnosisTestActivity;

public class DiagnosisActivity extends TestTrackableActivity
        implements RequestDoctorAdapter.OnItemClickListener {


    public static final String DIAGNOSIS = "diagnosis";

    @BindView(R.id.rvDiagnosis) RecyclerView rvDiagnosis;

    private List<String> diagnosis;
    private RequestDoctorAdapter diagnosisAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_diagnosis;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");

        diagnosis = new ArrayList<>();
        diagnosis = dummyDiagnosis();

        diagnosisAdapter = new RequestDoctorAdapter(context, diagnosis, this);

        rvDiagnosis.setLayoutManager(new LinearLayoutManager(this));
        rvDiagnosis.setAdapter(diagnosisAdapter);
    }

    public List<String> dummyDiagnosis() {
        List<String> dummys = new ArrayList<>();
        for (int n = 1; n <= 10; n++) dummys.add("Diagnosis " + n);

        return dummys;
    }

    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(this, DiagnosisTestActivity.class);
        intent.putExtra(DIAGNOSIS, diagnosis.get(position));
        startActivity(intent);
    }
}

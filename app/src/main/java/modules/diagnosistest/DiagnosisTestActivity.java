package modules.diagnosistest;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.ItemSelectTest;
import modules.base.activities.TestTrackableActivity;
import modules.diagnosis.DiagnosisActivity;
import modules.diagnosistest.adapter.DiagnosisTestAdapter;
import modules.selecttest.adapter.SelectTestAdapter;
import modules.testattachment.TestAttachmentActivity;
import timber.log.Timber;

public class DiagnosisTestActivity extends TestTrackableActivity {


    @BindView(R.id.rvDiagnosisTest) RecyclerView rvDiagnosisTest;
    @BindView(R.id.diagnosisTitle) TextView diagnosisTitle;

    @BindView(R.id.fbDone) FancyButton fbDone;
    @BindView(R.id.btnAddMoreDiagnosis) Button btnAddMoreDiagnosis;

    private List<String> diagnosis;
    private DiagnosisTestAdapter diagnosisTestAdapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_diagnosis_test;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");

        try {
            String selectedDiagnosisTitle = getIntent().getStringExtra(DiagnosisActivity.DIAGNOSIS);
            diagnosisTitle.append(selectedDiagnosisTitle);
        } catch (Exception e) {
            Timber.d("%s", e.toString());
        }


        diagnosis = new ArrayList<>();
        diagnosis = dummyDiagnosisTest();

        diagnosisTestAdapter = new DiagnosisTestAdapter(context, diagnosis);
        rvDiagnosisTest.setLayoutManager(new LinearLayoutManager(this));
        rvDiagnosisTest.setAdapter(diagnosisTestAdapter);
    }

    @OnClick(R.id.fbDone)
    public void onDoneClick() {
        startActivity(new Intent(this, TestAttachmentActivity.class));
    }

    @OnClick(R.id.btnAddMoreDiagnosis)
    public void onAddMoreDiagnosisClick() {
        finish();
    }

    public List<String> dummyDiagnosisTest() {
        List<String> dummys = new ArrayList<>();
        for (int n = 1; n <= 10; n++) dummys.add("Test " + n);

        return dummys;
    }


}

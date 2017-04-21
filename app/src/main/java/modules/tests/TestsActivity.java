package modules.tests;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import adapter.TestAdapter;
import butterknife.BindView;
import butterknife.OnClick;
import model.DoctorTest;
import modules.base.activities.TestTrackableActivity;
import modules.consultation.ConsultationDetailsActivity;

public class TestsActivity extends TestTrackableActivity
        implements Tests.View {

    public static final String TAG =
            TestsActivity.class.getSimpleName();

    public static final String BASIC_TEST = "Basic Test";

    @BindView(R.id.rvRequestTest)
    RecyclerView rvRequestTest;

    @BindView(R.id.btnRequestNewApproval) Button btnRequestNewApproval;

    private Tests.Presenter presenter;
    private TestAdapter testAdapter;

    private TestAdapter.OnClickListener adapterViewClickListener =
            new TestAdapter.OnClickListener() {
        @Override
        public void onClick(int position) {
            onItemClick(position);
        }
    };

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_tests;
    }

    @Override
    protected void initViews() {
        super.initViews();
        initCustomableToolbarConfig();

        presenter = new TestsPresenter();
        presenter.attachView(this);

        testAdapter = new TestAdapter(this, doctorTestsDummy(), adapterViewClickListener);

        rvRequestTest.setLayoutManager(new LinearLayoutManager(this));
        rvRequestTest.setAdapter(testAdapter);

        Log.d(TAG, "initViews: Model Object " + getMember().toString());

    }

    private void initCustomableToolbarConfig() {
        setToolbarCustomableTitle(BASIC_TEST);
    }

    @Override
    protected void destroyView() {
        super.destroyView();
        presenter.detachView();
    }

    @OnClick(R.id.btnRequestNewApproval)
    public void startRequestNewApproval() {

    }

    public void onItemClick(int position) {
        Intent intent = new Intent(TestsActivity.this, ConsultationDetailsActivity.class);
        startActivity(intent);
    }

    /**
     * <p>
     *     This is for test purposes only
     * </p>
     *
     * @return
     * Dummy DoctorTest Data
     */
    private List<DoctorTest> doctorTestsDummy() {
        List<DoctorTest> doctorTests = new ArrayList<>();
        doctorTests.add(new DoctorTest.Builder()
                .hospitalName("Medicard Lifestyle Center")
                .doctorName("Dr. Stephanie Nonna Paraguas")
                .dateOfConsultation("Date of Consultation: April 2, 2016")
                .build());

        doctorTests.add(new DoctorTest.Builder()
                .hospitalName("Medicard Lifestyle Center")
                .doctorName("Dr. Eric Ong")
                .dateOfConsultation("Date of Consultation: May 2, 2016")
                .build());

        return doctorTests;
    }

}

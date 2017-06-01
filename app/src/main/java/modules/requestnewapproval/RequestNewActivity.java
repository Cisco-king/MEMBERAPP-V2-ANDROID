package modules.requestnewapproval;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.TextView;

import com.medicard.member.R;

import butterknife.BindView;
import modules.base.activities.TestTrackableActivity;
import modules.prescriptionattachment.adapter.AttachmentAdapter;

/**
 * // todo request new approval
 */
public class RequestNewActivity extends TestTrackableActivity
        implements RequestNewMvp.View {

    @BindView(R.id.etReasonForConsult) EditText etReasonForConsult;

    @BindView(R.id.cvRequestDoctor) CardView cvRequestDoctor;
    @BindView(R.id.tvDoctorDetails) TextView tvDoctorDetails;

    @BindView(R.id.cvHospitalClinicForAvailment) CardView cvHospitalClinicForAvailment;
    @BindView(R.id.tvHospitalAvailment) TextView tvHospitalAvailment;

    @BindView(R.id.rvAttachments) RecyclerView rvAttachments;

    private AttachmentAdapter attachmentAdapter;

    private RequestNewMvp.Presenter presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_request_new;
    }

    @Override
    protected void initViews() {
        super.initViews();

        presenter = new RequestNewPresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.detachCallback();
    }

}

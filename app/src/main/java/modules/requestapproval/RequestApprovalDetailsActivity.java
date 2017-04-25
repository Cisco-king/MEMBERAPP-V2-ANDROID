package modules.requestapproval;

import android.widget.Button;

import com.medicard.member.R;

import butterknife.BindView;
import modules.base.activities.TestTrackableActivity;

public class RequestApprovalDetailsActivity extends TestTrackableActivity {


    @BindView(R.id.btnSubmitRequest) Button btnSubmitRequest;
    @BindView(R.id.btnRequestCancel) Button btnRequestCancel;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_request_details;
    }

    @Override
    protected void initViews() {
        super.initViews();
    }

}

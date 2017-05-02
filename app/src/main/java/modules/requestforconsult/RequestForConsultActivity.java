package modules.requestforconsult;

import android.content.Intent;
import android.widget.Button;

import com.medicard.member.R;

import butterknife.BindView;
import butterknife.OnClick;
import modules.base.activities.TestTrackableActivity;
import modules.requestdoctor.RequestDoctorActivity;

public class RequestForConsultActivity extends TestTrackableActivity {


    @BindView(R.id.btnReasonForConsultOk) Button btnReasonForConsultOk;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_request_for_consult;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");
    }

    @OnClick(R.id.btnReasonForConsultOk)
    public void startRequestingDoctor() {
        startActivity(new Intent(this, RequestDoctorActivity.class));
    }


}

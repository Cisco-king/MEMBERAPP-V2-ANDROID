package modules.consultation;

import com.medicard.member.R;

import modules.base.activities.TestTrackableActivity;

public class ConsultationDetailsActivity extends TestTrackableActivity {


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_consultation_details;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Basic Test");
    }

}

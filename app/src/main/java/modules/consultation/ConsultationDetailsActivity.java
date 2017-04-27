package modules.consultation;

import android.content.Intent;
import android.widget.Button;

import com.medicard.member.R;

import butterknife.BindView;
import butterknife.OnClick;
import modules.base.activities.TestTrackableActivity;
import modules.selecthospital.SelectHospitalActivity;
import utilities.AlertDialogCustom;

public class ConsultationDetailsActivity extends TestTrackableActivity {

    @BindView(R.id.btnRequestApproval) Button btnRequestApproval;

    private AlertDialogCustom alertDialogCustom;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_consultation_details;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Basic Test");

        alertDialogCustom = new AlertDialogCustom();
    }

    @OnClick(R.id.btnRequestApproval)
    public void startRequestApproval() {
        if (true) {
            startActivity(new Intent(this, SelectHospitalActivity.class));
        } else {
            alertDialogCustom.showMe(
                    this,
                    alertDialogCustom.HOLD_ON_title,
                    alertDialogCustom.maternity_not_available, 1);
        }
    }

}

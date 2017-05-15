package modules.prescriptionattachment;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.medicard.member.R;

import butterknife.BindView;
import mehdi.sakout.fancybuttons.FancyButton;
import modules.base.activities.BaseActivity;

public class PrescriptionAttachmentActivity extends BaseActivity {

    @BindView(R.id.edSearchPrescribeAttachment) TextView edSearchPrescribeAttachment;
    @BindView(R.id.rvAttachment) RecyclerView rvAttachment;

    @BindView(R.id.fbDone) FancyButton fbDone;

    private PrescriptionAttachmentMvp.Presenter presenter;
//    private

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_prescription_attachment;
    }

    @Override
    protected void initViews() {
        super.initViews();

    }


}

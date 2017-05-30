package modules.requestnewapproval;

import com.medicard.member.R;

import modules.base.activities.TestTrackableActivity;

/**
 * // todo request new approval
 */
public class RequestNewActivity extends TestTrackableActivity implements RequestNewMvp.View {


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

}

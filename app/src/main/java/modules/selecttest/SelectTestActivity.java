package modules.selecttest;

import android.support.v7.widget.RecyclerView;

import com.medicard.member.R;

import butterknife.BindView;
import modules.base.activities.TestTrackableActivity;

public class SelectTestActivity extends TestTrackableActivity implements SelectTest.View {


    @BindView(R.id.rvSelectTest) RecyclerView rvSelectTest;


    @Override
    protected int getLayoutResource() {
        return R.layout.activity_select_test;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");
    }

}

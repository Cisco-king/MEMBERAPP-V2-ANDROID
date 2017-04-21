package modules.selecttest;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import model.ItemSelectTest;
import modules.base.activities.TestTrackableActivity;
import modules.selecttest.adapter.SelectTestAdapter;
import timber.log.Timber;

public class SelectTestActivity extends TestTrackableActivity
        implements SelectTest.View, SelectTestAdapter.OnItemClickListener {


    @BindView(R.id.rvSelectTest) RecyclerView rvSelectTest;

    private List<ItemSelectTest> selectTests;

    private SelectTestAdapter selectTestAdapter;

    private int itemClick = 0;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_select_test;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");

        selectTests = new ArrayList<>();
        selectTests = tests();

        selectTestAdapter = new SelectTestAdapter(context, selectTests, this);
        rvSelectTest.setLayoutManager(new LinearLayoutManager(this));
        rvSelectTest.setAdapter(selectTestAdapter);
    }

    @Override
    public void onItemClick(int position) {
        Timber.d("Click on this position %s ", position );
    }

    @Override
    public void onItemCheck(int position, boolean isCheck) {
        itemClick = isCheck ? (itemClick + 1) : (itemClick - 1);
        Timber.d("Item Position %s and checked %s", position, Boolean.toString(isCheck));
        Timber.d("Total Item Check : %s ", itemClick);
    }

    private List<ItemSelectTest> tests() {
        List<ItemSelectTest> tests = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            tests.add(new ItemSelectTest.Builder()
                    .testName("Test " + i)
                    .costCenter("Cost Center " + i)
                    .diagnosis("Diagnosis " + i)
                    .build());
        }

        return tests;
    }

}

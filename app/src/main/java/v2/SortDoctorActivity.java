package v2;

import android.content.Context;
import android.content.Intent;
import android.medicard.com.medicard.R;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.appyvet.rangebar.IRangeBarFormatter;
import com.appyvet.rangebar.RangeBar;

import java.util.ArrayList;

import InterfaceService.DoctorRetrieve;
import InterfaceService.DoctorSortInterface;
import InterfaceService.DoctorSortRetrieve;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.SpecsAdapter;
import utilities.Constant;

public class SortDoctorActivity extends AppCompatActivity implements DoctorSortInterface {

    @BindView(R.id.tv_sort_by)
    TextView tv_sort_by;
    @BindView(R.id.tv_specialization)
    TextView tv_specialization;
    @BindView(R.id.et_room_number)
    EditText et_room_number;
    @BindView(R.id.rangebar)
    RangeBar rangebar;
    @BindView(R.id.tv_left)
    TextView tv_left;
    @BindView(R.id.tv_right)
    TextView tv_right;
    @BindView(R.id.btn_back)
    FancyButton btn_back;

    @BindView(R.id.btn_show)
    FancyButton btn_show;
    @BindView(R.id.btn_reset)
    FancyButton btn_reset;

    @BindView(R.id.et_search)
    EditText et_search;

    DoctorSortRetrieve implement;
    DoctorSortInterface callback;
    Context context;
    ArrayList<SpecsAdapter> selectedSpec = new ArrayList<>();
    private String sort_by;
    private String search_String;
    private String room_number;
    private String SPEC_SEARCH  = "";
    private int SPECIALIZATION_CALL = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sort_doctor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);
        context = this;
        callback = this;
        implement = new DoctorSortRetrieve(context, callback);
        implement.setSortBy(tv_sort_by);


        search_String = getIntent().getStringExtra(Constant.SEARCH_STRING);
        selectedSpec = getIntent().getParcelableArrayListExtra(Constant.SELECTED_SPECIALIZATION);
        sort_by = getIntent().getStringExtra(Constant.SORT_BY);
        room_number = getIntent().getStringExtra(Constant.ROOM_NUMBER);
        implement.setPrevDataSortAndSearch(search_String, sort_by, tv_sort_by, et_search);
        implement.setSpecText(tv_specialization, selectedSpec);
        implement.setRoom(room_number, et_room_number);

        rangebar.setOnRangeBarChangeListener(new RangeBar.OnRangeBarChangeListener() {
            @Override
            public void onRangeChangeListener(RangeBar rangeBar, int leftPinIndex, int rightPinIndex, String leftPinValue, String rightPinValue) {
                implement.setMessage(leftPinIndex, tv_left);
                implement.setMessage(rightPinIndex, tv_right);
            }
        });


        rangebar.setFormatter(new IRangeBarFormatter() {
            @Override
            public String format(String s) {

                return implement.setMessageData(s);
            }
        });


    }


    @OnClick({R.id.btn_reset, R.id.tv_sort_by, R.id.btn_back, R.id.tv_specialization, R.id.btn_show})
    public void onCLick(View view) {


        switch (view.getId()) {
            case R.id.tv_sort_by:
                implement.showSortBy();
                break;
            case R.id.btn_back:
                finish();
                break;

            case R.id.tv_specialization:

                Intent gotoSelection1 = new Intent(SortDoctorActivity.this, SelectProvinceActivity.class);
                gotoSelection1.putExtra(Constant.SELECT, Constant.SELECT_SPECIALIZATION);
                gotoSelection1.putExtra(Constant.SPEC_SEARCH, SPEC_SEARCH );
                gotoSelection1.putParcelableArrayListExtra(Constant.SELECTED_SPECIALIZATION, selectedSpec);
                startActivityForResult(gotoSelection1, SPECIALIZATION_CALL);
                break;

            case R.id.btn_show:

                Intent intent = new Intent();
                intent.putExtra(Constant.SEARCH_STRING, et_search.getText().toString().trim());
                intent.putExtra(Constant.SORT_BY, tv_sort_by.getText().toString().trim());
                Log.d("SORT", tv_sort_by.getText().toString().trim());
                intent.putExtra(Constant.ROOM_NUMBER, et_room_number.getText().toString().trim());
                intent.putParcelableArrayListExtra(Constant.SELECTED_SPECIALIZATION, selectedSpec);
                setResult(RESULT_OK, intent);
                finish();
                break;

            case R.id.btn_reset:
                search_String = "";
                sort_by = "";
                implement.setPrevDataSortAndSearch(search_String, sort_by, tv_sort_by, et_search);
                selectedSpec.clear();
                implement.setSpecText(tv_specialization, selectedSpec);
                room_number = "";
                implement.setRoom(room_number, et_room_number);
                break;

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == SPECIALIZATION_CALL && resultCode == RESULT_OK) {
            SPEC_SEARCH = data.getStringExtra(Constant.SPEC_SEARCH) ;
            selectedSpec = data.getParcelableArrayListExtra("SPECIALIZATION");
            implement.setSpecText(tv_specialization, selectedSpec);
        }


        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onSortListener(String sortBy) {
        tv_sort_by.setText(sortBy);

    }
}

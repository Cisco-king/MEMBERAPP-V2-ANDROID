package mockup;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.medicard.member.R;

import java.util.ArrayList;

import adapter.MockUpDocAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Doctors;
import model.GetDoctorsToHospital;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;

/**
 * Created by IPC on 1/16/2018.
 */

public class MockUpDocList extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener, MockUpDocImplement.MockUpCallback {

    @BindView(R.id.ed_searchDoctor)
    EditText ed_searchDoctor;
    //    @BindView(R.id.refresh_layout)
//    SwipeRefreshLayout refresh_layout;
    @BindView(R.id.rv_doctor)
    RecyclerView rv_doctor;


    ArrayList<GetDoctorsToHospital> array = new ArrayList<>();
    LinearLayoutManager layoutManager;
    Context context;

    MockUpDocAdapter mAdapter;
    AlertDialogCustom alertDialogCustom;


    protected Handler handler;
    MockUpDocImplement implement;
    MockUpDocImplement.MockUpCallback callback;
    int offset = 0;
    String search_string = "";
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mock_up_doc);
        ButterKnife.bind(this);
        array = new ArrayList<>();
        implement = new MockUpDocImplement(context);
        handler = new Handler();
        context = this;
        callback = this;
        alertDialogCustom = new AlertDialogCustom();
        pd = new ProgressDialog(this, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);


        initViews();
    }

    private void initViews() {

        ed_searchDoctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() != 0) {
                    search_string = String.valueOf(s);
                    offset = 0;
                    array.clear();
                    mAdapter.setMoreDataAvailable(true);
                    mAdapter.setIsLoading(false);
                    load();
                }else if (s.length() == 0){
                    search_string = "";
                    array.clear();
                    offset = 0;
                    mAdapter.setMoreDataAvailable(true);
                    mAdapter.setIsLoading(false);
                    load();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        mAdapter = new MockUpDocAdapter(this, array);
        mAdapter.setLoadMoreListener(new MockUpDocAdapter.OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                rv_doctor.post(new Runnable() {
                    @Override
                    public void run() {
                        int index = array.size() - 1;
                        loadMore(index);
                    }
                });
            }
        });
        rv_doctor.setHasFixedSize(true);
        rv_doctor.setLayoutManager(new LinearLayoutManager(context));
        rv_doctor.addItemDecoration(new VerticalLineDecorator(2));
        rv_doctor.setAdapter(mAdapter);

        load();
        pd.setMessage("Loading list...");
        pd.show();
    }


    @Override
    public void onRefresh() {

    }


    private void load() {
        mAdapter.notifyDataSetChanged();
        implement.loadFirst("000018", "20", String.valueOf(offset), search_string, callback);
    }


    private void loadMore(int index) {

        //add loading progress view
        array.add(new GetDoctorsToHospital("load"));
        mAdapter.notifyItemInserted(array.size() - 1);

        implement.loadMore("000018", "20", String.valueOf(++offset), search_string, callback);


    }


    @Override
    public void onSuccessFirstLoad(Doctors body) {
        if (pd != null)
            pd.dismiss();
        array.addAll(body.getGetDoctorsToHospital());
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onSuccessLoadMore(Doctors body) {
        if (pd != null)
            pd.dismiss();
        //remove loading view
        array.remove(array.size() - 1);

        ArrayList<GetDoctorsToHospital> result = body.getGetDoctorsToHospital();
        if (result.size() > 0) {
            //add loaded data
            array.addAll(result);
        } else {//result size 0 means there is no more data available at server
            mAdapter.setMoreDataAvailable(false);
            //telling adapter to stop calling loadFirst more as no more server data available
            Toast.makeText(context, "No More Data Available", Toast.LENGTH_LONG).show();
        }
        mAdapter.notifyDataChanged();
    }

    @Override
    public void onError(String message) {
        if (pd != null)
            pd.dismiss();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (pd != null)
            pd.dismiss();
    }
}
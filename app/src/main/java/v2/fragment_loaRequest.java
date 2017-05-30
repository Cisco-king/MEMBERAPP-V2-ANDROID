package v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicard.member.R;

import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import InterfaceService.LOARequestCallback;
import InterfaceService.LoaRequestRetrieve;
import Sqlite.DatabaseHandler;
import adapter.LoaRequestAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.LoaFetch;
import model.SimpleData;
import services.response.LoaListResponse;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.DateConverter;
import utilities.ErrorMessage;
import utilities.NetworkTest;
import utilities.SharedPref;

import static android.app.Activity.RESULT_OK;


public class fragment_loaRequest extends Fragment implements LOARequestCallback {


    /**
     * ADD LOADING
     */

    @BindView(R.id.rv_loa_request)
    RecyclerView rv_loa_request;

    @BindView(R.id.btn_sort)
    FancyButton btn_sort;

    @BindView(R.id.pb)
    ProgressBar pb;

    @BindView(R.id.tv_list)
    TextView tv_list;

    LinearLayoutManager llm;
    LoaRequestAdapter adapter;
    ArrayList<LoaFetch> loaFetches = new ArrayList<>();
    ArrayList<LoaFetch> arrayMASTERList = new ArrayList<>();

    LoaRequestRetrieve implement;
    LOARequestCallback callback;

    AlertDialogCustom alertDialogCustom;
    DatabaseHandler databaseHandler;
    private Context context;

    private final int CALL_SORT_LOA = 100;

    //SORTING DATA
    String sort_by = "";
    String status_sort = "";
    String service_type_sort = "";
    String date_end_sort = "";
    String date_start_sort = "";
    ArrayList<SimpleData> doctor_sort = new ArrayList<>();
    ArrayList<SimpleData> hospital_sort = new ArrayList<>();
    String seachedData = "";

    private int CALL_LOA_VIEW = 200;

    public fragment_loaRequest() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callback = this;
        sort_by = getString(R.string.request_date);
        context = getActivity();
        databaseHandler = new DatabaseHandler(context);
        implement = new LoaRequestRetrieve(context, callback);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loarequest, container, false);
        ButterKnife.bind(this, view);

//        init();

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
    }

    private void init() {
//        callback = this;
//        sort_by = getString(R.string.request_date);
//        context = getActivity();
//        databaseHandler = new DatabaseHandler(context);
//        implement = new LoaRequestRetrieve(context, callback);
        alertDialogCustom = new AlertDialogCustom();
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rv_loa_request.setLayoutManager(llm);
        adapter = new LoaRequestAdapter(context, loaFetches, databaseHandler, callback);
        rv_loa_request.setAdapter(adapter);

        databaseHandler.dropLoa();

        if (NetworkTest.isOnline(context)) {
            if (implement != null)
                implement.getLoa(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context));
        } else
            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.NO_Internet, 1);

        if (implement != null)
            implement.UIUpdateShowLoad(true, pb, rv_loa_request, btn_sort);
    }


    @OnClick({R.id.btn_sort})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_sort:
                Intent gotoSort = new Intent(context, SortLoaReqActivity.class);
                gotoSort.putParcelableArrayListExtra(Constant.LOA_REQUEST, arrayMASTERList);
                gotoSort.putExtra(Constant.SORT_BY, sort_by);
                gotoSort.putExtra(Constant.SEARCHED_DATA, seachedData);
                gotoSort.putExtra(Constant.STATUS, status_sort);
                gotoSort.putExtra(Constant.SERVICE_TYPE, service_type_sort);
                gotoSort.putExtra(Constant.SELECTED_END_DATE, date_end_sort);
                gotoSort.putExtra(Constant.SELECTED_START_DATE, date_start_sort);
                gotoSort.putParcelableArrayListExtra(Constant.SELECTED_HOSPITAL, hospital_sort);
                gotoSort.putParcelableArrayListExtra(Constant.SELECT_DOCTOR, doctor_sort);
                startActivityForResult(gotoSort, CALL_SORT_LOA);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CALL_SORT_LOA && resultCode == RESULT_OK) {
            if (implement != null) {
                sort_by = data.getStringExtra(Constant.SORT_BY);
                status_sort = data.getStringExtra(Constant.STATUS);
                service_type_sort = data.getStringExtra(Constant.SERVICE_TYPE);
                date_end_sort = data.getStringExtra(Constant.SELECTED_END_DATE);
                date_start_sort = data.getStringExtra(Constant.SELECTED_START_DATE);
                ArrayList<SimpleData> temp = data.getParcelableArrayListExtra(Constant.SELECTED_HOSPITAL);
                implement.replactDataArray(hospital_sort, temp);
                temp = data.getParcelableArrayListExtra(Constant.SELECT_DOCTOR);
                implement.replactDataArray(doctor_sort, temp);
                seachedData = data.getStringExtra(Constant.SEARCHED_DATA);

                implement.updateList(loaFetches, databaseHandler, sort_by, status_sort,
                        service_type_sort, DateConverter.converttoyyyymmdd(date_start_sort),
                        DateConverter.converttoyyyymmddEnd(date_end_sort), doctor_sort, hospital_sort, seachedData);
//                adapter.notifyDataSetChanged();
                adapter.update(loaFetches);

                implement.updateUIList(rv_loa_request, tv_list, loaFetches);
            }
            //used if user cancelled a request to update current list
        } else if (requestCode == CALL_LOA_VIEW && resultCode == RESULT_OK) {
            if (NetworkTest.isOnline(context)) {
                if (implement != null) {
                    databaseHandler.dropLoa();
                    implement.getLoa(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context));
                }
            } else
                alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.NO_Internet, 1);

            if (implement != null)
                implement.UIUpdateShowLoad(true, pb, rv_loa_request, btn_sort);

        }

    }

    @Override
    public void gotoLoaPage(ArrayList<LoaFetch> arrayList, int adapterPosition) {
        Intent gotoLoa = new Intent(context, LoaPageActivity.class);
        gotoLoa.putParcelableArrayListExtra(Constant.DATA_SEARCHED, arrayList);
        gotoLoa.putExtra(Constant.POSITION, adapterPosition + "");

        startActivityForResult(gotoLoa, CALL_LOA_VIEW);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }


    @Override
    public void onErrorLoaListener(String message) {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onSuccessLoaListener(LoaListResponse loa) {
        Log.d("LOA_SUCCESS", loa.toString());
        if (implement != null)
            implement.getData(loa, databaseHandler);
    }

    @Override
    public void onDbLoaSuccessListener(LoaListResponse loa) {
        if (implement != null) {
            implement.updateList(loaFetches, databaseHandler, sort_by, status_sort,
                    service_type_sort, DateConverter.converttoyyyymmdd(date_start_sort), DateConverter.converttoyyyymmdd(date_end_sort), doctor_sort, hospital_sort, seachedData);

            implement.getDoctorCreds(loa, databaseHandler);
        }
    }


    @Override
    public void onErrorFetchingDoctorCreds(String message) {

        //  alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void doneFetchingDoctorData() {
        if (implement != null) {
            Timber.d("fetching doctor....");
            implement.updateList(loaFetches, databaseHandler, sort_by, status_sort,
                    service_type_sort, DateConverter.converttoyyyymmdd(date_start_sort), DateConverter.converttoyyyymmdd(date_end_sort), doctor_sort, hospital_sort, seachedData);
            implement.updateHospitals(loaFetches, databaseHandler);
        }
    }

    @Override
    public void doneUpdatingHosp() {
        if (implement != null) {
            Timber.d("done doctor update...");
            implement.UIUpdateShowLoad(false, pb, rv_loa_request, btn_sort);
            implement.updateList(loaFetches, databaseHandler, sort_by, status_sort,
                    service_type_sort, DateConverter.converttoyyyymmdd(date_start_sort), DateConverter.converttoyyyymmdd(date_end_sort), doctor_sort, hospital_sort, seachedData);

            arrayMASTERList.addAll(loaFetches);
            adapter.update(loaFetches);
            implement.updateUIList(rv_loa_request, tv_list, loaFetches);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Timber.d("destroyView was called");
        implement.detachSubscription();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Timber.d("onDestroy was called");
        implement = null;
    }
}

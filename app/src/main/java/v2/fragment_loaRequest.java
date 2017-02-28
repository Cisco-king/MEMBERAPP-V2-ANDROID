package v2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.medicard.com.medicard.R;
import android.widget.ProgressBar;

import java.util.ArrayList;

import InterfaceService.LOARequestCallback;
import InterfaceService.LoaRequestRetrieve;
import Sqlite.DatabaseHandler;
import adapter.LoaRequestAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Loa;
import model.LoaFetch;
import model.SimpleData;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.DateConverter;
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

    LinearLayoutManager llm;
    LoaRequestAdapter adapter;
    ArrayList<LoaFetch> arrayList = new ArrayList<>();
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

    public fragment_loaRequest() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_loarequest, container, false);
        ButterKnife.bind(this, view);

        init();


        return view;
    }

    private void init() {

        callback = this;
        context = getActivity();
        databaseHandler = new DatabaseHandler(context);
        implement = new LoaRequestRetrieve(context, callback);
        alertDialogCustom = new AlertDialogCustom();
        llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        rv_loa_request.setLayoutManager(llm);
        adapter = new LoaRequestAdapter(context, arrayList, databaseHandler, callback);
        rv_loa_request.setAdapter(adapter);

        databaseHandler.dropLoa();

        if (NetworkTest.isOnline(context)) {
            implement.getLoa(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context));
        } else
            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.NO_Internet, 1);


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

            implement.updateList(arrayList, databaseHandler, sort_by, status_sort,
                    service_type_sort, DateConverter.converttoyyyymmdd(date_start_sort),
                    DateConverter.converttoyyyymmdd(date_end_sort), doctor_sort, hospital_sort ,seachedData);
            adapter.notifyDataSetChanged();

        }

    }

    @Override
    public void gotoLoaPage(ArrayList<LoaFetch> arrayList, int adapterPosition) {
        Intent gotoLoa = new Intent(context, LoaPageActivity.class);
        gotoLoa.putParcelableArrayListExtra(Constant.DATA_SEARCHED, arrayList);
        gotoLoa.putExtra(Constant.POSITION, adapterPosition + "");
        startActivity(gotoLoa);
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
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);
    }

    @Override
    public void onSuccessLoaListener(Loa loa) {
        Log.d("LOA_SUCCESS", loa.toString());

        implement.getData(loa, databaseHandler);
    }

    @Override
    public void onDbLoaSuccessListener() {
        implement.updateList(arrayList, databaseHandler, sort_by, status_sort,
                service_type_sort, DateConverter.converttoyyyymmdd(date_start_sort), DateConverter.converttoyyyymmdd(date_end_sort), doctor_sort, hospital_sort, seachedData);
        implement.getDoctorCreds(arrayList, databaseHandler);
    }


    @Override
    public void onErrorFetchingDoctorCreds(String message) {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);
    }

    @Override
    public void doneFetchingDoctorData() {
        implement.updateList(arrayList, databaseHandler, sort_by, status_sort,
                service_type_sort, DateConverter.converttoyyyymmdd(date_start_sort), DateConverter.converttoyyyymmdd(date_end_sort), doctor_sort, hospital_sort, seachedData);
        implement.updateHospitals(arrayList, databaseHandler);
    }

    @Override
    public void doneUpdatingHosp() {

        implement.UIUpdateShowLoad(false, pb, rv_loa_request, btn_sort);
        implement.updateList(arrayList, databaseHandler, sort_by, status_sort,
                service_type_sort, DateConverter.converttoyyyymmdd(date_start_sort), DateConverter.converttoyyyymmdd(date_end_sort), doctor_sort, hospital_sort, seachedData);

        arrayMASTERList.addAll(arrayList);
        adapter.notifyDataSetChanged();
    }


}

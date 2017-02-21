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
import model.LoaList;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.NetworkTest;
import utilities.SharedPref;


public class fragment_loaRequest extends Fragment implements LOARequestCallback {


    /**
     * ADD LOADING
     */

    @BindView(R.id.rv_loa_request)
    RecyclerView rv_loa_request;

    @BindView(R.id.btn_sort)
    FancyButton btn_sort;

    LinearLayoutManager llm;
    LoaRequestAdapter adapter;
    ArrayList<LoaFetch> arrayList = new ArrayList<>();

    LoaRequestRetrieve implement;
    LOARequestCallback callback;

    AlertDialogCustom alertDialogCustom;
    DatabaseHandler databaseHandler;
    private Context context;

    private final int CALL_SORT_LOA = 100;

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
        arrayList.addAll(databaseHandler.retrieveLoa());


        if (NetworkTest.isOnline(context)) {
            implement.getLoa(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context));
        } else
            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.NO_Internet, 1);

    }


    @OnClick({R.id.btn_sort})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_sort:
                Intent gotoSort = new Intent(context, SortLoaReqActivity.class);
                gotoSort.putParcelableArrayListExtra(Constant.LOA_REQUEST, arrayList);
                Log.d("HOSP_GET_NAME", arrayList.get(0).getHospitalName());
                startActivityForResult(gotoSort, CALL_SORT_LOA);
                break;


        }

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
        arrayList.clear();
        arrayList.addAll(databaseHandler.retrieveLoa());
        implement.getDoctorCreds(arrayList, databaseHandler);
    }

    @Override
    public void gotoLoaPage(ArrayList<LoaFetch> arrayList, int adapterPosition) {
        Intent gotoLoa = new Intent(context, LoaPageActivity.class);
        gotoLoa.putParcelableArrayListExtra(Constant.DATA_SEARCHED, arrayList);
        gotoLoa.putExtra(Constant.POSITION, adapterPosition + "");
        startActivity(gotoLoa);
    }

    @Override
    public void onErrorFetchingDoctorCreds(String message) {
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);
    }

    @Override
    public void doneFetchingDoctorData() {
        arrayList.clear();
        arrayList.addAll(databaseHandler.retrieveLoa());
        implement.updateHospitals(arrayList, databaseHandler);


    }

    @Override
    public void doneUpdatingHosp() {
        arrayList.clear();
        arrayList.addAll(databaseHandler.retrieveLoa());
        adapter.notifyDataSetChanged();
    }
}

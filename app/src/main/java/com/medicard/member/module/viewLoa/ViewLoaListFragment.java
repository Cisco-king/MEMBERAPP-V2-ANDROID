package com.medicard.member.module.viewLoa;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.medicard.member.NavigationActivity;
import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.core.session.DiagnosisTestSession;
import com.medicard.member.module.DiagnosisTallyActivity.DiagnosisTallyActivity;
import com.medicard.member.module.DiagnosisTallyActivity.adapter.DiagnosisTallyAdapter;
import com.medicard.member.module.DiagnosisTallyActivity.fragment.DiagnosisTallyFragment;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.base.BaseFragment;
import com.medicard.member.module.diagnosis.DiagnosisActivity;
import com.medicard.member.module.viewLoa.session.ViewLoaListSession;

import java.io.Serializable;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import InterfaceService.LoaRequestRetrieve;
import adapter.LoaRequestAdapter;
import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.SimpleData;
import modules.prescriptionattachment.PrescriptionAttachmentActivity;
import services.model.Diagnosis;
import services.model.MaceRequest;
import services.model.Test;
import utilities.Constant;
import utilities.SharedPref;
import v2.LoaPageActivity;
import v2.SortLoaReqActivity;

import static android.app.Activity.RESULT_OK;

/**
 * Created by Aquino, Francisco III =) on 7/26/17.
 */

public class ViewLoaListFragment extends BaseFragment implements ViewLoaListMVP.View, ViewLoaRetrieve.ViewLoaRetrieveCallback {


    private final int CALL_SORT_LOA = 100;
    private int CALL_LOA_VIEW = 200;
    private static final String MACEREQUESTBUNLE = "MACEREQUESTBUNLE";

    //SORTING DATA
    String seachedData = "";
    String sort_by = "";
    String status_sort = "";
    String service_type_sort = "";
    String doctor_sort = "";
    String doctor_sort_id = "";
    String hospital_sort = "";
    String hospital_sort_id = ""; //holds the id of the current hosp
    String test_sort = "";
    String diag_sort = "";
    String date_start_sort = "";
    String date_end_sort = "";


//    ArrayList<SimpleData> doctor_sort = new ArrayList<>();
//    ArrayList<SimpleData> hospital_sort = new ArrayList<>();


    //Initialize of TextViews
    @BindView(R.id.tv_list)
    TextView tv_list;

    //Initialize of RecyclerView
    @BindView(R.id.rv_loa_request)
    RecyclerView rv_loa_request;

    //Initialize of FancyButton
    @BindView(R.id.btn_sort)
    FancyButton btn_sort;

    //Initialize of Layouts
    @BindView(R.id.linearLayout4)
    LinearLayout linearLayout4;

    //Intialize of ProgressBar
    @BindView(R.id.pb)
    ProgressBar pb;

    private ViewLoaListMVP.Presenter presenter;
    ViewLoaRetrieve implement;
    LoaRequestAdapter adapter;


    ViewLoaRetrieve.ViewLoaRetrieveCallback callback;

    List<MaceRequest> list;
    String memberCode;

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_loarequest;
    }

    public static Fragment newInstance() {
        ViewLoaListFragment fragment = new ViewLoaListFragment();
        return fragment;
    }

    @Override
    protected void initComponents(View view, Bundle savedInstanceState) {
        super.initComponents(view, savedInstanceState);
        implement = new ViewLoaRetrieve(context);
        callback = this;
        memberCode = SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context);

        //TODO: hide the line below until the sorting is complete
//        implement.getLoa(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context), callback);


        /**
         * new retrieve of list
         * para of getSortedMemberLoaList
         *String memberCode,
         * String status,
         * String serviceTypeId,
         * String hospitalCode,
         * String doctorCode,
         * String procCode,
         * String diagCode,
         * String startDate,
         * String endDate,
         * final ViewLoaRetrieveCallback callback
         */
        implement.getSortedMemberLoaList(memberCode, null, null, null, null, null, null, null, null, callback);

        presenter = new ViewLoaListPresenter();
        presenter.attachView(this);

        rv_loa_request.setLayoutManager(new LinearLayoutManager(context));


    }

    @OnClick({R.id.btn_sort})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_sort:
                Bundle args = new Bundle();
                args.putSerializable(MACEREQUESTBUNLE, (Serializable) list);
                Intent gotoSort = new Intent(context, SortLoaReqActivity.class);
//                gotoSort.putExtra(Constant.BundleForMaceRequest,args);
                gotoSort.putExtra(Constant.SEARCHED_DATA, seachedData);
                gotoSort.putExtra(Constant.SORT_BY, sort_by);
                gotoSort.putExtra(Constant.STATUS, status_sort);
                gotoSort.putExtra(Constant.SERVICE_TYPE, service_type_sort);
                gotoSort.putExtra(Constant.SELECT_HOSP, hospital_sort);
                gotoSort.putExtra(Constant.SELECT_HOSP_ID, hospital_sort_id);
                gotoSort.putExtra(Constant.SELECT_DOCTOR, doctor_sort);
                gotoSort.putExtra(Constant.SELECT_DOCTOR_ID, doctor_sort);

                gotoSort.putExtra(Constant.SELECT_TEST, test_sort);
                gotoSort.putExtra(Constant.SELECT_DIAG, diag_sort);
                gotoSort.putExtra(Constant.SELECTED_START_DATE, date_start_sort);
                gotoSort.putExtra(Constant.SELECTED_END_DATE, date_end_sort);
                startActivityForResult(gotoSort, CALL_SORT_LOA);
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == CALL_SORT_LOA && resultCode == RESULT_OK) {
                if (implement != null) {
                    seachedData = data.getStringExtra(Constant.SEARCHED_DATA);
                    sort_by = data.getStringExtra(Constant.SORT_BY);
                    status_sort = data.getStringExtra(Constant.STATUS);
                    service_type_sort = data.getStringExtra(Constant.SERVICE_TYPE);
                    hospital_sort = data.getStringExtra(Constant.SELECT_HOSP);
                    hospital_sort_id = data.getStringExtra(Constant.SELECT_HOSP_ID);
                    doctor_sort = data.getStringExtra(Constant.SELECT_DOCTOR);
                    doctor_sort_id = data.getStringExtra(Constant.SELECT_DOCTOR_ID);
                    test_sort = data.getStringExtra(Constant.SELECT_TEST);
                    diag_sort = data.getStringExtra(Constant.SELECT_DIAG);
                    date_start_sort = data.getStringExtra(Constant.SELECTED_START_DATE);
                    date_end_sort = data.getStringExtra(Constant.SELECTED_END_DATE);

                    if (seachedData.isEmpty()) // search locally?
                        seachedData = null;
                    if (sort_by.isEmpty())    //sort locally
                        sort_by = null;
                    if (status_sort.isEmpty())
                        status_sort = null;
                    if (service_type_sort.isEmpty())
                        service_type_sort = null;
                    if (hospital_sort_id.isEmpty())
                        hospital_sort_id = null;
                    if (doctor_sort_id.isEmpty())
                        doctor_sort_id = null;
                    if (test_sort.isEmpty())
                        test_sort = null;
                    if (diag_sort.isEmpty())
                        diag_sort = null;
                    if (date_start_sort.isEmpty())
                        date_start_sort = null;
                    if (date_end_sort.isEmpty())
                        date_end_sort = null;
                    /**
                     * new retrieve of list
                     * para of getSortedMemberLoaList
                     *String memberCode,
                     * String status,
                     * String serviceTypeId,
                     * String hospitalCode,
                     * String doctorCode,
                     * String procCode,
                     * String diagCode,
                     * String startDate,
                     * String endDate,
                     * final ViewLoaRetrieveCallback callback
                     */
                    implement.getSortedMemberLoaList(memberCode, status_sort, service_type_sort, hospital_sort_id, doctor_sort_id, test_sort, diag_sort, date_start_sort, date_end_sort, callback);

//
//                    ArrayList<SimpleData> temp = data.getParcelableArrayListExtra(Constant.SELECTED_HOSPITAL);
//                    implement.replactDataArray(hospital_sort, temp);
//                    temp = data.getParcelableArrayListExtra(Constant.SELECT_DOCTOR);
//                    implement.replactDataArray(doctor_sort, temp);
                    //TODO: add Test and diagnosis sorting


//                implement.updateList(arrayMASTERList, databaseHandler, sort_by, status_sort,
//                        service_type_sort, DateConverter.converttoyyyymmdd(date_start_sort),
//                        DateConverter.converttoyyyymmddEnd(date_end_sort), doctor_sort, hospital_sort, seachedData);
//                adapter.notifyDataSetChanged();
//                adapter.update(loaFetches);
//
//                implement.updateUIList(rv_loa_request, tv_list, arrayMASTERList);
                }
                //used if user cancelled a request to update current list
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
//        implement.getLoa(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context), callback);
    }


    /*
     * This Method is not in use
     */
    @Override
    public void displayLoaList(List<MaceRequest> maceRequests) {
        System.out.println("========================== SIZE === " + maceRequests.size());
    }

    @Override
    public void gotoLoaPage(List<MaceRequest> maceRequestList, int position) {
        SharedPref.MACEREQUESTLIST = maceRequestList;
        Intent gotoLoa = new Intent(context, LoaPageActivity.class);
//        Bundle args = new Bundle();
//        args.putSerializable(Constant.DATA_SEARCHED, (Serializable) maceRequestList);
//        gotoLoa.putExtra(Constant.BundleForMaceRequest, args);
        gotoLoa.putExtra(Constant.POSITION, position + "");
        startActivityForResult(gotoLoa, CALL_LOA_VIEW);
    }

    /*
         * This Method is invoke once the Response is Successful
         * @Params List<MaceRequest>
         */
    @Override
    public void onSuccess(List<MaceRequest> maceRequests) {
        this.list = maceRequests;
        pb.setVisibility(View.GONE);
        adapter = new LoaRequestAdapter(context, maceRequests, callback);
        rv_loa_request.setAdapter(adapter);

    }

    @Override
    public void onFailure() {
        pb.setVisibility(View.GONE);
        Toast.makeText(context, "Failed to connect", Toast.LENGTH_SHORT);
        tv_list.setVisibility(View.VISIBLE);
    }

}


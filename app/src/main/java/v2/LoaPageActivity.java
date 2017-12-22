package v2;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;

import com.medicard.member.R;
import com.medicard.member.module.viewLoa.ViewLoaListFragment;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import InterfaceService.LoaPageInterface;
import InterfaceService.LoaPageRetieve;
import InterfaceService.ScreenshotCallback;
import Sqlite.DatabaseHandler;
import adapter.BasicTestLoaAdapter;
import adapter.FileUploadLoaAdapter;
import adapter.OperatingRoomLoaAdapter;
import adapter.OtherTestLoaAdapter;
import adapter.ProcedureLoaAdapter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import constants.FileGenerator;
import constants.OutPatientConsultationForm;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Doctor;
import model.GetUSER;
import model.MemberInfo;
import services.model.AttachmentObject;
import services.model.MaceRequest;
import timber.log.Timber;
import utilities.AgeCorrector;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.DateConverter;
import utilities.ErrorMessage;
import utilities.FileUtils;
import utilities.GenderPicker;
import utilities.Loader;
import utilities.NetworkTest;
import utilities.PermissionUtililities;
import utilities.QrCodeCreator;
import utilities.RequestType;
import utilities.ResultSetters;
import utilities.SharedPref;
import utilities.ViewUtilities;
import v2.module.loapage.LoaPage;
import v2.module.loapage.LoaPagePresenter;

public class LoaPageActivity extends AppCompatActivity
        implements LoaPage.View, ScreenshotCallback, LoaPageInterface {

    public static final String REFERENCE_NUMBER = "Reference No : ";

    @BindView(R.id.content_loa_page)
    ScrollView content_loa_page;
    @BindView(R.id.cv_problem)
    CardView cv_problem;

    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.tv_approval_code)
    TextView tv_approval_code;
    @BindView(R.id.tv_member_code)
    TextView tv_member_code;
    @BindView(R.id.tv_member_name)
    TextView tv_member_name;
    @BindView(R.id.tv_age)
    TextView tv_age;
    @BindView(R.id.tv_gender)
    TextView tv_gender;
    @BindView(R.id.tv_company)
    TextView tv_company;
    @BindView(R.id.tv_date_approved)
    TextView tv_date_approved;

    @BindView(R.id.tv_doc_name)
    TextView tv_doc_name;
    @BindView(R.id.tv_spec)
    TextView tv_spec;

    @BindView(R.id.tv_diagnosis)
    TextView tv_diagnosis;

    @BindView(R.id.tv_hospname)
    TextView tv_hospname;

    @BindView(R.id.tv_procedure)
    TextView tv_procedure;
    @BindView(R.id.tv_validity_date)
    TextView tv_validity_date;

    @BindView(R.id.tv_problem)
    TextView tv_problem;

    @BindView(R.id.tv_header)
    TextView tv_header;

    @BindView(R.id.tv_sub_title)
    TextView tv_sub_title;

    @BindView(R.id.cvDoctorDetails)
    CardView cvDoctorDetails;


    @BindView(R.id.btn_download)
    FancyButton btn_download;
    @BindView(R.id.btn_cancel_req)
    FancyButton btn_cancel_req;
    @BindView(R.id.btn_cancel)
    FancyButton btn_cancel;

    @BindView(R.id.ivQrApprovalNumber)
    ImageView ivQrApprovalNumber;
    @BindView(R.id.tvReferenceNumber)
    TextView tvReferenceNumber;

    @BindView(R.id.tvValidityDate)
    TextView tvValidityDate;
    @BindView(R.id.tvEffectiveDate)
    TextView tvEffectiveDate;
    @BindView(R.id.tvDateApproved)
    TextView tvDateApproved;
    @BindView(R.id.tvRemarks)
    TextView tvRemakrs;

    @BindView(R.id.tvHospitalClinicName)
    TextView tvHospitalClinicName;
    @BindView(R.id.tvHopitalClinicLocation)
    TextView tvHopitalClinicLocation;
    @BindView(R.id.tvHopitalClinicContacts)
    TextView tvHopitalClinicContacts;
    @BindView(R.id.tvHopitalClinicDoctorName)
    TextView tvHopitalClinicDoctorName;

    @BindView(R.id.tvDoctorName)
    TextView tvDoctorName;
    @BindView(R.id.tvDoctorInfo)
    TextView tvDoctorInfo;

    @BindView(R.id.tvServiceType)
    TextView tvServiceType;

    @BindView(R.id.cvHospitalClinic)
    CardView cvHospitalClinic;

    @BindView(R.id.tvDisclaimerInfo)
    TextView tvDisclaimerInfo;

    @BindView(R.id.tvDisclaimerInfo2)
    TextView tvDisclaimerInfo2;

    //used for other test selected data
    @BindView(R.id.cv_othertest_tests)
    CardView cv_othertest_tests;
    @BindView(R.id.rv_otherTest)
    RecyclerView rv_otherTest;
    @BindView(R.id.tv_total_title)
    TextView tv_total_title;
    @BindView(R.id.tv_total_price)
    TextView tv_total_price;
    @BindView(R.id.tv_other_test)
    TextView tv_other_test;

    //used for other test selected data
    @BindView(R.id.cv_procedures_box)
    CardView cv_procedures_box;
    @BindView(R.id.rv_procedures)
    RecyclerView rv_procedures;
    @BindView(R.id.tv_procedures_total_title)
    TextView tv_procedures_total_title;
    @BindView(R.id.tv_procedures_total_price)
    TextView tv_procedures_total_price;

    //used for other test selected data
    @BindView(R.id.cv_OperatingRoom)
    CardView cv_OperatingRoom;
    @BindView(R.id.rv_OperatingRoom)
    RecyclerView rv_OperatingRoom;
    @BindView(R.id.tv_total_title_OperatingRoom)
    TextView tv_total_title_OperatingRoom;
    @BindView(R.id.tv_total_price_OperatingRoom)
    TextView tv_total_price_OperatingRoom;


    //used for pictures of attachments
    @BindView(R.id.cv_FileUpload)
    CardView cv_FileUpload;
    @BindView(R.id.rv_FileUpload)
    RecyclerView rv_FileUpload;



    @BindView(R.id.tv_procedures_diagnosis)
    TextView tv_procedures_diagnosis;
    @BindView(R.id.tv_procedures_approval_no)
    TextView tv_procedures_approval_no;

    ProcedureLoaAdapter procedureLoaAdapter;

    private int RESULT_GETTER;
    int position;
    List<MaceRequest> loaList = new ArrayList<>();
    ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> arrayListGroupedByDiag = new ArrayList<>();
    ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> arrayListGroupedByDiagOP = new ArrayList<>();
    ArrayList<AttachmentObject> attachmentObjectArrayList = new ArrayList<>();
    OtherTestLoaAdapter otherTestLoaAdapter;
    BasicTestLoaAdapter basicTestLoaAdapter;
    OperatingRoomLoaAdapter operatingRoomLoaAdapter;
    FileUploadLoaAdapter fileUploadLoaAdapter;

    Context context;
    ScreenshotCallback screenshotCallback;
    MaceRequest loa;
    AlertDialogCustom alertDialogCustom;
    LoaPageInterface callback;
    LoaPageRetieve implement;
    Loader loader;
    FragmentTransaction fragmentTransaction;

    private OutPatientConsultationForm outPatientForm;
    OutPatientConsultationForm.Builder loaFormBuilder;

    private LoaPagePresenter presenter;
    private GetUSER userInformation;

    String serviceType;

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loa_page);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ButterKnife.bind(this);

        context = this;
        screenshotCallback = this;
        callback = this;

        dbHandler = new DatabaseHandler(context);

        loader = new Loader(context);

        implement = new LoaPageRetieve(context, callback);
        loader = new Loader(context);
        alertDialogCustom = new AlertDialogCustom();

        position = Integer.parseInt(getIntent().getStringExtra(Constant.POSITION));
        List<MaceRequest> temp;
        Bundle args = getIntent().getBundleExtra(Constant.BundleForMaceRequest);
        temp = (List<MaceRequest>) args.getSerializable(Constant.DATA_SEARCHED);
        loaList.addAll(temp);


        presenter = new LoaPagePresenter();
        presenter.attachView(this);

        if (NetworkTest.isOnline(this)) {

            String memberId = SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, this);
            Log.d("UserInformationxxx", "onCreate: " + memberId);

            presenter.initUserInformation(
                    SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, this)
            );
        } else {
            alertDialogCustom.showMe(
                    this,
                    alertDialogCustom.NO_Internet_title,
                    alertDialogCustom.NO_Internet, 1);
        }


        init(loaList, position);

    }

    private void init(List<MaceRequest> loaList, int position) {

        loa = loaList.get(position);

        // serviceType = "( " + loa.getRemarks() + " )";

        /*loader.startLad();
        loader.setMessage("Loading...");*/
//        presenter.requestDoctorByCode(loa.getDoctorCode());
        String changeFormat = DateConverter.convertDatetoMMMddyyy(loa.getRequestDatetime());
        // todo init lao form data


        //Timber.d("serviceType : %s approval code : %s batchCode %s", loa.getRemarks(), loa.getApprovalNo(), loa.getBatchCode());


        loaFormBuilder = new OutPatientConsultationForm.Builder()
                .validFrom(DateConverter.convertDateToMMddyyyy(changeFormat))
                .validUntil(DateConverter.validityDatePLusDay(changeFormat, 3))
                .dateOfConsult(changeFormat)
                .referenceNumber(loa.getApprovalNo())
                .doctor(loa.getDoctorName())
                .hospital(loa.getHospitalName())
                .memberName(loa.getMemFname() + " " + loa.getMemLname())
                .age(AgeCorrector.age(SharedPref.getStringValue(SharedPref.USER, SharedPref.AGE, context)))
                .gender(GenderPicker.setGender(Integer.parseInt(SharedPref.getStringValue(SharedPref.USER, SharedPref.GENDER, this))))
                .memberId(loa.getMemCode())
                .company(loa.getMemCompany())
                //         .remarks(loa.getRemarks())
                .chiefComplaint(loa.getReasonForConsult())
                .serviceType(loa.getServiceType());
        //     .bactchCode(loa.getco);


        try {
            ivQrApprovalNumber.setVisibility(View.VISIBLE);
            ivQrApprovalNumber.setImageBitmap(QrCodeCreator.getBitmapFromString2(loa.getApprovalNo()));
        } catch (Exception e) {
            e.printStackTrace();
            ivQrApprovalNumber.setVisibility(View.GONE);
        }

        try {
            tvReferenceNumber.setVisibility(View.VISIBLE);
            tvReferenceNumber.setText(REFERENCE_NUMBER.concat(loa.getApprovalNo()));
        } catch (Exception e) {
            e.printStackTrace();
            tvReferenceNumber.setVisibility(View.GONE);
        }


        try {
            implement.setExpiredStatus(btn_download, btn_cancel_req, loa.getStatus());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // tv_header.setText(loa.getRemarks());
        tv_status.setText(ResultSetters.titleSetter(loa.getStatus()));
        tv_approval_code.setText(loa.getApprovalNo());
        tv_member_code.setText(loa.getMemCode());
        tv_member_name.setText(loa.getMemFname() + " " + loa.getMemLname());
        tv_age.setText(AgeCorrector.age(SharedPref.getStringValue(SharedPref.USER, SharedPref.AGE, context)));
        tv_gender.setText(GenderPicker.setGender(Integer.parseInt(
                SharedPref.getStringValue(SharedPref.USER, SharedPref.GENDER, this))));
        tv_company.setText(loa.getMemCompany());

        try {
            tv_date_approved.setText(DateConverter.convertDatetoMMMddyyy(loa.getRequestDatetime())); // view is invisible
        } catch (Exception e) {
            tv_date_approved.setText(loa.getRequestDatetime());
        }


        /*tvValidityDate.setText(changeFormat);
        tvEffectiveDate.setText(DateConverter.validityDatePLusDay(changeFormat, 3));
        tvDateApproved.setText(DateConverter.convertDatetoMMMddyyy(loa.getApp));*/
//        tvEffectiveDate.setText();
        tvDateApproved.setText(changeFormat);

        tv_doc_name.setText(loa.getDoctorName());
        if (null == loa.getReasonForConsult()) {
            cv_problem.setVisibility(View.GONE);
        } else {
            cv_problem.setVisibility(View.VISIBLE);
            tv_problem.setText(loa.getReasonForConsult());
        }


        tvServiceType.setText(serviceType);


        tv_validity_date.setText(
                getString(R.string.this_req_is_valid_from).concat("\n" +
                        DateConverter.convertDateToMMddyyyy(changeFormat) + " to " +
                        DateConverter.validityDatePLusDay(changeFormat, 3)));
        try {
            tv_spec.setText(testData(loa.getDoctorSpec()));
        } catch (Exception e) {
            e.printStackTrace();
            tv_spec.setVisibility(View.GONE);
        }

        if (loa.getHospitalName() != null && !loa.getHospitalName().isEmpty()) {
            cvHospitalClinic.setVisibility(View.VISIBLE);
            tvHospitalClinicName.setText(loa.getHospitalName());
            tvHopitalClinicLocation.setText(loa.getHospitalAddress());
            tvHopitalClinicContacts.setText(loa.getHospitalContact());
            tvHopitalClinicDoctorName.setVisibility(View.GONE);
        } else {
            cvHospitalClinic.setVisibility(View.GONE);
        }

        tvDoctorName.setText(loa.getDoctorName());
        tv_sub_title.setText(Constant.SUBTITLEPENDING);


        if (tv_status.getText().toString().trim().equals(ResultSetters.REQUEST_APPROVAL)) {
            setPending();
        } else if (tv_status.getText().toString().trim().equals(ResultSetters.REQUEST_CONFIRMED)) {
            setApproved();
        }

        //to show data for other test
        if (loa.getRequestType().equalsIgnoreCase(RequestType.OTHER_TEST)) {
            setOtherTestData(loaList, position);
        }

        //to show data for basic test
        if (loa.getRequestType().equalsIgnoreCase(RequestType.BASIC_TEST)) {
            setBasicTestData(loaList, position);
        }

        //to show data for procedure
        if (loa.getRequestType().equalsIgnoreCase(RequestType.PROCEDURE)) {
            setProcedureData(loaList, position);
        }

        //to show data for operating room
        if (loa.getRequestType().equalsIgnoreCase(RequestType.OP_OR)) {
            setOperatingRoomData(loaList, position);
        }

        if (loa.getRequestType().equalsIgnoreCase(RequestType.FILE_UPLOAD)) {
            setFileUploadData(loaList, position);
        }


//        String doctorInfo = new StringBuilder()
//                .append(loa.getDoctorSpec())
//                .append(!(loa.get() == null || loa.getRoom().equals("null")) ? "\n\n" + loa.getRoom() : "")
//                .append(!(loa.getSchedule() == null || loa.getSchedule().equals("null")) ? "\n\n" + loa.getSchedule() : "")
//                .toString();

        //  Timber.d("doctorSpec %s, loaRoom %s, loaSchedule %s", loa.getDoctorSpec(), loa.getRoom(), loa.getSchedule());

//        if (!doctorInfo.trim().isEmpty()) {
//            tvDoctorInfo.setText(doctorInfo);
//        } else {
//            tvDoctorInfo.setVisibility(View.GONE);
//        }

        OutPatientConsultationForm build = loaFormBuilder.build();
        if (FileUtils.fileExistance(build.getServiceType(), build.getReferenceNumber())) {
            btn_download.setText(getString(R.string.view_loa));
        }

        if (isCancelledORExpired(loa.getStatus())) {
            ViewUtilities.hideView(tvDisclaimerInfo);
            ViewUtilities.hideView(tvDisclaimerInfo2);
        } /*else {
            // todo with provider
            ViewUtilities.showView(tvDisclaimerInfo);
            if (loa.getBooleanWithProvider()) {
                ViewUtilities.hideView(tvDisclaimerInfo2);
                tvDisclaimerInfo.setText(getString(R.string.doctor_with_app));
            } else {
                ViewUtilities.showView(tvDisclaimerInfo2);
                tvDisclaimerInfo2.setText(getString(R.string.doctor_without_app2));
                tvDisclaimerInfo.setText(getString(R.string.doctor_without_app));
            }
        }*/

        //cas
        //Timber.d("WithProvider ......... %s", loa.getWithProvider());


    }

    private void setFileUploadData(List<MaceRequest> loaList, int position) {
        loa = loaList.get(position);
        cvDoctorDetails.setVisibility(View.GONE);
        cv_problem.setVisibility(View.GONE);
        cv_FileUpload.setVisibility(View.VISIBLE);

        attachmentObjectArrayList.clear();
        attachmentObjectArrayList.addAll(loa.getAttachments());
        fileUploadLoaAdapter = new FileUploadLoaAdapter(context, attachmentObjectArrayList,alertDialogCustom);
        rv_FileUpload.setLayoutManager(new LinearLayoutManager(this));
        rv_FileUpload.setAdapter(fileUploadLoaAdapter);

    }

    private void setBasicTestData(List<MaceRequest> loaList, int position) {
        loa = loaList.get(position);
        cv_problem.setVisibility(View.GONE);

        cv_othertest_tests.setVisibility(View.VISIBLE);
        tv_other_test.setText(context.getString(R.string.basic_test));
        tv_total_price.setText("P " + loa.getTotalAmount());


        ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> unfilteredGrouping = new ArrayList<>();
        for (MaceRequest.GroupedByCostCenter groupedByCostCenter : loa.getGroupedByCostCenters()) {
            for (int i = 0; i < groupedByCostCenter.getGroupedByDiag().size(); i++) {
                MaceRequest.GroupedByCostCenter.GroupedByDiag groupedByDiag = groupedByCostCenter.getGroupedByDiag().get(i);
                groupedByDiag.setFirstInstance(i == 0 ? true : false);
                groupedByDiag.setBasicTestFlag(loa.getRequestType().contains(RequestType.BASIC_TEST) ? true : false);
                groupedByDiag.setCostCenter(groupedByCostCenter.getCostCenter());
                groupedByDiag.setStatus(groupedByCostCenter.getStatus());
                groupedByDiag.setSubTotal(groupedByCostCenter.getSubTotal());
                unfilteredGrouping.add(groupedByDiag);
            }
        }
        //Process and group by DiagType -> Tests + CostCenter
        arrayListGroupedByDiag.addAll(unfilteredGrouping);
        basicTestLoaAdapter = new BasicTestLoaAdapter(context, unfilteredGrouping);
        rv_otherTest.setLayoutManager(new LinearLayoutManager(this));
        rv_otherTest.setAdapter(basicTestLoaAdapter);

    }

    private void setOperatingRoomData(List<MaceRequest> loaList, int position) {
        loa = loaList.get(position);
        cv_problem.setVisibility(View.GONE);
        cv_OperatingRoom.setVisibility(View.VISIBLE);
        tv_total_price_OperatingRoom.setText("P " + loa.getTotalAmount());

        ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> unfilteredGrouping = new ArrayList<>();
        for (MaceRequest.GroupedByCostCenter groupedByCostCenter : loa.getGroupedByCostCenters()) {
            for (int i = 0; i < groupedByCostCenter.getGroupedByDiag().size(); i++) {
                MaceRequest.GroupedByCostCenter.GroupedByDiag groupedByDiag = groupedByCostCenter.getGroupedByDiag().get(i);
//                groupedByDiag.setFirstInstance(i == 0 ? true : false);
//                groupedByDiag.setBasicTestFlag(groupedByDiag.getDiagDesc().equals(groupedByCostCenter.getGroupedByDiag().get(i)) ?  : false);
                groupedByDiag.setCostCenter(groupedByCostCenter.getCostCenter());
                groupedByDiag.setStatus(groupedByCostCenter.getStatus());
                groupedByDiag.setSubTotal(groupedByCostCenter.getSubTotal());
                unfilteredGrouping.add(groupedByDiag);
            }
        }
        arrayListGroupedByDiagOP.addAll(processGrouping(unfilteredGrouping));
        operatingRoomLoaAdapter = new OperatingRoomLoaAdapter(context, arrayListGroupedByDiagOP);
        rv_OperatingRoom.setLayoutManager(new LinearLayoutManager(this));
        rv_OperatingRoom.setAdapter(operatingRoomLoaAdapter);

    }

    private void setProcedureData(List<MaceRequest> loaList, int position) {
        loa = loaList.get(position);
        cv_problem.setVisibility(View.GONE);
        cv_procedures_box.setVisibility(View.VISIBLE);

        tv_procedures_diagnosis.setText("Diagnosis: " + loa.getPrimaryDiag());
        tv_procedures_approval_no.setText(loa.getStatus() == RequestType.APPROVED ? loa.getApprovalNo() : "");
        procedureLoaAdapter = new ProcedureLoaAdapter(context, loa.getMappedTest());
        rv_procedures.setLayoutManager(new LinearLayoutManager(this));
        rv_procedures.setAdapter(procedureLoaAdapter);

        tv_procedures_total_price.setText("P " + loa.getTotalAmount());

    }

    private void setOtherTestData(List<MaceRequest> loaList, int position) {

        loa = loaList.get(position);

        cv_problem.setVisibility(View.GONE);
        cv_othertest_tests.setVisibility(View.VISIBLE);
        tv_total_price.setText("P " + loa.getTotalAmount());


        ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> unfilteredGrouping = new ArrayList<>();
        for (MaceRequest.GroupedByCostCenter groupedByCostCenter : loa.getGroupedByCostCenters()) {
            for (int i = 0; i < groupedByCostCenter.getGroupedByDiag().size(); i++) {
                MaceRequest.GroupedByCostCenter.GroupedByDiag groupedByDiag = groupedByCostCenter.getGroupedByDiag().get(i);
                groupedByDiag.setCostCenter(groupedByCostCenter.getCostCenter());
                groupedByDiag.setStatus(groupedByCostCenter.getStatus());
                groupedByDiag.setSubTotal(groupedByCostCenter.getSubTotal());
                unfilteredGrouping.add(groupedByDiag);
            }
        }
        //Process and group by DiagType -> Tests + CostCenter
        arrayListGroupedByDiag.addAll(processGrouping(unfilteredGrouping));
        otherTestLoaAdapter = new OtherTestLoaAdapter(context, arrayListGroupedByDiag);
        rv_otherTest.setLayoutManager(new LinearLayoutManager(this));
        rv_otherTest.setAdapter(otherTestLoaAdapter);
    }

    private ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> processGrouping(ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> gbdList) {
        ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> returnedList = new ArrayList<>();
        ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> primaryList = arrangeListByDiagType(gbdList, 1);
        ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> otherList = arrangeListByDiagType(gbdList, 2);

        returnedList.addAll(setSubTotalAndIndices(primaryList));
        returnedList.addAll(setSubTotalAndIndices(otherList));

        return returnedList;
    }

    private ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> arrangeListByDiagType(ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> gbdList, int diagType) {
        int a = 0;
        int b = 2;
        ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> primaryList = new ArrayList<>();
        for (MaceRequest.GroupedByCostCenter.GroupedByDiag groupedByDiag : gbdList) {
            if (groupedByDiag.getDiagType() == diagType) {
                groupedByDiag.setFirstInstance(a++ == 0 ? true : false);
                groupedByDiag.setLastInstance(false);
                groupedByDiag.setBasicTestFlag(true);
                primaryList.add(groupedByDiag);
            }
        }
        return primaryList;
    }

    private ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> setSubTotalAndIndices(ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> primaryList) {
        //Subtotal of CostCenter
        String costCenter = "";
        Double subTotal = 0.0;
        for (int i = 0; i < primaryList.size(); i++) {
            //Set initial CostCenter
            if (i == 0)
                costCenter = primaryList.get(i).getCostCenter();
            //Check if grouped CostCenter is equal to current CostCenter
            if (!primaryList.get(i).getCostCenter().equals(costCenter)) {
                costCenter = primaryList.get(i).getCostCenter();
                primaryList.get(i - 1).setSubTotal(String.valueOf(subTotal));
                primaryList.get(i - 1).setLastInstance(true);
                subTotal = 0.0;
            }
            for (MaceRequest.MappedTest mappedTest : primaryList.get(i).getMappedTest()) {
                subTotal += Double.valueOf(mappedTest.getAmount());
            }
            if (i == primaryList.size() - 1) {
                primaryList.get(i).setSubTotal(String.valueOf(subTotal));
                primaryList.get(i).setLastInstance(true);
            }
        }

        return primaryList;
    }

    public void setApproved() {
        tvDisclaimerInfo.setVisibility(View.VISIBLE);
        tv_sub_title.setVisibility(View.GONE);
        btn_download.setVisibility(View.VISIBLE);
        tvReferenceNumber.setVisibility(View.VISIBLE);
        tvValidityDate.setVisibility(View.VISIBLE);
        tv_validity_date.setVisibility(View.VISIBLE);
    }

    public void setPending() {
        tvDisclaimerInfo.setVisibility(View.GONE);
        tv_sub_title.setVisibility(View.VISIBLE);
        btn_download.setVisibility(View.GONE);
        tvReferenceNumber.setVisibility(View.GONE);
        tvValidityDate.setVisibility(View.GONE);
        tv_validity_date.setVisibility(View.GONE);
        ivQrApprovalNumber.setVisibility(View.GONE);

        cvDoctorDetails.setVisibility(View.VISIBLE);
        cv_problem.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick({R.id.btn_download, R.id.btn_cancel_req, R.id.btn_cancel})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_download:
                OutPatientConsultationForm build = loaFormBuilder.build();
                if (FileUtils.fileExistance(build.getServiceType(), build.getReferenceNumber())) {
                    showLoaForm(this, build.getServiceType(), build.getReferenceNumber());
                } else {
                    screenshotCallback.onScreenShotListener();
                }
                break;

            case R.id.btn_cancel:
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_body, ViewLoaListFragment.newInstance());
                fragmentTransaction.commit();
                finish();
                break;

            case R.id.btn_cancel_req:
                implement.showCancelConfirmation(loa.getRequestCode());
                break;
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Timber.d("request code for storate %s permission response %s", PermissionUtililities.READ_WRITE_PERMISSION, requestCode);
        switch (requestCode) {
            case PermissionUtililities.REQUESTCODE_STORAGE_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    presenter.generateLoaForm(
                            loaFormBuilder.build(),
                            getResources().openRawResource(R.raw.loa_consultation_form));
                } else {
                    Timber.d("permission denied");
                }
            }

            return;
        }
    }

    private String testData(String doctorSpec) {
        return doctorSpec.equals("") ? "Specialization Not Specified" : doctorSpec;
    }


    @Override
    public void onScreenShotListener() {
        Log.d("TRIGGERED", "TRIGGERED");
        // todo generate pdf form
//        if (Permission.checkPermissionStorage(context)) {
        if (PermissionUtililities.hasPermissionToReadAndWriteStorage(this)) {
            OutPatientConsultationForm build = loaFormBuilder.build();
            if (FileUtils.fileExistance(build.getServiceType(), build.getReferenceNumber())) {
//                showLoaForm(this, build.getServiceType(), build.getReferenceNumber());
                onShowNotifyExistingPdfDialog(build.getServiceType(), build.getReferenceNumber());
            } else {
                generateLoaConsultationForm();
            }

            /*Bitmap bitmap = Screenshot.loadBitmapFromView(content_loa_page);
            new ImageSaver(context).
                    setFileName(loa.getApprovalNo()
                            + "_" + loa.getRemarks() + ".jpg")
                    .setDirectoryName("MediCard")
                    .setExternal(false)
                    .save(bitmap, screenshotCallback);*/
        }
    }

    private void generateLoaConsultationForm() {
        btn_download.setVisibility(View.GONE);
        btn_cancel.setVisibility(View.GONE);
        btn_cancel_req.setVisibility(View.GONE);

        String text = tvRemakrs.getText().toString();
        text = text.replace("\n", ", ").replace("\r", "");
        loaFormBuilder.remarks(text);
        presenter.generateLoaForm(
                loaFormBuilder.build(),
                getResources().openRawResource(R.raw.loa_consultation_form));
    }

    @Override
    public void onSuccessfulScreenshot() {
        btn_cancel.setVisibility(View.VISIBLE);
        btn_cancel_req.setVisibility(View.VISIBLE);
        btn_download.setVisibility(View.VISIBLE);

//        btn_download.setText(getString(R.string.view_loa));

        alertDialogCustom.showMe(context, alertDialogCustom.CONGRATULATIONS_title, alertDialogCustom.Saved_Screenshot, 2);
    }

    @Override
    public void onError(String message) {
        Log.e("ERROR", message);
        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
        RESULT_GETTER = implement.setToLoadList(false);
    }

    @Override
    public void noInternet() {
        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.NO_Internet, 1);
        RESULT_GETTER = implement.setToLoadList(false);
    }

    @Override
    public void onSuccess() {
        tv_status.setText("REQUEST CANCELLED");
        btn_download.setVisibility(View.GONE);
        btn_cancel_req.setVisibility(View.GONE);
        tvDisclaimerInfo.setVisibility(View.GONE);
        tvReferenceNumber.setVisibility(View.GONE);
        tv_validity_date.setVisibility(View.GONE);


        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.success, alertDialogCustom.data_cancelled, 2);
        RESULT_GETTER = implement.setToLoadList(true);
    }

    @Override
    public void onCancelRequestListener() {
        loader.startLad();
        loader.setMessage("Cancelling Request");

        implement.cancelRequest(loa.getRequestCode());
        // implement.cancelRequest(loa.getBatchCode());
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_GETTER, intent);
        finish();
    }

    @Override
    public void userInformation(GetUSER getUSER) {
        Log.d("UserInformationxxx", "userInformation: " + getUSER.getMemberInfo().getVAL_DATE());

        MemberInfo memberInfo = getUSER.getMemberInfo();

        tvValidityDate.setText(memberInfo.getVAL_DATE());

        tvEffectiveDate.setText(memberInfo.getEFF_DATE());

        loaFormBuilder.validityDate(memberInfo.getVAL_DATE())
                .dateEffectivity(memberInfo.getEFF_DATE());
    }

    @Override
    public void displayRemarks(String remark) {
        tvRemakrs.append(remark + "\n");
    }

    @Override
    public void onNetworkError() {
        Log.d("UserInformationxxx", "onNetworkError:");
//        loader.stopLoad();
    }

    @Override
    public void displayDoctor(Doctor doctor) {
        Log.d("testtesttest", "displayDoctor: " + doctor.getSpecDesc());
//        loader.stopLoad();
//        tvDoctorName.setText(loa.getDoctorName());
//        String doctorInfo = new StringBuilder()
//                .append(doctor.getSpecDesc())
//                .append(!(doctor.getRoomNo() == null || doctor.getRoomNo().equals("null"))
//                        ? "\n\n" + doctor.getRoomNo() : "")
//                .append(!(doctor.getSchedule() == null || doctor.getSchedule().equals("null"))
//                        ? "\n\n" + doctor.getSchedule() : "")
//                .toString();
//
//        tvDoctorInfo.setText(doctorInfo);
    }

    @Override
    public void onGenerateLoaFormSuccess() {
        btn_cancel.setVisibility(View.VISIBLE);
        btn_cancel_req.setVisibility(View.VISIBLE);
        btn_download.setVisibility(View.VISIBLE);

        btn_download.setText(getString(R.string.view_loa));

        alertDialogCustom.showMe(
                context,
                alertDialogCustom.CONGRATULATIONS_title,
                alertDialogCustom.LOA_GENERATE_PDF_SUCCESS,
                2);
    }

    public void onShowNotifyExistingPdfDialog(final String serviceType, final String referenceNumber) {
        alertDialogCustom.showMe(context, alertDialogCustom.CONGRATULATIONS_title, alertDialogCustom.LOA_GENERATE_PDF_SUCCESS, 2,
                new AlertDialogCustom.OnCustomDialogClickListener() {
                    @Override
                    public void onOkClick() {
                        Timber.d("on ok with cancel functionality was clicked");
                    }

                    @Override
                    public void onViewPdf() {
                        showLoaForm(LoaPageActivity.this, serviceType, referenceNumber);
                    }
                });
    }

    public void showLoaForm(Context context, String serviceType, String referenceNumber) {

        try {
            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM), "MediCard");

            String loaFileName = FileGenerator.genFileName(serviceType, referenceNumber);
            File loaFormFile = new File(pdfFolder, loaFileName + ".pdf");


            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(Uri.fromFile(loaFormFile), "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(context, "No PDF Viewer Installed", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onGenerateLoaFormError() {
        Timber.d("generated loa form encounter an error!");
    }

    private boolean isCancelledORExpired(String loaStatus) {
        return (loaStatus.equals(Constant.CANCELLED) || loaStatus.equals(Constant.EXPIRED));
    }

}

package v2;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.medicard.com.medicard.R;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import InterfaceService.LoaPageInterface;
import InterfaceService.LoaPageRetieve;
import InterfaceService.ScreenshotCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Loa;
import model.LoaFetch;
import utilities.AgeCorrector;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.DateConverter;
import utilities.GenderPicker;
import utilities.ImageSaver;
import utilities.Loader;
import utilities.Permission;
import utilities.Screenshot;
import utilities.SharedPref;

public class LoaPageActivity extends AppCompatActivity implements ScreenshotCallback, LoaPageInterface {


    @BindView(R.id.content_loa_page)
    ScrollView content_loa_page;

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


    @BindView(R.id.btn_download)
    FancyButton btn_download;
    @BindView(R.id.btn_cancel_req)
    FancyButton btn_cancel_req;
    @BindView(R.id.btn_cancel)
    FancyButton btn_cancel;

    private int RESULT_GETTER;
    int position;
    ArrayList<LoaFetch> loaList = new ArrayList<>();
    Context context;
    ScreenshotCallback screenshotCallback;
    LoaFetch loa;
    AlertDialogCustom alertDialogCustom;
    LoaPageInterface callback;
    LoaPageRetieve implement;
    Loader loader;

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
        implement = new LoaPageRetieve(context, callback);
        loader = new Loader(context);
        alertDialogCustom = new AlertDialogCustom();
        position = Integer.parseInt(getIntent().getStringExtra(Constant.POSITION));
        ArrayList<LoaFetch> temp;
        temp = getIntent().getParcelableArrayListExtra(Constant.DATA_SEARCHED);
        loaList.addAll(temp);
        temp.clear();

        init(loaList, position);

    }

    private void init(ArrayList<LoaFetch> loaList, int position) {

        loa = loaList.get(position);
        tv_header.setText(loa.getRemarks());
        implement.setCancelButton(loa.getStatus(), btn_cancel_req);
        tv_status.setText(loa.getStatus());
        tv_approval_code.setText(loa.getApprovalNo());
        tv_member_code.setText(loa.getMemberCode());
        tv_member_name.setText(loa.getMemFname() + " " + loa.getMemLname());
        tv_age.setText(AgeCorrector.age(SharedPref.getStringValue(SharedPref.USER, SharedPref.AGE, this)));
        tv_gender.setText(GenderPicker.setGender(Integer.parseInt(
                SharedPref.getStringValue(SharedPref.USER, SharedPref.GENDER, this))));
        tv_company.setText(loa.getMemCompany());
        tv_date_approved.setText(DateConverter.convertDatetoMMMddyyy(loa.getDateAdmitted()));

        tv_doc_name.setText(loa.getDoctorName());
        tv_problem.setText(loa.getProcedureDesc());

        String changeFormat = DateConverter.convertDatetoMMMddyyy(loa.getDateAdmitted());
        tv_validity_date.setText(DateConverter.convertDateToMMddyyyy(changeFormat) + " to " +
                DateConverter.validityDatePLusDay(changeFormat, 3));
        tv_spec.setText(testData(loa.getDoctorSpec()));
    }


    @OnClick({R.id.btn_download, R.id.btn_cancel_req, R.id.btn_cancel})
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_download:
                screenshotCallback.onScreenShotListener();
                break;

            case R.id.btn_cancel:
                Intent intent = new Intent();
                setResult(RESULT_GETTER, intent);
                finish();
                break;

            case R.id.btn_cancel_req:
                implement.showCancelConfirmation();
                break;
        }


    }


    private String testData(String doctorSpec) {
        return doctorSpec.equals("") ? "Specialization Not Specified" : doctorSpec;
    }


    @Override
    public void onScreenShotListener() {
        Log.d("TRIGGERED", "TRIGGERED");
        Bitmap bitmap = Screenshot.loadBitmapFromView(content_loa_page);

        if (Permission.checkPermissionStorage(context)) {


            btn_download.setVisibility(View.GONE);
            btn_cancel.setVisibility(View.GONE);
            btn_cancel_req.setVisibility(View.GONE);

            new ImageSaver(context).
                    setFileName(loa.getApprovalNo()
                            + "_" + loa.getRemarks() + ".jpg")
                    .setDirectoryName("Medicard")
                    .setExternal(false)
                    .save(bitmap, screenshotCallback);

        }
    }

    @Override
    public void onSuccessfulScreenshot() {
        btn_download.setVisibility(View.VISIBLE);
        btn_cancel.setVisibility(View.VISIBLE);
        implement.setCancelButton(loa.getStatus(), btn_cancel_req);

        alertDialogCustom.showMe(context, alertDialogCustom.CONGRATULATIONS_title, alertDialogCustom.Saved_Screenshot, 2);

    }

    @Override
    public void onError(String message) {
        Log.e("ERROR", message);
        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);
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
        loader.stopLoad();
        alertDialogCustom.showMe(context, alertDialogCustom.success, alertDialogCustom.data_cancelled, 2);
        RESULT_GETTER = implement.setToLoadList(true);
    }

    @Override
    public void onCancelRequestListener() {
        loader.startLad();
        loader.setMessage("Cancelling Request");
        implement.cancelRequest(loa.getApprovalNo());
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        setResult(RESULT_GETTER, intent);
        finish();
    }
}

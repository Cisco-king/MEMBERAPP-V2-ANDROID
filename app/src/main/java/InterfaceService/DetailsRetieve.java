package InterfaceService;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;

import com.itextpdf.text.ExceptionConverter;
import com.medicard.member.R;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import model.Confirm;
import model.RequestResult;
import model.SendLoa;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import timber.log.Timber;
import utilities.Constant;
import utilities.Permission;
import utilities.PhoneInformations;
import utilities.SharedPref;
import v2.DetailsActivity;
import v2.RequestButtonsActivity;

/**
 * Created by mpx-pawpaw on 2/13/17.
 */

public class DetailsRetieve {


    private Context context;
    public static String CONSULTATION = "CONSULTATION";
    public static String MATERNITY = "MATERNITY";
    private DetailsActCallback callback;

    public DetailsRetieve(Context context, DetailsActCallback callback) {
        this.context = context;
        this.callback = callback;

    }

    public void showDialog(Context context, final String condition, final String destination) {

        final Dialog dialog = new Dialog(this.context);
        final Button btn_proceed, btn_cancel;


        final ProgressDialog pd;
        pd = new ProgressDialog(context, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Requesting Approval...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);


        TextView tv_title;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirmloa);
        btn_proceed = (Button) dialog.findViewById(R.id.btn_proceed);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        tv_title = (TextView) dialog.findViewById(R.id.tv_title);

        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                pd.show();
                try {
                    if (destination.equalsIgnoreCase(CONSULTATION)) {
                        sendConsultation(pd, condition);
                    } else if (destination.equalsIgnoreCase(MATERNITY)) {
                        sendMaternity(pd, condition);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dialog.dismiss();
            }
        });


        dialog.show();

        DisplayMetrics metrics = this.context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout(width, Toolbar.LayoutParams.WRAP_CONTENT);
    }

    private void sendMaternity(final ProgressDialog pd, String condition) {


        PhoneInformations phoneInformation;
        phoneInformation = new PhoneInformations();

        SendLoa loa = new SendLoa();
        loa.setDoctorCode(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_CODE, context));
        loa.setDeviceID(phoneInformation.getIMEI(context));
        loa.setDiagnosisCode("");
        loa.setHospitalCode(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CODE, context));
        loa.setLocationCode("");
        loa.setMemberCode(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context));
        loa.setProcedureAmount("");
        loa.setProcedureCode("");
        loa.setUsername(SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context));
        loa.setProcedureDesc("");
        loa.setDiagnosisDesc("");
        loa.setPrimaryComplaint(condition);
        Gson gson = new Gson();
        Log.d("TO_SEND", gson.toJson(loa));


        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.sendMaternity(loa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<RequestResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        try {

                            pd.dismiss();
                            callback.onError(e.getMessage());
                        } catch (Exception error) {
                            pd.dismiss();
                            callback.onError(e.getMessage());

                            Log.e("Rx_ERROR", error.getMessage());
                        }

                    }

                    @Override
                    public void onNext(RequestResult requestResult) {
                        //   Log.d("REQUEST", requestResult.toString());

                        try {
                            if (requestResult.getResponseCode().equals("210")) {
                                callback.onDuplicateRequest(requestResult);
                            } else if (requestResult.getResponseCode().equals("220")) {
                                callback.onBlockRequest(requestResult.getResponseDesc());
                            } else {
                                callback.onSuccess(requestResult);
                            }

                            pd.dismiss();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });


    }

    private void sendConsultation(final ProgressDialog pd, String condition) {

        PhoneInformations phoneInformation;
        phoneInformation = new PhoneInformations();

        SendLoa loa = new SendLoa();
        loa.setDoctorCode(SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_CODE, context));
        loa.setDeviceID(phoneInformation.getIMEI(context));
        loa.setDiagnosisCode("");
        loa.setHospitalCode(SharedPref.getStringValue(SharedPref.USER, SharedPref.HOSPITAL_CODE, context));
        loa.setLocationCode("");
        loa.setMemberCode(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context));
        loa.setProcedureAmount("");
        loa.setProcedureCode("");
        loa.setUsername(SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context));
        loa.setProcedureDesc("");
        loa.setDiagnosisDesc("");
        loa.setPrimaryComplaint(condition);
        Gson gson = new Gson();
        Log.d("TO_SEND", gson.toJson(loa));


        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.sendConsultation(loa)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<RequestResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        try {
                            Timber.d("error %s", e.toString());
                            pd.dismiss();
                            callback.onError(e.getMessage());
                        } catch (Exception error) {

                            pd.dismiss();
                            callback.onError(e.getMessage());

                            Log.e("Rx_ERROR", error.getMessage());
                        }

                    }

                    @Override
                    public void onNext(RequestResult requestResult) {
                        Log.d("REQUEST", requestResult.toString());
                        Timber.d("response %s", requestResult.toString());

                        if (requestResult.getResponseCode().equals("210")) {
                            callback.onDuplicateRequest(requestResult);
                        } else if (requestResult.getResponseCode().equals("220")) {
                            callback.onBlockRequest(requestResult.getResponseDesc());
                        } else {
                            callback.onSuccess(requestResult);
                        }


                        pd.dismiss();
                    }
                });


    }


    public void testAndSubmitData(EditText et_input_diagnosis, EditText et_doctor, String doctor_name, String doctor_code) {


        String destination = SharedPref.getStringValue(SharedPref.USER, SharedPref.DESTINATION, context);
        if (destination.equals(RequestButtonsActivity.CONSULT) || destination.equals(RequestButtonsActivity.MATERNITY)) {

            if (et_input_diagnosis.getText().toString().trim().equals("")) {
                callback.emptyFieldsListener();
            } else {
                if (doctor_name.equals(Constant.NOT_FOUND) && et_doctor.getText().toString().equals("")) {
                    callback.emptyFieldsListener();
                } else {
                    if (doctor_name.equals(Constant.NOT_SET))
                        callback.emptyFieldsListener();
                    else {
                        if (doctor_code.equals(Constant.NOT_FOUND)) {
                            callback.emptyFieldsListener();
                        } else {


                            if (destination.equals(RequestButtonsActivity.MATERNITY)) {
                                if (Permission.checkPermissionPhone(context))
                                    showDialog(context, et_input_diagnosis.getText().toString().trim(), DetailsRetieve.MATERNITY);
                            } else {
                                if (Permission.checkPermissionPhone(context))
                                    showDialog(context, et_input_diagnosis.getText().toString().trim(), DetailsRetieve.CONSULTATION);
                            }
                        }
                    }
                }

            }

        }

    }

    public void setSubmitButtonDisabled(CheckBox cb_confirm, Button btn_submit) {

        if (cb_confirm.isChecked()) {
            btn_submit.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
            btn_submit.setEnabled(true);
            btn_submit.setClickable(true);
        } else {
            btn_submit.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
            btn_submit.setEnabled(false);
            btn_submit.setClickable(false);
        }

    }

    public String setNull(String getData) {
        String data = " ";

        try {
            if (!getData.equals("null"))
                data = getData + " letmeout ";
        } catch (Exception e) {

        }


        String regex2 = "\\s*\\bletmeout\\b\\s*";
        data = data.replaceAll(regex2, "");
        Log.d("RETURN_withProvider", data);
        return data;
    }

    public void setDoctorFieldEditText(boolean b, LinearLayout ll_doctor_not_found, EditText et_doctor) {
        if (b) {
            ll_doctor_not_found.setVisibility(View.GONE);
            et_doctor.setVisibility(View.VISIBLE);
        } else {
            ll_doctor_not_found.setVisibility(View.VISIBLE);
            et_doctor.setVisibility(View.GONE);
        }


    }

    public void showDisregardDialog() {


        final Dialog dialog = new Dialog(this.context);
        final Button btn_proceed, btn_cancel;

        TextView tv_title, textView3;
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_confirmloa);
        btn_proceed = (Button) dialog.findViewById(R.id.btn_proceed);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        textView3 = (TextView) dialog.findViewById(R.id.textView3);

        tv_title.setText(context.getString(R.string.cancel_req));
        textView3.setText(context.getString(R.string.disregard_all_changes));


        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callback.cancelRequest();
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });


        dialog.show();

        DisplayMetrics metrics = this.context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout(width, Toolbar.LayoutParams.WRAP_CONTENT);


    }


    public void sendConfirmConsult(final RequestResult requestResult) {

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.confirmLoaConsult(requestResult.getBatchCode())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Confirm>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onErrorConfirm(e.getMessage());
                    }

                    @Override
                    public void onNext(Confirm confirm) {
                        Log.d("CONFIRM", confirm.toString());
                        //  if (confirm.getResponseCode().equals("200"))
                        callback.onSuccessConfirm(requestResult);


                    }
                });
    }

}

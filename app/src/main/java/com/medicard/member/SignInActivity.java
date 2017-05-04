package com.medicard.member;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import com.medicard.member.ChangePassword.ChangePasswordActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import InterfaceService.SignInCallback;
import InterfaceService.SignInRetrieve;
import Sqlite.DatabaseHandler;
import Sqlite.SetCityToDatabase;
import Sqlite.SetHospitalToDatabase;
import Sqlite.SetProvinceToDatabase;
import Sqlite.SetSpecializationTodDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import model.City;
import model.Hospital;
import model.LogIn;
import model.Province;
import model.SignInDetails;
import model.SpecializationList;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.AccessGooglePlay;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.Permission;
import utilities.SharedPref;
import utilities.NetworkTest;
import utilities.UpdateCaller;


public class SignInActivity extends AppCompatActivity
        implements View.OnClickListener, SignInCallback, UpdateCaller.DialogUpdateInterface {

    @BindView(R.id.btn_signUp) Button btn_signUp;
    @BindView(R.id.btn_signIn) Button btn_signIn;

    @BindView(R.id.ed_password) EditText ed_password;
    @BindView(R.id.ed_userid) EditText ed_userid;

    @BindView(R.id.tv_forgot_password) TextView tv_forgot_password;

    @BindView(R.id.coords) CoordinatorLayout coords;

    Context context;

    DatabaseHandler databaseHandler;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();

    ProgressDialog pd;
    Permission permission;

    final String TAG = "SIGNIN";
    SharedPref s = new SharedPref();
    SignInRetrieve implement;
    SignInCallback callback;

    String memCode;
    String name;
    String getUsername;
    String getPassword;
    String forced_Change;
    String username, password;
    SignInDetails signInDetails;

    UpdateCaller.DialogUpdateInterface callbackDialog;

    private Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        unbinder = ButterKnife.bind(this);

        context = this;
        callback = this;
        callbackDialog = this;

        implement = new SignInRetrieve(context, callback);

        btn_signUp.setOnClickListener(this);
        btn_signIn.setOnClickListener(this);
        tv_forgot_password.setOnClickListener(this);

        pd = new ProgressDialog(SignInActivity.this, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Logging in...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        permission = new Permission();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }

    public void signIn(final String getUsername, final String getPassword) {
        pd.show();


        s.setStringValue(s.USER, s.USERNAME, getUsername, context);

        LogIn logIn = new LogIn();
        logIn.setUsername(getUsername);
        logIn.setPassword(getPassword);
        logIn.setVersionNo(BuildConfig.VERSION_NAME);
        Gson gson = new Gson();
        Log.d("JSON", gson.toJson(logIn));

        AppInterface appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.logInUser(logIn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<SignInDetails>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.e(TAG, e.getMessage());

                            if (e.getMessage().toString().contains("HTTP 401")) {
                                alertDialogCustom.showMe(
                                        context,
                                        alertDialogCustom.HOLD_ON_title,
                                        alertDialogCustom.INVALID_PASS_USER, 1);

                            } else if (e.getMessage().toString().contains("HTTP 404")) {
                                alertDialogCustom.showMe(
                                        context,
                                        alertDialogCustom.HOLD_ON_title,
                                        alertDialogCustom.errorNoUsername, 1);
                            }

                            pd.dismiss();
                            ed_password.setText("");

                        } catch (Exception error) {
                            pd.dismiss();

                            alertDialogCustom.showMe(
                                    context,
                                    alertDialogCustom.HOLD_ON_title,
                                    ErrorMessage.setErrorMessage(e.getMessage()), 1);

                            Log.e("Rx_ERROR", error.getMessage());
                        }
                    }

                    @Override
                    public void onNext(SignInDetails responseBody) {
                        Log.d("HAHAHAHA", responseBody.toString());

                        if (responseBody.getResponseCode().equals("210")) {
                            alertDialogCustom.showMe(context,
                                    alertDialogCustom.HOLD_ON_title,
                                    alertDialogCustom.errorNoUsername,
                                    1);

                            pd.dismiss();

                        } else if (responseBody.getResponseCode().equals("220")) {
                            alertDialogCustom.showMe(
                                    context,
                                    alertDialogCustom.HOLD_ON_title,
                                    alertDialogCustom.errorAccountLocked,
                                    1);

                            pd.dismiss();

                        } else if (responseBody.getResponseCode().equals("230")) {
                            alertDialogCustom.showMe(context,
                                    alertDialogCustom.HOLD_ON_title,
                                    alertDialogCustom.INVALID_PASS_USER,
                                    1);

                            pd.dismiss();

                        } else if (responseBody.getResponseCode().equals("200")) {

                            signInDetails = responseBody;
                            username = getUsername;
                            password = getPassword;

                            signinCredential(signInDetails);
                        } else if (responseBody.getResponseCode().equals("280")) {
                            signInDetails = responseBody;
                            username = getUsername;
                            password = getPassword;
                            UpdateCaller.showUpdateCall(
                                    context,
                                    "Optional Update Available",
                                    false,
                                    callbackDialog);

                        } else if (responseBody.getResponseCode().equals("290")) {
                            UpdateCaller.showUpdateCall(
                                    context, "Update Required", true, callbackDialog);
                        } else {
                            alertDialogCustom.showMe(
                                    context,
                                    alertDialogCustom.HOLD_ON_title,
                                    ErrorMessage.setErrorMessage(""),
                                    1);
                        }

                        Log.d("PIN", SharedPref.getStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, context));
                    }
                });
    }

    private void signinCredential(SignInDetails responseBody) {
        pd.setMessage("Updating Hospitals...");
        getHospitalList(responseBody, username, password);

        memCode = responseBody.getUserAccount().getMEM_CODE();

        name = responseBody.getUserAccount().getMEM_FNAME() + " " + responseBody.getUserAccount().getMEM_LNAME();
        getUsername = username;
        getPassword = password;
        forced_Change = responseBody.getUserAccount().getFORCE_CHANGE_PASSWORD();
        try {

            String test = responseBody.getUserAccount().getPIN();
            Log.d("PIN", test);
            SharedPref.setStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, "TRUE", context);
            SharedPref.setStringValue(SharedPref.USER, SharedPref.PIN, responseBody.getUserAccount().getPIN(), context);

        } catch (Exception e) {

            SharedPref.setStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, "FALSE", context);
        }
    }


    private void getHospitalList(final SignInDetails responseBody, final String username, final String password) {

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getHospital()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Hospital>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        try {
                            Log.d("ERROR_SIGN", e.getMessage());

                            pd.dismiss();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(e.getMessage()), 1);

                        } catch (Exception error) {
                            pd.dismiss();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(e.getMessage()), 1);

                            Log.e("Rx_ERROR", error.getCause().getMessage());
                            Log.d("ERROR_SIGN", e.getMessage());
                        }


                    }

                    @Override
                    public void onNext(Hospital hospital) {
                        Log.d("Hospital", hospital.toString());

                        setToDatabase(hospital, responseBody);

                    }
                });

    }

    private void setToDatabase(final Hospital hospital, final SignInDetails responseBody) {


        AsyncTask setHosp = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                databaseHandler.dropHospital();
                SetHospitalToDatabase.setHospToDb(hospital.getHospitalList(), databaseHandler);

                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                databaseHandler = new DatabaseHandler(context);

            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                String data = "";
                String filterNull = "";
                try {
                    filterNull = SharedPref.getStringValue(SharedPref.USER, SharedPref.FIRST_TIME, context);
                    if (!filterNull.equals("null"))
                        data = filterNull;
                } catch (Exception e) {

                }
                Log.d("LOG_IN", data);
                if (data.equals("TRUE") || data.equals("")) {
                    pd.setMessage("Updating Database...");
                    databaseHandler.dropProvince();
                    databaseHandler.dropCity();
                    databaseHandler.dropSpecialization();
                    implement.getProvince();
                } else {

                    gotoNavigationTest(responseBody);


                }


            }
        };


        setHosp.execute();


    }

    private void gotoNavigationTest(SignInDetails responseBody) {


        SharedPref.setStringValue(SharedPref.USER, SharedPref.MEMBERCODE, memCode, context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.NAME, name, context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.masterUSERNAME, getUsername, context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.masterPASSWORD, getPassword, context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.FORCE_CHANGE_PASSWORD, forced_Change, context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.DISCLAIMER, responseBody.getUserAccount().getHAS_DISCLAIMER(), context);

        SharedPref.setStringValue(SharedPref.USER, SharedPref.FIRST_TIME, "FALSE", context);
        Log.i("FORCE CHANGE PASSWORD: ", SharedPref.getStringValue(SharedPref.USER, SharedPref.FORCE_CHANGE_PASSWORD, context));
        Log.i("get_has_disclaimer: ", responseBody.getUserAccount().getHAS_DISCLAIMER());


        if (SharedPref.getStringValue(SharedPref.USER, SharedPref.FORCE_CHANGE_PASSWORD, context).equals("1")) {

            startActivity(new Intent(context, ChangePasswordActivity.class));
            finish();
            pd.dismiss();
        } else {
            startActivity(new Intent(context, NavigationActivity.class));
            finish();
            pd.dismiss();

        }


    }

    @Override
    public void onClick(View view) {


        switch (view.getId()) {
            case R.id.btn_signIn:


                if (ed_userid.getText().toString().equals("") || ed_password.getText().toString().trim().equals("")) {
                    ed_password.setText("");
                    alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorEmptyFields, 1);
                    //snackBar.SnackBar(coords, snackBar.INPUTCREDS);
                } else {

                    if (permission.checkPermissionPhone(context)) {
                        if (NetworkTest.isOnline(context)) {
                            signIn(ed_userid.getText().toString(), ed_password.getText().toString());
                        } else
                            alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);

                    }
                }
                break;

            case R.id.btn_signUp:
                startActivity(new Intent(context, RegistrationActivity.class));
                break;

            case R.id.tv_forgot_password:


                startActivity(new Intent(context, RequestChangePassword.class));

                break;
        }
    }

    @Override
    public void onSuccessProvince(Province province) {
        SetProvinceToDatabase.setProvince(province.getProvinces(), databaseHandler, callback);
    }

    @Override
    public void onErrorProvince(String message) {
        Log.d("ERROR_Province", message);
        pd.dismiss();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onSuccessCity(City city) {
        SetCityToDatabase.setCity(city.getCities(), databaseHandler, callback);
    }

    @Override
    public void onErrorCity(String message) {
        Log.d("ERROR_City", message);

        pd.dismiss();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onProvincetoDbListener() {
        pd.setMessage("Updating Database...");
        implement.getCity();
    }

    @Override
    public void onCitytoDbListener() {
        pd.setMessage("Updating Database...");
        implement.getSpecialization();
    }

    @Override
    public void onErrorSpecs(String message) {
        Log.d("ERROR_City", message);

        pd.dismiss();
        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, ErrorMessage.setErrorMessage(message), 1);
    }

    @Override
    public void onSuccessSpecs(SpecializationList specializationList) {

        SetSpecializationTodDatabase.setSpecs(specializationList.getSpecializations(), context, callback);
    }

    @Override
    public void onSpecsToDBListener() {
        gotoNavigationTest(signInDetails);
    }

    @Override
    public void updateRequired() {
        AccessGooglePlay.openAppInGooglePlay(context);
    }

    @Override
    public void updateNotRequired() {
        signinCredential(signInDetails);
    }
}
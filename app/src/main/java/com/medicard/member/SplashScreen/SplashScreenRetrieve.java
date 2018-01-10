package com.medicard.member.SplashScreen;

import android.content.Context;
import android.content.Intent;

import com.medicard.member.BuildConfig;
import com.medicard.member.ChangePassword.ChangePasswordActivity;
import com.medicard.member.NavigationActivity;

import android.util.Log;

import com.google.gson.Gson;

import model.LogIn;
import model.SignInDetails;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.SharedPref;
import utilities.UpdateCaller;

/**
 * Created by mpx-pawpaw on 1/27/17.
 */

public class SplashScreenRetrieve {

    private Context context;
    private SplashScreenInterface callback;

    public SplashScreenRetrieve(Context context, SplashScreenInterface callback) {
        this.callback = callback;
        this.context = context;
    }

    /**
     * TEST CREDENTIALS IF ALREADY EXIST
     * IT WILL SIGN IN ELSE
     * GO TO SIGN ACT. THEN ENTER CREDENTIALS
     * @param alertDialogCustom
     */
    public void testCredentials(AlertDialogCustom alertDialogCustom) {


        if (SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context).equals("null") ||
                SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context).equals("") ||
                SharedPref.getStringValue(SharedPref.USER, SharedPref.masterPASSWORD, context).equals("null") ||
                SharedPref.getStringValue(SharedPref.USER, SharedPref.masterPASSWORD, context).equals("")) {

            callback.onCredentialTestError();
        } else {

            Log.i("NOT NULL", "NOT NULL");

            signIn(SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context),
                    SharedPref.getStringValue(SharedPref.USER, SharedPref.masterPASSWORD, context),alertDialogCustom);

        }

    }


    /**
     * CONNECT TO TO BACK END
     *  @param username
     * @param password
     * @param alertDialogCustom
     */
    public void signIn(final String username, final String password, final AlertDialogCustom alertDialogCustom) {

        SharedPref.setStringValue(SharedPref.USER, SharedPref.USERNAME, username, context);

        LogIn logIn = new LogIn();
        logIn.setUsername(username);
        logIn.setPassword(password);
        logIn.setVersionNo(BuildConfig.VERSION_NAME);
        Gson gson = new Gson();
        Log.d("JSON", gson.toJson(logIn));



        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.logInUser(logIn)
                .enqueue(new Callback<SignInDetails>() {
                    @Override
                    public void onResponse(Call<SignInDetails> call, Response<SignInDetails> response) {
                        if(response.body() != null){
                            callback.onSignInSuccessListener(response.body(), username, password);
                        }else {
                            alertDialogCustom.showMe(
                                    context,
                                    alertDialogCustom.HOLD_ON_title,
                                    alertDialogCustom.no_connection_to_server,
                                    1);
                        }

                    }

                    @Override
                    public void onFailure(Call<SignInDetails> call, Throwable e) {
                        try {
                            callback.onSignInErrorListener(e.getMessage());

                        } catch (Exception error) {
                            callback.onSignInErrorListener(e.getMessage());

                            Log.e("RX_ERROR", error.getMessage());
                        }

                    }
                });
    }

    /**
     * SAVE DATA TO SHAREDPREF
     *
     * @param responseBody
     * @param username
     * @param password
     */
    public void saveData(SignInDetails responseBody, String username, String password) {


        //    Log.i("RESPONSE", responseBody.getResponseCode());

        SharedPref.setStringValue(SharedPref.USER, SharedPref.FORCE_CHANGE_PASSWORD, responseBody.getUserAccount().getFORCE_CHANGE_PASSWORD(), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.masterUSERNAME, username, context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.masterPASSWORD, password, context);

        if (SharedPref.getStringValue(SharedPref.USER, SharedPref.FORCE_CHANGE_PASSWORD, context).equals("1")) {

            callback.setActivity(new Intent(context, ChangePasswordActivity.class));

        } else {

            callback.setActivity(new Intent(context, NavigationActivity.class));

        }

        try {

            String test = responseBody.getUserAccount().getPIN();
            //  Log.d("PIN", test);
            SharedPref.setStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, "TRUE", context);

        } catch (Exception e) {

            SharedPref.setStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, "FALSE", context);
        }

        //  Log.d("PIN", SharedPref.getStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, context));


    }
}


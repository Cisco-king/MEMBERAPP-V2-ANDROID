package com.medicard.member.SplashScreen;

import android.content.Context;
import android.content.Intent;
import com.medicard.member.ChangePassword.ChangePasswordActivity;
import com.medicard.member.NavigationActivity;
import android.util.Log;

import com.google.gson.Gson;

import model.LogIn;
import model.SignInDetails;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.SharedPref;

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
     */
    public void testCredentials() {


        if (SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context).equals("null") ||
                SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context).equals("") ||
                SharedPref.getStringValue(SharedPref.USER, SharedPref.masterPASSWORD, context).equals("null") ||
                SharedPref.getStringValue(SharedPref.USER, SharedPref.masterPASSWORD, context).equals("")) {

            callback.onCredentialTestError();
        } else {

            Log.i("NOT NULL", "NOT NULL");

            signIn(SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context),
                    SharedPref.getStringValue(SharedPref.USER, SharedPref.masterPASSWORD, context));

        }

    }


    /**
     * CONNECT TO TO BACK END
     *
     * @param username
     * @param password
     */
    public void signIn(final String username, final String password) {

        SharedPref.setStringValue(SharedPref.USER, SharedPref.USERNAME, username, context);

        LogIn logIn = new LogIn();
        logIn.setUsername(username);
        logIn.setPassword(password);
        Gson gson = new Gson();
        Log.d("JSON", gson.toJson(logIn));
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        //   loginSubscription = appInterface.logInUser(logIn)
        appInterface.logInUser(logIn)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<SignInDetails>() {
                    @Override
                    public void onCompleted() {

                        Log.i("hahahaha", "gahahaha");

                    }

                    @Override
                    public void onError(Throwable e) {

                        try {
                            callback.onSignInErrorListener(e.getMessage());

                        } catch (Exception error) {
                            callback.onSignInErrorListener(e.getMessage());

                            Log.e("RX_ERROR", error.getMessage());
                        }
                    }

                    @Override
                    public void onNext(SignInDetails responseBody) {
                        callback.onSignInSuccessListener(responseBody, username, password);

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


        Log.i("RESPONSE", responseBody.getResponseCode());

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
            Log.d("PIN", test);
            SharedPref.setStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, "TRUE", context);

        } catch (Exception e) {

            SharedPref.setStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, "FALSE", context);
        }

        Log.d("PIN", SharedPref.getStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, context));


    }
}


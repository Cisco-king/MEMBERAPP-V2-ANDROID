package com.medicard.member.SplashScreen;

import android.content.Context;
import android.content.Intent;

import com.medicard.member.NavigationActivity;
import com.medicard.member.R;
import com.medicard.member.SignInActivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import model.SignInDetails;
import utilities.AccessGooglePlay;
import utilities.AlertDialogCustom;
import utilities.SharedPref;
import utilities.UpdateCaller;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenInterface, UpdateCaller.DialogUpdateInterface {

    Context context;
    final String TAG = "SPLASHSCREEN";
    SharedPref s = new SharedPref();


    SplashScreenInterface callback;
    SplashScreenRetrieve implement;
    AlertDialogCustom alertDialogCustom;
    UpdateCaller.DialogUpdateInterface callbackDialog;

    SignInDetails signInDetails;
    String password, username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = this;
        callback = this;
        callbackDialog = this;
        implement = new SplashScreenRetrieve(context, callback);
        alertDialogCustom = new AlertDialogCustom();
        String username = s.getStringValue(s.USER, s.masterUSERNAME, context);
        String Password = s.getStringValue(s.USER, s.masterPASSWORD, context);

        Log.i("USERNAME", username);
        Log.i("Password", Password);


        implement.testCredentials();


    }


    @Override
    public void onCredentialTestError() {
        startActivity(new Intent(context, SignInActivity.class));
        finish();
    }

    @Override
    public void onSignInErrorListener(String message) {
        Log.e("SPLASH_ERROR", message);
        setActivity(new Intent(context, SignInActivity.class));
    }

    @Override
    public void onSignInSuccessListener(SignInDetails test, String username, String password) {


        /**
         *
         Added
         Code: 270
         Desc: Device is not assigned to AppUser

         Code: 280
         Desc: Optional Update Available

         Code: 290
         Desc: Update Required
         */
        if (test.getResponseCode().equals("200")) {
            signInDetails = test;
            this.username = username;
            this.password = password;
            implement.saveData(test, username, password);
        } else if (test.getResponseCode().equals("280")) {
        signInDetails = test;
        this.username = username;
        this.password = password;
        UpdateCaller.showUpdateCall(context, "Optional Update Available", false, callbackDialog);
        } else if (test.getResponseCode().equals("290")) {
            UpdateCaller.showUpdateCall(context, "Update Required", true, callbackDialog);
        } else {
            callback.setActivity(new Intent(context, SignInActivity.class));
        }


    }

    @Override
    public void setActivity(Intent intent) {
        startActivity(intent);
        finish();
    }

    @Override
    public void updateRequired() {
        AccessGooglePlay.openAppInGooglePlay(context);
    }

    @Override
    public void updateNotRequired() {
        implement.saveData(signInDetails, username, password);
    }
}

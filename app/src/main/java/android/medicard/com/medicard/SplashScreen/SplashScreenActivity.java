package android.medicard.com.medicard.SplashScreen;

import android.content.Context;
import android.content.Intent;
import android.medicard.com.medicard.R;
import android.medicard.com.medicard.SignInActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import model.SignInDetails;
import utilities.SharedPref;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenInterface {

    Context context;
    final String TAG = "SPLASHSCREEN";
    SharedPref s = new SharedPref();


    SplashScreenInterface callback;
    SplashScreenRetrieve implement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = this;
        callback = this;
        implement = new SplashScreenRetrieve(context, callback);

        String username = s.getStringValue(s.USER, s.masterUSERNAME, context);
        String Password = s.getStringValue(s.USER, s.masterPASSWORD, context);

        Log.i("USERNAME", username);
        Log.i("Password", Password);


        implement.testCredentials();


    }


//
//    private void setActivity(Intent intent) {
//        startActivity(intent);
//        finish();
//    }

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
    public void onSignInSuccessListener(SignInDetails responseBody, String username, String password) {
        implement.saveData(responseBody , username , password);
    }

    @Override
    public void setActivity(Intent intent) {
        startActivity(intent);
        finish();
    }
}

package android.medicard.com.medicard.SplashScreen;

import android.content.Intent;

import model.SignInDetails;

/**
 * Created by mpx-pawpaw on 1/27/17.
 */

public interface SplashScreenInterface {
    void onCredentialTestError();

    void onSignInErrorListener(String message);

    void onSignInSuccessListener(SignInDetails responseBody, String username, String password);

    void setActivity(Intent intent);
}

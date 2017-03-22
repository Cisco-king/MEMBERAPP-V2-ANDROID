package InterfaceService;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import model.ChangePassword;
import model.Pin;
import model.Pinned;
import model.ReturnChangePassword;
import model.UpdatePin;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.AlertDialogCustom;
import utilities.NetworkTest;
import utilities.PasswordTester;
import utilities.SharedPref;

import static java.security.AccessController.getContext;

/**
 * Created by mpx-pawpaw on 2/6/17.
 */

public class ChangePasswordWithPinRetrieve {

    PasswordTester pTest = new PasswordTester();
    ChangePasswordWithPinCallback callback;
    Context context;

    public ChangePasswordWithPinRetrieve(FragmentActivity activity, ChangePasswordWithPinCallback callback) {
        this.callback = callback;
        context = activity;
    }

    public void setEmailAdd(TextView et_emailAdd) {


        et_emailAdd.setText(SharedPref.getStringValue(SharedPref.USER, SharedPref.USERNAME, context));

    }

    public void testChangePassword(TextView et_emailAdd, EditText et_newPass, EditText et_oldPass, EditText et_retypePass) {


        boolean passwordCorrect = pTest.tester(et_newPass.getText().toString().trim());


        if (et_emailAdd.getText().toString().equals("") || et_oldPass.getText().toString().equals("") || et_newPass.getText().toString().equals("")) {
            callback.incorrectFiledListener();
        } else {
            if (passwordCorrect) {

                if (et_newPass.getText().toString().trim().equals(et_retypePass.getText().toString().trim())) {

                    if (NetworkTest.isOnline(context)) {
                        callback.sendPasswordListener();
                    } else
                        callback.noInternetConnectionListener();

                } else {
                    callback.notEqualPasswordListener();
                }


            } else {

                callback.passwordWrongFormatListener();
            }


        }


    }

    public void changePassword(String emailAddress, String oldPassword, String newPassword) {


        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword(newPassword);
        changePassword.setUsername(emailAddress);
        changePassword.setOldPassword(oldPassword);


        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.changePassword(changePassword)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ReturnChangePassword>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        try {

                            callback.onErrorChangePassword();
                        } catch (Exception error) {
                            AlertDialogCustom alert = new AlertDialogCustom();
                            alert.showMe(context, alert.HOLD_ON_title, alert.unknown_msg, 1);


                            Log.e("Rx_ERROR", error.getMessage());
                        }


                    }

                    @Override
                    public void onNext(ReturnChangePassword returnChangePassword) {
                        callback.onSuccessListener(returnChangePassword);
                    }
                });


    }


    public void setVisibility(CardView cv_new, CardView cv_old, TextView tv_current_pin) {

        if (SharedPref.getStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, context).equals("TRUE")) {
            tv_current_pin.setText("Your current PIN is : " + SharedPref.getStringValue(SharedPref.USER, SharedPref.PIN, context));
            cv_new.setVisibility(View.GONE);
            cv_old.setVisibility(View.VISIBLE);
        } else {
            cv_new.setVisibility(View.VISIBLE);
            cv_old.setVisibility(View.GONE);
        }

    }


    public void testInput(EditText et_old_pin, EditText et_new_pin, EditText et_retype_pin_new) {

        Log.e("ET_old_PINNED", getText(et_old_pin));
        Log.e("ET_new_PINNED", getText(et_new_pin));
        Log.e("ET_RETYPE_PINNED", getText(et_retype_pin_new));


        if (getText(et_old_pin).equals("") || getText(et_new_pin).equals("") || getText(et_retype_pin_new).equals("")) {
            callback.testInputListener();
        } else {

            if (getText(et_new_pin).equals(getText(et_retype_pin_new))) {
                callback.updatePin(getText(et_new_pin), getText(et_old_pin));
            } else
                callback.testInputPinListener();

        }
    }


    @NonNull
    private String getText(EditText data) {
        return data.getText().toString().trim();
    }

    public void testupdatePin(String newPIN, String oldPIN) {
        if (NetworkTest.isOnline(context))
            updatePin(newPIN, oldPIN);
        else
            callback.noInternetConnectionListener();
    }

    private void updatePin(final String newPIN, String oldPIN) {


        UpdatePin updatePin = new UpdatePin();
        updatePin.setNewPin(newPIN);
        updatePin.setOldPin(oldPIN);
        updatePin.setUsername(SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context));

        Gson gson = new Gson();
        Log.d("UPDATE_PIN", gson.toJson(updatePin));

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.updatePin(updatePin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Pinned>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable error) {
                        try {
                            callback.onErrorUpdatePin();
                        } catch (Exception e) {
                            AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);
                        }
                    }

                    @Override
                    public void onNext(Pinned pinned) {
                        Log.e("PINNED", pinned.toString());
                        callback.onSuccessUpdatePin(pinned , newPIN);
                    }
                });

    }

    public void testInput(EditText et_pin, EditText et_retype_pin) {

        Log.e("ET_PINNED", getText(et_pin));
        Log.e("ET_RETYPE_PINNED", getText(et_retype_pin));

        if (getText(et_pin).equals("") || getText(et_retype_pin).equals("")) {
            callback.testInputListener();
        } else {

            if (getText(et_pin).equals(getText(et_retype_pin))) {
                callback.registerPin(getText(et_pin));
            } else
                callback.testInputPinListener();


        }


    }

    public void testRegisterPin(String pin) {
        if (NetworkTest.isOnline(context))
            registerPin(pin);
        else
            callback.noInternetConnectionListener();
    }

    private void registerPin(final String newPin) {

        Pin pin = new Pin();
        pin.setUsername(SharedPref.getStringValue(SharedPref.USER, SharedPref.masterUSERNAME, context));
        pin.setPassword(SharedPref.getStringValue(SharedPref.USER, SharedPref.masterPASSWORD, context));
        pin.setPin(newPin);


        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.regPin(pin)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Pinned>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable error) {
                        try {
                            callback.onErrorRegisterPin(error);
                        } catch (Exception e) {
                            AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);
                        }
                    }

                    @Override
                    public void onNext(Pinned pinned) {
                        Log.e("PINNED", pinned.toString());
                        callback.onSuccessRegisterPin(pinned.getResponseCode() , newPin);
                    }
                });
    }

    public void setToEmpty(EditText et_old_pin, EditText et_new_pin, EditText et_retype_pin_new) {

        et_old_pin.setText("");
        et_retype_pin_new.setText("");
        et_new_pin.setText("");


    }

    public void updatePinUI(CardView cv_new, CardView cv_old, TextView tv_current_pin) {
        SharedPref.setStringValue(SharedPref.USER, SharedPref.PIN_IS_AVAILABLE, "TRUE", context);
        setVisibility(cv_new, cv_old, tv_current_pin);

    }
}

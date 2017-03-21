package com.medicard.member.ChangePassword;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import com.medicard.member.NavigationActivity;
import com.medicard.member.R;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import model.ChangePassword;
import model.ReturnChangePassword;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.AlertDialogCustom;
import utilities.NetworkTest;
import utilities.PasswordTester;
import utilities.SharedPref;
import utilities.SnackBar;

public class ChangePasswordActivity extends AppCompatActivity implements ChangePassInterface {


    EditText et_emailAdd, et_oldPass, et_newPass, et_retypePass;
    Button btn_changePassword;
    CoordinatorLayout coords;
    SnackBar snackBar;
    ProgressDialog pd;
    Context context;
    SharedPref s = new SharedPref();
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    PasswordTester pTest = new PasswordTester();


    TextView tv_message, tv_title;
    CircleImageView ci_error_image;
    Button btn_accept;


    ChangePassRetrieve implement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        context = this;
        implement = new ChangePassRetrieve(this);

        snackBar = new SnackBar();

        et_emailAdd = (EditText) findViewById(R.id.et_emailAdd);
        et_oldPass = (EditText) findViewById(R.id.et_oldPass);
        et_newPass = (EditText) findViewById(R.id.et_newPass);
        btn_changePassword = (Button) findViewById(R.id.btn_changePassword);
        coords = (CoordinatorLayout) findViewById(R.id.coords);
        et_retypePass = (EditText) findViewById(R.id.et_retypePass);

        pd = new ProgressDialog(context, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Requesting...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);

        et_emailAdd.requestFocus();

        et_emailAdd.setText(s.getStringValue(s.USER, s.USERNAME, context) + "");

        btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean passwordCorrect = pTest.tester(et_newPass.getText().toString().trim());

                if (et_emailAdd.getText().toString().equals("") || et_oldPass.getText().toString().equals("") || et_newPass.getText().toString().equals("")) {

                    alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorEmptyFields, 1);

                } else {

                    if (passwordCorrect) {


                        if (et_newPass.getText().toString().trim().equals(et_retypePass.getText().toString().trim())) {

                            if (NetworkTest.isOnline(context)) {
                                pd.show();
                                changePassword(et_emailAdd.getText().toString(), et_oldPass.getText().toString(), et_newPass.getText().toString());
                            } else {

                                alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);
                            }

                        } else {

                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.PASSWORD_RETYPEPASS_MATCH_message, 1);

                        }


                    } else {

                        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.PASSWORD_CRED_message, 1);

                    }
                }

            }
        });


        Log.i("FORCE CHANGE PASSWORD: ", s.getStringValue(s.USER, s.FORCE_CHANGE_PASSWORD, context));


        if (s.getStringValue(s.USER, s.FORCE_CHANGE_PASSWORD, context).equals("1")) {

            AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorNeedToChangePass, 1);

        }

    }

    private void changePassword(String emailAddress, String oldPassword, String newPassword) {


        ChangePassword changePassword = new ChangePassword();
        changePassword.setNewPassword(newPassword);
        changePassword.setUsername(emailAddress);
        changePassword.setOldPassword(oldPassword);


        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.changePassword(changePassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ReturnChangePassword>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        try {
                            pd.dismiss();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorUnableToChangePass, 1);

                        } catch (Exception error) {
                            pd.dismiss();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorUnableToChangePass, 1);

                            Log.e("Rx_ERROR", error.getMessage());
                        }


                    }

                    @Override
                    public void onNext(ReturnChangePassword returnChangePassword) {

                        pd.dismiss();

                        Log.d("RESPONSE", returnChangePassword.getResponseCode());

                        if (returnChangePassword.getResponseCode().equals("200")) {

                            s.setStringValue(s.USER, s.FORCE_CHANGE_PASSWORD, "0", context);
                            s.setStringValue(s.USER, s.masterUSERNAME, et_emailAdd.getText().toString(), context);
                            s.setStringValue(s.USER, s.masterPASSWORD, et_newPass.getText().toString(), context);

                            et_newPass.setText("");
                            et_oldPass.setText("");
                            //et_emailAdd.setText("");

                            showMeChangePass(context);


                        } else {

                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorUnableToChangePass, 1);
                        }


                    }
                });

    }

    public void showMeChangePass(final Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertshow);
        dialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        ci_error_image = (CircleImageView) dialog.findViewById(R.id.ci_error_image);
        tv_message = (TextView) dialog.findViewById(R.id.tv_message);
        tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        btn_accept = (Button) dialog.findViewById(R.id.btn_accept);
        ci_error_image.setImageDrawable(ContextCompat.getDrawable(context , R.drawable.warning));
        tv_title.setText("Success!");
        tv_message.setText("You have successfully changed your password.");
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(new Intent(context, NavigationActivity.class));
                finish();

            }
        });


        dialog.show();


        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.70);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = width;
        dialog.getWindow().setAttributes(lp);

    }

}

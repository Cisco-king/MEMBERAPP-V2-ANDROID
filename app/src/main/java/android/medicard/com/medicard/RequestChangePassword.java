package android.medicard.com.medicard;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
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
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import mehdi.sakout.fancybuttons.FancyButton;
import model.RequestNewPassword;
import model.ReturnRequestPassword;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.AlertDialogCustom;
import utilities.EmailTester;
import utilities.NetworkTest;
import utilities.PhoneInformations;
import utilities.SnackBar;

public class RequestChangePassword extends AppCompatActivity {


    EditText et_emailAdd, et_member_code;
    Button btn_sendNewPassword;
    SnackBar snackBar;
    CoordinatorLayout coords;
    ProgressDialog pd;
    PhoneInformations phoneInformation;
    Context context;
    FancyButton btn_back;
    Toolbar toolbar;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    EmailTester eTest = new EmailTester();

    //Alert
    TextView tv_message, tv_title;
    CircleImageView ci_error_image;
    Button btn_accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_change_password);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        snackBar = new SnackBar();
        phoneInformation = new PhoneInformations();
        context = this;

        btn_back = (FancyButton) toolbar.findViewById(R.id.btn_back);

        et_emailAdd = (EditText) findViewById(R.id.et_emailAdd);
        et_member_code = (EditText) findViewById(R.id.et_member_code);
        btn_sendNewPassword = (Button) findViewById(R.id.btn_sendNewPassword);
        coords = (CoordinatorLayout) findViewById(R.id.coords);

        et_emailAdd.requestFocus();


        pd = new ProgressDialog(this, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Requesting...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);



        btn_sendNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean emailCorrect = eTest.isEmailValid(et_emailAdd.getText().toString().trim());

                if (et_emailAdd.getText().toString().equals("") || et_member_code.getText().toString().equals("")){

                    alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorEmptyFields, 1);

                }else{

                    if (emailCorrect) {

                        if (NetworkTest.isOnline(context)) {

                            pd.show();
                            requestChangePassword(et_emailAdd.getText().toString(), et_member_code.getText().toString(), phoneInformation.getIMEI(context));
                        } else {

                            alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);

                        }

                    }else{

                        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.EMAIL_message, 1);

                    }

                }


            }
        });


        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

            }
        });

    }

    private void requestChangePassword(String emailAddress, String memberCode, String deviceId) {

        RequestNewPassword requestNewPassword = new RequestNewPassword();
        requestNewPassword.setDeviceId(deviceId);
        requestNewPassword.setEmail(emailAddress);
        requestNewPassword.setMemberCode(memberCode);

        AppInterface appInterface ;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.requestPassword(requestNewPassword)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ReturnRequestPassword>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        try {
                            Log.i("ERROR", e.getMessage());

                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorRequestUsernameOrPass, 1);

                            pd.dismiss();

                        }catch (Exception error){

                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorRequestUsernameOrPass, 1);

                            pd.dismiss();

                            Log.e("Rx_ERROR" , error.getMessage());
                        }


                    }

                    @Override
                    public void onNext(ReturnRequestPassword returnRequestPassword) {

                        pd.dismiss();

                        Log.i("RESPONSE", returnRequestPassword.getResponseCode());


                        if(returnRequestPassword.getResponseCode().equals("200")){

                            showMeSuccess(context, alertDialogCustom.success, alertDialogCustom.succesCheckEmailPassword);

                            et_emailAdd.setText("");
                            et_member_code.setText("");

                        }else{

                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorRequestUsernameOrPass, 1);

                        }
                        
                    }
                });



    }



    public void showMeSuccess(final Context context, String title, String message) {

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
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();


                finish();

            }
        });


        tv_message.setText(message);
        tv_title.setText(title);
        ci_error_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.success));
        btn_accept.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));


        dialog.show();


        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.70);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = width;
        dialog.getWindow().setAttributes(lp);

    }
}

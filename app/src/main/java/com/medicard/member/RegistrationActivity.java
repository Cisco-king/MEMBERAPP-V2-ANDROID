package com.medicard.member;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.Date;

import model.MemberInfo;
import model.RequestAccount;
import model.VerifyMember;
import model.VerifyMemberData;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.DatepickerSet;
import utilities.ErrorMessage;
import utilities.SharedPref;
import utilities.SnackBar;
import utilities.EmailTester;
import utilities.NetworkTest;
import utilities.PasswordTester;
import utilities.Permission;
import utilities.PhoneInformations;
import utilities.ShowTermsNCondition;
import utilities.ValidatorUtils;
import views.NoSpecialCharacterChecker;

import static android.R.color.black;

//7329
public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener {


    SnackBar snackBar = new SnackBar();
    PasswordTester pTest = new PasswordTester();
    EmailTester eTest = new EmailTester();
    AlertDialogCustom alert = new AlertDialogCustom();
    ShowTermsNCondition showTermsNCondition = new ShowTermsNCondition();
    Permission permission = new Permission();
    PhoneInformations phoneInformations = new PhoneInformations();

    EditText ed_email, ed_idNumber, ed_username, ed_password, ed_repassword, ed_contact;
    TextView btn_terms, ed_birth;
    Button btn_regAccount, btn_verify;

    CoordinatorLayout coords;
    Context context;
    TextView ed_memberName;

    String member_ID = "";
    String phone_ID = "";
    String DateofBirth = "";
    String member_Fname = "";
    String member_Lname = "";
    int member_Gender;
    ProgressDialog pd;
    final String TAG = "REGISTRATION";
    boolean calerdarIsSelected = false;
    int mDay, mMonth, mYear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        init();


    }

    private void init() {
        context = this;

        pd = new ProgressDialog(RegistrationActivity.this, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Creating Account...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);


        final Calendar c = Calendar.getInstance();
        int mYear_ = c.get(Calendar.YEAR); // current year
        int mMonth_ = c.get(Calendar.MONTH); // current month
        int mDay_ = c.get(Calendar.DAY_OF_MONTH); // current day
        calerdarIsSelected = true;


        SharedPref.setStringValue(SharedPref.USER, SharedPref.DAY, mDay_ + "", context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.MONTH, mMonth_ + "", context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.YEAR, mYear_ + "", context);



        if (!NetworkTest.isOnline(context)) {
            alert.showMe(context, alert.NO_Internet_title, alert.NO_Internet, 1);
        }

        coords = (CoordinatorLayout) findViewById(R.id.coords);
        ed_idNumber = (EditText) findViewById(R.id.ed_idNumber);
        ed_birth = (TextView) findViewById(R.id.ed_birth);
        ed_memberName = (TextView) findViewById(R.id.ed_memberName);

        ed_username = (EditText) findViewById(R.id.ed_username);
        ed_username.addTextChangedListener(new NoSpecialCharacterChecker(ed_username));

        ed_email = (EditText) findViewById(R.id.ed_email);
        ed_password = (EditText) findViewById(R.id.ed_password);
        ed_repassword = (EditText) findViewById(R.id.ed_repassword);
        ed_contact = (EditText) findViewById(R.id.ed_contact);
        btn_terms = (TextView) findViewById(R.id.btn_terms);
        btn_regAccount = (Button) findViewById(R.id.btn_regAccount);
        btn_verify = (Button) findViewById(R.id.btn_verify);
        widgetEnabler(false);


        btn_verify.setOnClickListener(this);
        btn_regAccount.setOnClickListener(this);
        ed_birth.setOnClickListener(this);
        btn_terms.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_verify:

                btnVerifyFunction();
                break;
            case R.id.btn_regAccount:
                btnRegisterAccountFunction();
                break;

            case R.id.ed_birth:

                final Calendar c = Calendar.getInstance();
                Date today = new Date();


                if (calerdarIsSelected) {

                    SharedPref sharedPref = new SharedPref();
                    mDay = Integer.parseInt(sharedPref.getStringValue(sharedPref.USER, sharedPref.DAY, context));
                    mMonth = Integer.parseInt(sharedPref.getStringValue(sharedPref.USER, sharedPref.MONTH, context));
                    mYear = Integer.parseInt(sharedPref.getStringValue(sharedPref.USER, sharedPref.YEAR, context));


                } else {


                    mYear = c.get(Calendar.YEAR); // current year
                    mMonth = c.get(Calendar.MONTH); // current month
                    mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                    calerdarIsSelected = true;


                }

                DatepickerSet datePicker = new DatepickerSet();
                datePicker.getDate(context, ed_birth, mYear, mMonth, mDay);
                Log.d(TAG, DateofBirth);
                break;

            case R.id.btn_terms:
                startActivity(new Intent(RegistrationActivity.this, TermsActivity.class));
                break;

        }
    }

    private void btnRegisterAccountFunction() {

        if (ed_username.getText().toString().equals("") ||
                ed_password.getText().toString().equals("") ||
                ed_repassword.getText().toString().equals("") ||
                ed_email.getText().toString().equals("")) {

            ed_repassword.setText("");
            ed_password.setText("");

            alert.showMe(context, alert.HOLD_ON_title, alert.errorEmptyFields, 1);

        } else if (ed_username.getText().toString().length() < 3) {

            alert.showMe(context, alert.HOLD_ON_title, alert.UsernameMinimumCharacter, 1);

        } else if (ed_username.getText().toString().contains(" ")) {

            alert.showMe(context, alert.HOLD_ON_title, alert.InvalidUsername, 1);

        } else if (ValidatorUtils.noSpecialCharacter(ed_username.getText().toString().trim())) {
            alert.showMe(context, alert.HOLD_ON_title, alert.noSpecialCharacter, 1);
        } else {
            boolean passwordCorrect = pTest.tester(ed_password.getText().toString().trim());
            boolean emailCorrect = eTest.isEmailValid(ed_email.getText().toString().trim());

            /**
             * TEST PASSWORD'S CREDIBILITY
             *      RETYPED PASSWORD
             *             EMAIL
             */

            if (passwordCorrect) {

                if (ed_password.getText().toString().contains(" ")) {

                    alert.showMe(context, alert.HOLD_ON_title, alert.PASSWORD_CRED_message, 1);


                } else if (ed_password.getText().toString().equals(ed_repassword.getText().toString())) {
                    if (emailCorrect) {
                        /**
                         * REGISTER ACCCOUT
                         */
                        showTermsNCondition.showTerms(context);

                    } else {
                        /**
                         * DIALOG FOR INCORRECT EMAILFORMAT
                         */

                        alert.showMe(context, alert.HOLD_ON_title, alert.EMAIL_message, 1);

                    }

                } else {
                    /**
                     * DIALOG FOR NOT MATCHING PASSWORD
                     */
                    alert.showMe(context, alert.HOLD_ON_title, alert.PASSWORD_MATCH_message, 1);

                }
            } else {
                /**
                 * DIALOG FOR INCORRECT PASSWORD FORMAT
                 */
                alert.showMe(context, alert.HOLD_ON_title, alert.PASSWORD_CRED_message, 1);

            }


        }
    }

    private void btnVerifyFunction() {
        member_ID = ed_idNumber.getText().toString().trim();
        if (member_ID.equals("")
                || DateofBirth.equals("")) {
            alert.showMe(context, alert.HOLD_ON_title, alert.errorEmptyFields, 1);
        } else {
            if (NetworkTest.isOnline(context)) {
                verifyMember(member_ID, DateofBirth);
            } else
                alert.showMe(context, alert.NO_Internet_title, alert.NO_Internet, 1);

        }
    }

    private void sendDetail() {

        pd.show();
        RequestAccount r = new RequestAccount();
        r.setUsername(ed_username.getText().toString().trim());
        r.setPassword(ed_password.getText().toString().trim());
        r.setMembercode(member_ID);
        r.setMemBday(DateofBirth);
        r.setMemFname(member_Fname);
        r.setMemLname(member_Lname);
        r.setMemMi("");

        if (ed_contact.getText().toString().trim().equals("")) {
            r.setPhone(" ");
        } else
            r.setPhone(ed_contact.getText().toString().trim());

        r.setPhone(ed_contact.getText().toString().trim());
        r.setEmail(ed_email.getText().toString().trim());
        r.setRegisterDevice(phoneInformations.getIMEI(context));
        r.setMemSex(setGender(member_Gender));

        Gson gson = new Gson();
        Log.d("JSON", gson.toJson(r));
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.requestUser(r)

                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        try {
                            alert.showMe(context, "Hold On", alert.ALREADY_message, 1);

                            Log.e("RETURN", e.toString());
                            pd.dismiss();
                        } catch (Exception error) {
                            alert.showMe(context, "Hold On", ErrorMessage.setErrorMessage(e.getMessage()), 1);
                            pd.dismiss();
                            Log.e("Rx_ERROR", error.getMessage());
                        }


                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.d("RETURN", responseBody.toString());
                        alert.showRegisterDone(context, alert.CONGRATULATIONS_title, alert.CONGRATULATIONS_message, 2);
                        pd.dismiss();
                    }
                });

    }


    private void verifyMember(String memberCode, String dateofBirth) {

        Log.e(TAG, memberCode);
        Log.e(TAG, dateofBirth);
        pd.show();
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.verifyMember(memberCode, dateofBirth)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<VerifyMemberData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        try {

                            alert.showMe(context, alert.HOLD_ON_title, ErrorMessage.setErrorMessage(e.getMessage()), 1);
                            Log.d("RETURN", e.getMessage());
                            pd.dismiss();
                        } catch (Exception error) {
                            pd.dismiss();
                            alert.showMe(context, alert.HOLD_ON_title, ErrorMessage.setErrorMessage(e.getMessage()), 1);

                            Log.e("Rx_ERROR", error.getMessage());
                        }

                    }

                    @Override
                    public void onNext(VerifyMemberData verifyMember) {
                        Timber.d(verifyMember.toString());

                        if (!verifyMember.getResponseCode().toString().equals("200")) {

                            alert.showMe(context, alert.HOLD_ON_title, verifyMember.getResponseDesc(), 1);

                        } else {

                            ed_memberName.setText(verifyMember.getMemberInfo().getMEM_NAME());
                            member_Fname = verifyMember.getMemberInfo().getMEM_FNAME();
                            member_Lname = verifyMember.getMemberInfo().getMEM_LNAME();
                            member_Gender = Integer.parseInt(verifyMember.getMemberInfo().getMEM_SEX());
                            widgetEnabler(true);

                        }


                        pd.dismiss();

                    }
                });


    }


    public void widgetEnabler(boolean enabler) {

        ed_username.setEnabled(enabler);
        ed_email.setEnabled(enabler);
        ed_password.setEnabled(enabler);
        ed_repassword.setEnabled(enabler);
        ed_contact.setEnabled(enabler);
        btn_regAccount.setEnabled(enabler);


        if (enabler) {
            btn_verify.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
            btn_regAccount.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
            ed_idNumber.setEnabled(false);
            ed_birth.setEnabled(false);
            btn_verify.setEnabled(false);

            ed_birth.setTextColor(ContextCompat.getColor(context, R.color.grey));
            ed_memberName.setTextColor(ContextCompat.getColor(context, black));
        } else {
            btn_regAccount.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
            btn_verify.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
            ed_idNumber.setEnabled(true);
            ed_birth.setEnabled(true);
            btn_verify.setEnabled(true);
        }

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);

    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.d(TAG, event.getMessage());
        if (event.getMessage().equals(showTermsNCondition.TO_REGISTRATION)) {
            if (permission.checkPermissionPhone(context)) {
                if (NetworkTest.isOnline(context)) {
                    sendDetail();
                } else
                    alert.showMe(context, alert.NO_Internet_title, alert.NO_Internet, 1);

            }

        } else if (event.getMessage().equals(alert.CONGRATULATIONS_title)) {
            finish();
        } else DateofBirth = event.getMessage();


    }

    public static class MessageEvent {


        private String message;

        public MessageEvent(String s) {
            this.message = s;
        }

        public String getMessage() {
            return message;
        }


    }


    public String setGender(int gender) {
        if (gender == 0)
            return "Male";
        else return "Female";

    }

}

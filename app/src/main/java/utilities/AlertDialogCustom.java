package utilities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;

import com.medicard.member.R;
import com.medicard.member.RegistrationActivity;

import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import de.hdodenhof.circleimageview.CircleImageView;
import model.RequestResult;

/**
 * Created by mpx-pawpaw on 10/21/16.
 */

public class AlertDialogCustom {

    public String A_VALID_PIN = "A valid PIN is required to Request for LOA. Please register PIN through Account Settings";
    public String EMAIL_message = "Invalid email Address. Please try again.";
    public String success_pin_message = "You have successfully registered a PIN.";
    public String PASSWORD_CRED_message = "Password must have at least one (1) capitalized letter, one (1) number and a minimum of eight (8) characters.";
    public String PASSWORD_MATCH_message = "Password did not match. Please try again.";
    public String PASSWORD_RETYPEPASS_MATCH_message = "New Password and Re-Type Password did not match. Please try again.";
    public String PIN_RETYPEPIN_MATCH_message = "PIN and Re-Type PIN did not match. Please try again.";
    public String PIN_RETYPE_LENGTH_message = "PIN must be four (4) digits long";

    public String CONGRATULATIONS_title = "Success!";
    public String CONGRATULATIONS_message = "You have successfully created an account. Please login to begin.";

    public String Saved_Screenshot = "LOA Request has been saved to MediCard folder in your photo gallery.";

    public String ALREADY_message = "Account is already existing.";
    public String MemberAccountDidNotMatch = "Date of Birth does not match with MediCard ID number.";
    public String HOLD_ON_title = "Hold On";

    public String close_loa = "Are you sure you to cancel LOA request?";
    public String UsernameMinimumCharacter = "Username must contain a minimum of 3 characters.";
    public String InvalidUsername = "Username invalid. It must not contain blank spaces.";
    public String spec_not_good = "Please proceed to Coordinator";

    public String successfullyAddedDep = "You have successfully added a dependent.";
    public String INVALID_PASS_USER = "You have entered an incorrect password. After 3 unsuccessful attempts your account will be locked.";
    public String NO_Internet = "Please check your internet connection.";
    public String NO_Internet_title = "Hold On";
    public String unknown = "Alert";

    public String data_cancelled = "Request successfully cancelled.";
    public String didnt_match_old_new_pin = "Incorrect Old PIN.";
    public String success = "Success";
    public String success_msg = "You have successfully uploaded your photo.";
    public String errorUploadPhoto = "Failed to upload photo. Please try again.";
    public String delete_msg = "Image successfully deleted.";
    public String addDependenceAlreadyAdded = "Something went wrong. Account has already been added.";
    public String addDependenceNotDependent = "Something went wrong. Member ID is not your dependent.";
    public String successChangePass = "You have successfully changed your password.";
    public String succesCheckEmailPassword = "Password has been sent to your email address.";
    public String errorUnableToChangePass = "Something went wrong. Unable to change your password. Please try again.";
    public String errorUnableToRequestPass = "Something went wrong. Unable to request a new password. Please try again.";
    public String errorUsernameOrPass = "It seems like you've got the wrong username/password. Please try again.";
    public String errorRequestUsernameOrPass = "Something went wrong. Incorrect Email Address or MemberCode.";
    public String errorAccountLocked = "Your account is locked. Please tap on the \"Forgot your Password\" link to reset your password.";
    public String errorNeedToChangePass = "You need to change your password.";
    public String errorNoUsername = "No User Account for entered username.";
    public String errorEmptyFields = "Please fill up the required fields.";
    public String warninginputMemberId = "Please input Dependent Member ID.";
    public String maternity_not_available = "Maternity Consultation is not available for male.";
    public String no_city_selected = "Please Select City";
    public String success_update_pin = "You have successfully changed your PIN";
    public String no_condition = "Please enter Problem/Condition.";
    public String no_doctor = "Please enter Doctor.";
    public String pick_start_Date = "Pick Start date first.";
    public String end_must_greater = "End date should be greater than Start Date.";
    public String start_must_lesser = "Start Date should be lesser than End Date.";
    public String data_not_avilable = "List is not available";
    public  String successfully_updated = "Setting is successfully updated.";
    TextView tv_message, tv_title;
    CircleImageView ci_error_image;
    Button btn_accept, btn_cancel;
    onClickDialogListener callback;


    public void showMe(Context context, String title, String message, int errorImage) {

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
            }
        });

        setDetails(context, message, title, errorImage, btn_accept);


        dialog.show();


        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.70);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = width;
        dialog.getWindow().setAttributes(lp);

    }

    public void showMe(Context context, String title, String message, int errorImage, final onClickDialogListener callback) {

        this.callback = callback;

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertshow2button);
        dialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        ci_error_image = (CircleImageView) dialog.findViewById(R.id.ci_error_image);
        tv_message = (TextView) dialog.findViewById(R.id.tv_message);
        tv_title = (TextView) dialog.findViewById(R.id.tv_title);
        btn_accept = (Button) dialog.findViewById(R.id.btn_accept);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callback.onOkPress();
            }
        });
        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });
        btn_cancel.setBackgroundColor(ContextCompat.getColor(context, R.color.BLACK));
        setDetails(context, message, title, errorImage, btn_accept);


        dialog.show();


        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.70);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = width;
        dialog.getWindow().setAttributes(lp);

    }


    public void showRegisterDone(final Context context, String title, String message, int errorImage) {

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
                EventBus.getDefault().post(new RegistrationActivity.MessageEvent(CONGRATULATIONS_title));
            }
        });


        setDetails(context, message, title, errorImage, btn_accept);


        dialog.show();


        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.70);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = width;
        dialog.getWindow().setAttributes(lp);

    }


    private void setDetails(Context context, String message, String title, int errorImage, Button button) {

        tv_message.setText(message);
        tv_title.setText(title);


        switch (errorImage) {

            case 1:
//error
                ci_error_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.warning));
                button.setBackgroundColor(ContextCompat.getColor(context, R.color.BLACK));
                break;


            case 2:
//congrats
                ci_error_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.success));
                button.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimary));

                break;


            case 3:
//Log out
                ci_error_image.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.logout));
                button.setBackgroundColor(ContextCompat.getColor(context, R.color.BLACK));

                break;


        }
    }


    public void showMeValidateReq(final RequestResult requestResult, final onClickDialogListener callbackDialog, Context context) {

        final onClickDialogListener callback;
        callback = callbackDialog;
        Button btn_accept;
        Button btn_cancel;


        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertshow2buttonconfirmreq);
        dialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        btn_accept = (Button) dialog.findViewById(R.id.btn_accept);
        btn_cancel = (Button) dialog.findViewById(R.id.btn_cancel);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                callbackDialog.loaApprovedListener(requestResult);
            }
        });

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
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

    public interface onClickDialogListener {

        void onOkPress();

        void onRequestDupliate();

        void loaApprovedListener(RequestResult requestResult);
    }

}

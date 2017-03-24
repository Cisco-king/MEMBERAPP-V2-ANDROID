package fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.medicard.member.R;
import android.widget.Button;
import android.widget.EditText;

import model.RequestNewPassword;
import model.ReturnChangePassword;
import model.ReturnRequestPassword;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.NetworkTest;
import utilities.PhoneInformations;
import utilities.SnackBar;

public class fragment_request_new_password extends Fragment {


    EditText et_emailAdd, et_member_code;
    Button btn_sendNewPassword;
    SnackBar snackBar;
    CoordinatorLayout coords;
    ProgressDialog pd;
    PhoneInformations phoneInformation;
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom() ;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_request_change_password, container, false);

        snackBar = new SnackBar();
        phoneInformation = new PhoneInformations();

        et_emailAdd = (EditText) view.findViewById(R.id.et_emailAdd);
        et_member_code = (EditText) view.findViewById(R.id.et_member_code);
        btn_sendNewPassword = (Button) view.findViewById(R.id.btn_sendNewPassword);
        coords = (CoordinatorLayout) view.findViewById(R.id.coords);


        pd = new ProgressDialog(getContext(), R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Requesting...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);



        btn_sendNewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (et_emailAdd.getText().toString().equals("") || et_member_code.getText().toString().equals("")){

                    snackBar.SnackBar(coords, "Fill up the required fields");

                }else{

                    pd.show();
                    if (NetworkTest.isOnline(getContext())){
                        requestChangePassword(et_emailAdd.getText().toString(), et_member_code.getText().toString(), phoneInformation.getIMEI(getContext()));
                    }else
                        alertDialogCustom.showMe(getContext(), alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);



                }


            }
        });




        return view;


    }


    private void requestChangePassword(String emailAddress, String memberCode, String deviceId) {

        RequestNewPassword requestNewPassword = new RequestNewPassword();
        requestNewPassword.setDeviceId(deviceId);
        requestNewPassword.setEmail(emailAddress);
        requestNewPassword.setMemberCode(memberCode);

        final AppInterface appInterface ;
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
                            pd.dismiss();
                            alertDialogCustom.showMe(getActivity() , alertDialogCustom.HOLD_ON_title , ErrorMessage.setErrorMessage(e.getMessage()) , 1);
                        }catch (Exception error){
                            pd.dismiss();
                            alertDialogCustom.showMe(getActivity() , alertDialogCustom.HOLD_ON_title , ErrorMessage.setErrorMessage(e.getMessage()), 1);

                            Log.e("Rx_ERROR" , error.getMessage());
                        }



                    }

                    @Override
                    public void onNext(ReturnRequestPassword returnRequestPassword) {

                        pd.dismiss();

                        if (returnRequestPassword.getResponseCode().equals("200")){


                            AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
                            alertDialogCustom.showMe(getContext(), alertDialogCustom.success, alertDialogCustom.successChangePass, 2);

                            et_emailAdd.setText("");
                            et_member_code.setText("");

                        }else if(returnRequestPassword.getResponseCode().equals("200")){

                            AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
                            alertDialogCustom.showMe(getContext(), alertDialogCustom.success, alertDialogCustom.errorRequestUsernameOrPass, 1);

                        }else{

                            AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
                            alertDialogCustom.showMe(getContext(), alertDialogCustom.success, alertDialogCustom.errorUnableToRequestPass, 1);

                        }


                    }
                });



    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}

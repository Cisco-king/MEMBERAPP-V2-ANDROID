package utilities;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mpx-pawpaw on 1/5/17.
 */

public class ResultSetters {

    public static final String Approved = "Approved";
    public static final String REQUEST_APPROVED = "REQUEST SUBMITTED";
    public static final String REQUEST_DISAPPROVED = "REQUEST DISAPPROVED";
    public static final String REQUEST_APPROVAL = "PENDING APPROVAL";
    public static final String WITHPROVIDER = "withProvider";

    public static String nameSetter(String name, Context context) {

        if (name.equals(Constant.NOT_FOUND)) {
            return SharedPref.getStringValue(SharedPref.USER, SharedPref.DOCTOR_CODE, context);
        } else {
            return name;
        }

    }

    public static String descSetter(String desc) {

        if (desc.equals(Constant.NOT_FOUND) || desc.equals(Constant.NOT_SET)) {
            return "Room not specified";
        } else {
            return desc;
        }


    }


    public static String specSetter(String desc) {

        if (desc.equals(Constant.NOT_FOUND) || desc.equals(Constant.NOT_SET)) {
            return "Specialization not specified";
        } else {
            return desc;
        }


    }



    public static String schedSetter(String desc) {

        if (desc.equals(Constant.NOT_FOUND) || desc.equals(Constant.NOT_SET)) {
            return "Schedule not specified";
        } else {
            return desc;
        }


    }



    public static boolean isDoctorAvailable(String doctor) {
        if (doctor.equals(Constant.NOT_FOUND)) {
            return false;
        } else {
            return true;
        }


    }

    public static String titleSetter(String request) {

        if (request.equals(Approved))
            return REQUEST_APPROVED;
        else
            return REQUEST_DISAPPROVED;


    }

    public static void setDoctorWithProvider(String withProvider, TextView textView) {

        if (withProvider.equals(WITHPROVIDER))
            textView.setVisibility(View.GONE);
        else
            textView.setVisibility(View.VISIBLE);

    }

}

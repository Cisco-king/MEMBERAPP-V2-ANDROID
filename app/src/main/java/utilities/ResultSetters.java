package utilities;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

/**
 * Created by mpx-pawpaw on 1/5/17.
 */

public class ResultSetters {

    public static final String Approved = "APPROVED";
    public static final String Pending = "PENDING";
    public static final String Cancelled = "CANCELLED";
    public static final String REQUEST_APPROVED = "REQUEST SUBMITTED";
    public static final String REQUEST_CONFIRMED = "REQUEST APPROVED";

    public static final String REQUEST_TEST_APPROVED = "REQUEST APPROVED";
    public static final String REQUEST_DISAPPROVED = "REQUEST DISAPPROVED";
    public static final String REQUEST_APPROVAL = "PENDING APPROVAL";
    public static final String REQUEST_CANCELLED = "REQUEST CANCELLED";
    public static final String WITHPROVIDER = "withProvider";
    public static final String ACTIVESTATUS = "ACTIVE";
    public static final String ONHOLD = "ON HOLD";
    public static final String FORREACTIVATIONMEMBER = "FOR REACTIVATION";
    public static final String VERIFYPAYMENTWRMDStatus = "VERIFY PAYMENT WITH RMD";
    public static final String VERIFYRENEWALStatus = "VERIFY RENEWAL FROM MARKETING / SALES";
    public static final String VERIFYMEMBERSHIPStatus = "VERIFY MEMBERSHIP";

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

        String newString = request.trim();
        if (newString.equalsIgnoreCase(ACTIVESTATUS)) {
            return REQUEST_APPROVED;
        } else if (newString.equalsIgnoreCase(Pending)) {
            return REQUEST_APPROVAL;
        } else if (newString.equalsIgnoreCase(Approved)) {
            return REQUEST_TEST_APPROVED;
        } else if (newString.equalsIgnoreCase(ONHOLD)) {
            return REQUEST_APPROVAL;
        } else if (newString.equalsIgnoreCase(FORREACTIVATIONMEMBER)) {
            return REQUEST_APPROVAL;
        } else if (newString.equalsIgnoreCase(VERIFYPAYMENTWRMDStatus)) {
            return REQUEST_APPROVAL;
        } else if (newString.equalsIgnoreCase(VERIFYRENEWALStatus)) {
            return REQUEST_APPROVAL;
        } else if (newString.equalsIgnoreCase(VERIFYMEMBERSHIPStatus)) {
            return REQUEST_APPROVAL;
        } else if(newString.equalsIgnoreCase(Cancelled)){
            return REQUEST_CANCELLED;
        }else {
            return REQUEST_DISAPPROVED;
        }



    }

    public static void setDoctorWithProvider(String withProvider, TextView textView) {

/*
        if (withProvider.equals(WITHPROVIDER))
            textView.setVisibility(View.GONE);
        else
            textView.setVisibility(View.VISIBLE);
*/

    }

}

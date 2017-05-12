package utilities;

import java.util.Arrays;

import constants.MemberStatus;
import timber.log.Timber;

/**
 * Created by mpx-pawpaw on 11/18/16.
 */

public class StatusSetter {

    String[] stringBender = {
            "ACTIVE", "DISAPPROVED", "FOR ENCODING", "MEDICAL EVALUATION", "ON HOLD", "FOR APPROVAL",
            "RESIGNED", "CANCELLED", "PENDING (E-MEDICARD)", "LAPSE (NON RENEW)", "FOR REACTIVATION", "VERIFY PAYMENT WITH RMD",
            "VERIFY RENEWAL FROM MARKETING / SALES", "VERIFY MEMBERSHIP"
    };

    String[] stringResult = {
            "Active", "Disapproved", "Pending", "Pending", "Pending", "Pending", "Inactive", "Inactive",
            "Pending", "Inactive", "Inactive", "Pending", "Pending", "Pending"};


    public String setStatus(String status) {
        String result = "";

        int resultIndex = Arrays.asList(stringBender).indexOf(status);
        Timber.d("using find Arrays.aslist status %s in the container %s", status, resultIndex);
//        result = stringResult[resultIndex];

        for (int x = 0; x < stringBender.length; x++) {
            if (stringBender[x].equals(status)) {
                Timber.d("using for if statement result status %s index %s", status, x);
                result = stringResult[x];
                break;
            }
        }

        return result;
    }

    public static String setRemarks(String status) {

        String setRemark = "";

        if (status.equalsIgnoreCase(MemberStatus.DISAPPROVED)) { // DISAPPROVED
            setRemark = "Your request is denied. Please call 841-8080 for details.";
        } else if (status.equalsIgnoreCase(MemberStatus.FOR_ENCODING)) { // FOR ENCODING
            setRemark = "Your request is currently on-process.";
        } else if (status.equalsIgnoreCase(MemberStatus.MEDICAL_EVALUATION)) { // MEDICAL EVALUATION
            setRemark = "Your request is subject to medical evaluation.";
        } else if (status.equalsIgnoreCase(MemberStatus.ON_HOLD)) { // ON HOLD
//            setRemark = "Your request is currently put on-hold.\n" +
//                    "Please contact your HRD/Medicard account offer.";
            setRemark = "Your Account is on hold. Please contact your MediCard Account Officer.";
        } else if (status.equalsIgnoreCase(MemberStatus.FOR_APPROVAL)) { // FOR APPROVAL
            setRemark = "Your request is subject to approval.";
        } else if (status.equalsIgnoreCase(MemberStatus.RESIGNED)) { // RESIGNED
            setRemark = "Your request is denied. Account is no longer active.";
        } else if (status.equalsIgnoreCase(MemberStatus.CANCELLED)) { // CANCELLED
            setRemark = "Your Request is denied. Account is no longer active.";
        } else if (status.equalsIgnoreCase(MemberStatus.LAPSE_NON_RENEW)) { // LAPSE (NON RENEW)
            setRemark = "Your request is denied. Account is no longer active.";
        } else if (status.equalsIgnoreCase(MemberStatus.FOR_REACTIVATION)) { // FOR REACTIVATION
//            setRemark = "Your request is denied. Account is subject to reactivation.";
            setRemark = "Your account is for Reactivation. Please contact your MediCard Account Officer.";
        } else if (status.equalsIgnoreCase(MemberStatus.VERIFY_MEMBERSHIP)) { // VERIFY MEMBERSHIP
//            setRemark = "Please contact your HRD/Medicard account officer.";
            setRemark = "Your request cannot be processed at this time. Please call 841-8080";
        } else if (status.equalsIgnoreCase(MemberStatus.VERIFY_PAYMENT_WITH_RMD)) { // VERIFY PAYMENT WITH RMD
//            setRemark = "Please contact your HRD/Medicard account officer.";
            setRemark = "Your request cannot be processed at this time. Please call 841-8080";
        } else if (status.equalsIgnoreCase(MemberStatus.VERIFY_RENEWAL_FROM_MARKETING_SALES)) { // VERIFY RENEWAL FROM MARKETING / SALES
//            setRemark = "Please contact your HRD/Medicard account officer.";
            setRemark = "Your request cannot be processed at this time. Please call 841-8080";
        } else if (status.equalsIgnoreCase(MemberStatus.PENDING_E_MEDICARD)) {
            setRemark = "Your request is currently on-process.";
        }

        return setRemark;
    }


}

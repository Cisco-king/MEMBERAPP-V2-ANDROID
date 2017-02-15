package utilities;

/**
 * Created by mpx-pawpaw on 11/18/16.
 */

public class StatusSetter {
    String[] stringBender =

            {
                    "ACTIVE", "DISAPPROVED", "FOR ENCODING", "MEDICAL EVALUATION", "ON HOLD", "FOR APPROVAL",
                    "RESIGNED", "CANCELLED", "PENDING (E-MEDICARD)", "LAPSE (NON RENEW)", "FOR REACTIVATION", "VERIFY PAYMENT WITH RMD",
                    "VERIFY RENEWAL FROM MARKETING / SALES", "VERIFY MEMBERSHIP"
            };

    String[] stringResult = {

            "Active", "Disapproved", "Pending", "Pending", "Pending", "Pending", "Inactive", "Inactive",
            "Pending", "Inactive", "Inactive", "Pending", "Pending", "Pending"


    };


    public String setStatus(String status) {
        String result = "" ;

        for (int x = 0; x < stringBender.length; x++) {

            if (stringBender[x].equals(status)) {
                result =  stringResult[x];
                break;
            }

        }

        return result ;
    }


}

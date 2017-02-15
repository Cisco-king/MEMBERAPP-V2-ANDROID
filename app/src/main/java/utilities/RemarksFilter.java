package utilities;

import android.util.Log;

/**
 * Created by mpx-pawpaw on 11/16/16.
 */

public class RemarksFilter {


    public String filterRemarks(String rem1, String rem2, String rem3, String rem4, String rem5, String rem6, String rem7) {


        String remarkToSend = " ";
        String filterNull = "";


        try {

            filterNull = "";
            filterNull = rem1;
            if (!filterNull.equals("null"))
                remarkToSend = remarkToSend + rem1 + " letmeout ";


        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        try {

            filterNull = "";
            filterNull = rem2;
            if (!filterNull.equals("null"))
                remarkToSend = remarkToSend + rem2 + " letmeout ";

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        try {

            filterNull = "";
            filterNull = rem3;
            if (!filterNull.equals("null"))
                remarkToSend = remarkToSend + rem3 + " letmeout ";


        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        try {
            filterNull = "";
            filterNull = rem4;
            if (!filterNull.equals("null"))
                remarkToSend = remarkToSend + rem4 + " letmeout ";

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        try {

            filterNull = "";
            filterNull = rem5;
            if (!filterNull.equals("null"))
                remarkToSend = remarkToSend + rem5 + " letmeout ";

        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }

        try {

            filterNull = "";
            filterNull = rem6;
            if (!filterNull.equals("null"))
                remarkToSend = remarkToSend + rem6 + " letmeout ";


        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }


        try {

            filterNull = "";
            filterNull = rem7;
            if (!filterNull.equals("null"))
                remarkToSend = remarkToSend + rem7 + " letmeout ";


        } catch (Exception e) {
            Log.e("ERROR", e.getMessage());
        }



        String regex = "\\s*\\b\nnull\\b\\s*";
        String regex2 = "\\s*\\bletmeout\\b\\s*";
        remarkToSend = remarkToSend.replaceAll(regex, "");
        remarkToSend = remarkToSend.replaceAll(regex2, "\n");

        remarkToSend = remarkToSend.substring(1, remarkToSend.length());

        return remarkToSend;
    }
}

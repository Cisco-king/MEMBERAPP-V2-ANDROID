package utilities;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mpx-pawpaw on 11/10/16.
 */

public class DateConverter {

    public static String validityDatePLusDay(String date_, int dayToADD) {

        String date_s = date_;
        SimpleDateFormat dt = new SimpleDateFormat("MMM dd , yyyy");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat day = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");

        int addedDay = Integer.parseInt(day.format(date)) + dayToADD;
        String dateRaw = (Integer.parseInt(month.format(date))) + " " + addedDay + " , " + year.format(date);


        Log.d("date_parse2", dateRaw);


        return convertDateToMMddyyyy(convertDate(dateRaw));
    }


    public static String convertDateToMMddyyyy(String dateAdmitted) {


        /**
         CURRENT FORMAT
         02 14, 1975
         to
         FEB 14 , 2017
         */


        String date_s = dateAdmitted;
        SimpleDateFormat dt = new SimpleDateFormat("MMM dd , yyyy");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("MM/dd/yyyy");

        return dt1.format(date);
    }

    public static String convertDate(String birthday) {

        /**
         CURRENT FORMAT
         02 14, 1975
         to
         FEB 14 , 2017
         */
        Log.d("date_parse", birthday);

        String date_s = birthday;
        SimpleDateFormat dt = new SimpleDateFormat("MM dd , yyyy");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("MMM dd , yyyy");

        return dt1.format(date);
    }


    public static String convertDatetoMMMddyyy(String birthday) {

        /**
         CURRENT FORMAT
         "2017-02-14 03:43",
         to
         19751213
         */

        String date_s = birthday;
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat date_ = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");


        return convertDate((Integer.parseInt(month.format(date)) + 1) + " " + date_.format(date) + " , " + year.format(date));
    }

}

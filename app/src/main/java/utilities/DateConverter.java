package utilities;

import android.util.Log;
import android.widget.TextView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat date_ = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");


        return convertDate((Integer.parseInt(month.format(date))) + " " + date_.format(date) + " , " + year.format(date));
    }


    public static String convertDateFromYYYYMDD(String s) {
        Log.d("DATE_DATE_toformat", s + "");
        SimpleDateFormat dt = new SimpleDateFormat("yyyy,MM,dd");
        Date date = null;
        try {
            date = dt.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat dt1 = new SimpleDateFormat("MMM dd , yyyy");
        return dt1.format(date);
    }

    public static boolean testDataStartAndEnd(String start, String end) {


        Date startTime = null;
        Date endTime = null;
        SimpleDateFormat dfDate = new SimpleDateFormat("MMM dd , yyyy");

        try {
            startTime = dfDate.parse(start);
            endTime = dfDate.parse(end);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        boolean b = false;
        if (startTime.equals(endTime)) {
            b = true ;
        } else if (startTime.before(endTime)) {
            b = true;
        } else if (startTime.after(endTime)) {
            b = false;
        }

        return b;


    }

    public static int[] getDates(String s) {
        int[] dates = new int[3];
        SimpleDateFormat dt = new SimpleDateFormat("MMM dd , yyyy");

        Date date = null;
        try {
            date = dt.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat date_ = new SimpleDateFormat("d");
        SimpleDateFormat month = new SimpleDateFormat("MM");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");

        dates[0] = (Integer.parseInt(date_.format(date)));
        dates[1] = (Integer.parseInt(month.format(date)));
        dates[2] = (Integer.parseInt(year.format(date)));


        return dates;
    }


    private static String getText(TextView v) {
        return v.getText().toString().trim();
    }

    public static String converttoyyyymmddEnd(String tv_req_date_start) {

        String stringDate = "";
        if (tv_req_date_start.equals("")) {
            stringDate = "";
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("MMM dd , yyyy");
            Date date = null;
            try {
                date = dt.parse(tv_req_date_start);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stringDate = dt1.format(addDays(date , 1));

        }
        return stringDate;

    }
    public static Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }

    public static String converttoyyyymmdd(String tv_req_date_start) {

        String stringDate = "";
        if (tv_req_date_start.equals("")) {
            stringDate = "";
        } else {
            SimpleDateFormat dt = new SimpleDateFormat("MMM dd , yyyy");
            Date date = null;
            try {
                date = dt.parse(tv_req_date_start);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            stringDate = dt1.format(date);

        }
        return stringDate;

    }

    public static String currentDate() {
        Calendar c = Calendar.getInstance();
        System.out.println("Current time => " + c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("MMM dd , yyyy");
        String formattedDate = df.format(c.getTime());

        return formattedDate;
    }

    public static boolean testExpiration(String approvalDate) {

        boolean data = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        String current = convertDateToMMddyyyy(currentDate());

        Date current_date = null;
        Date approval_date = null;
        try {
            current_date = dateFormat.parse(current);
            approval_date = dateFormat.parse(validityDatePLusDay(approvalDate, 3));
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Log.d("CURRENT DATE", current_date + "");
        Log.d("APPROVAL DATE", approval_date + "");

        if (current_date.after(approval_date))
            return false;
        else if (current_date.before(approval_date))
            return true;
        else if (current_date.equals(approval_date))
            return true;

        return true;
    }


    public static String convertDate(String approvalDate, SimpleDateFormat currentFormat) {


        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd , yyyy");
        String dateToSend = "";
        try {
            Date currentDate = currentFormat.parse(approvalDate);
            dateToSend = dateFormat.format(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateToSend;
    }
}

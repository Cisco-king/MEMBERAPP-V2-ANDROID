package utilities;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mpx-pawpaw on 11/10/16.
 */

public class DateConverter {


    public String convertDate (String birthday){

        /**
         CURRENT FORMAT
         Dec 13, 1975
         to
         19751213
         */


        String date_s = birthday;
        SimpleDateFormat dt = new SimpleDateFormat("MMM dd, yyyy");
        Date date = null;
        try {
            date = dt.parse(date_s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat dt1 = new SimpleDateFormat("yyyyMMdd");
        System.out.println(dt1.format(date));

        Log.d("CURRENT" , dt1.format(date));
        return  dt1.format(date);
    }
}

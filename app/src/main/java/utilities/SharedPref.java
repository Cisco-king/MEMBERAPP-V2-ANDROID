package utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by window on 9/29/2016.
 */

public class SharedPref {
    public static String masterPASSWORD = "masterPASSWORD";
    public static String masterUSERNAME = "masterUSERNAME" ;

    public static String PIN = "PIN" ;
    public static String USER = "USER";
    public static String MEMBERCODE = "MEMBERCODE";
    public static String NAME = "NAME";

    public static String FORCE_CHANGE_PASSWORD = "FORCE_CHANGE_PASSWORD";
    public static String USERNAME = "USERNAME";

    public static String DISCLAIMER = "DISCLAIMER";

    public static String YEAR = "YEAR";
    public static String DAY = "DAY";
    public static String MONTH = "MONTH";

    public static String DOCTOR_NAME = "DOCTOR_NAME" ;
    public static String DOCTOR_CODE = "DOCTOR_CODE" ;
    public static String DOCTOR_DESC = "DOCTOR_DESC" ;
    public static String DOCTOR_U = "DOCTOR_U" ;
    public static String DOCTOR_ROOM = "DOCTOR_ROOM" ;

    public static String HOSPITAL_NAME = "HOSPITAL_NAME";
    public static String HOSPITAL_CODE = "HOSPITAL_CODE";
    public static String HOSPITAL_ADD = "HOSPITAL_ADD";
    public static String HOSPITAL_CONTACT = "HOSPITAL_CONTACT";
    public static String HOSPITAL_CONTACT_PERSON = "HOSPITAL_CONTACT_PERSON";
    public static String HOSPITAL_U = "HOSPITAL_U";

    public static String DESTINATION = "DESTINATION";

    public static String FIRST_TIME = "FIRST_TIME" ;
    public static String VAL_DATE = "VAL_DATE" ;
    public static String EFF_DATE = "EFF_DATE";
        public static String PIN_IS_AVAILABLE = "PIN_IS_AVAILABLE" ;
    public static String PROVINCE_CODE = "PROVINCE_CODE" ;
    public static String AGE = "AGE" ;
    public static String GENDER = "GENDER" ;



    public void setBoolValue(String Key, boolean value, Context context) {

        SharedPreferences prefs = context.getSharedPreferences(Key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("LOG", value);
        editor.commit();


    }


    public static boolean getBoolValue(String Key, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Key, Context.MODE_PRIVATE);
        return preferences.getBoolean("LOG", false);
    }


    public static void setStringValue(String Key, String specID, String value, Context context) {

        SharedPreferences prefs = context.getSharedPreferences(Key, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(specID, value);
        editor.commit();


    }

    public static String getStringValue(String Key, String specID, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(Key, Context.MODE_PRIVATE);
        return preferences.getString(specID, "null");
    }

}

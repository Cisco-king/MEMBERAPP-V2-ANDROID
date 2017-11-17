package utilities;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by window on 9/29/2016.
 */

public class SharedPref {

    // application preference
    public static String USER = "USER";

    // value
    public static String masterPASSWORD = "masterPASSWORD";
    public static String masterUSERNAME = "masterUSERNAME";

    public static String PIN = "PIN";
    public static String MEMBERCODE = "MEMBERCODE";
    public static String NAME = "NAME";

    public static String FORCE_CHANGE_PASSWORD = "FORCE_CHANGE_PASSWORD";
    public static String USERNAME = "USERNAME";

    public static String DISCLAIMER = "DISCLAIMER";

    public static String YEAR = "YEAR";
    public static String DAY = "DAY";
    public static String MONTH = "MONTH";

    public static String DOCTOR_NAME = "DOCTOR_NAME";
    public static String DOCTOR_CODE = "DOCTOR_CODE";
    public static String DOCTOR_DESC = "DOCTOR_DESC";
    public static String DOCTOR_SCHED = "DOCTOR_SCHED";
    public static String DOCTOR_U = "DOCTOR_U";
    public static String DOCTOR_ROOM = "DOCTOR_ROOM";

    public static String HOSPITAL_NAME = "HOSPITAL_NAME";
    public static String HOSPITAL_CODE = "HOSPITAL_CODE";
    public static String HOSPITAL_ADD = "HOSPITAL_ADD";
    public static String HOSPITAL_CONTACT = "HOSPITAL_CONTACT";
    public static String HOSPITAL_CONTACT_PERSON = "HOSPITAL_CONTACT_PERSON";
    public static String HOSPITAL_U = "HOSPITAL_U";

    public static String DESTINATION = "DESTINATION";

    public static String FIRST_TIME = "FIRST_TIME";
    public static String VAL_DATE = "VAL_DATE";
    public static String EFF_DATE = "EFF_DATE";
    public static String PIN_IS_AVAILABLE = "PIN_IS_AVAILABLE";
    public static String PROVINCE_CODE = "PROVINCE_CODE";
    public static String AGE = "AGE";
    public static String GENDER = "GENDER";

    // the value of this key is base on the response of {@link services.AppInterface#logInUser} maternity
    public static final String KEY_HAS_MATERNITY = "hasMaternity";

    public static final String KEY_REASON_FOR_CONSULT = "reasonForConsult";
    public static final String KEY_DOCTOR = "doctor";
    public static final String KEY_HOSPITAL = "hospital";
    public static final String KEY_PROCEDURE_DIAGNOSIS = "procedureDiagnosis";

    public static final String KEY_DISPLAY_ALL_PROCEDURE = "displayAllProcedure";

    public static final String KEY_HOSPITAL_FULL_ADDRESS = "fullAddress";


    public static String LAST_POSITION = "LAST_POSITION";

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

    public static void setAppPreference(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String getPreferenceByKey(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(USER, Context.MODE_PRIVATE);
        return preferences.getString(key, "null");
    }

    public static void setBoolValue(Context context, String key, boolean value) {
        SharedPreferences prefs = context.getSharedPreferences(USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public static boolean getBooleanValue(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(USER, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }


}

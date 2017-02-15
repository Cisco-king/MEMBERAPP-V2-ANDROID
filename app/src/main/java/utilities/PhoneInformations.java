package utilities;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Log;

/**
 * Created by mpx-pawpaw on 10/25/16.
 */

public class PhoneInformations {


    /**
     * IMEI means International Mobile Equipment Identity.
     * an IMEI is the unique serial number of every GSM mobile cell phone.
     * The IMEI number is used by networks to identify valid phones and
     * block stolen or blacklisted phones from accessing the network.
     */
    public String getIMEI(Context context) {

        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);

        Log.d("IMEI", telephonyManager.getDeviceId());

        return telephonyManager.getDeviceId();
    }

    /**
     * @param context GET CARRIER
     *                CLIENT HAS SOME PROMO DEPENDS ON CARRIER
     */


    public String getCarrier(Context context) {

        TelephonyManager telephonyManager = ((TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE));
        String simOperatorName = telephonyManager.getSimOperatorName();
        Log.d("Sim Operator", simOperatorName);

        return simOperatorName;
    }

}

package utilities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by mpx-pawpaw on 4/5/17.
 */

public class AccessGooglePlay {


    public static void  openAppInGooglePlay(Context context) {
        final String appPackageName = context.getPackageName();
        try {
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch (android.content.ActivityNotFoundException e) { // if there is no Google Play on device
            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }


}

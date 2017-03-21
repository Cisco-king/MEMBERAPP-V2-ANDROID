package utilities;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import com.medicard.member.R;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by mpx-pawpaw on 1/6/17.
 */

public class SetBlockedUser {



    public static String setTitle(String status) {
        String message = "";

        if (status.equals("DISAPPROVED")) {
            message = "Please call 841-8080 for details";
        } else if (status.equals("RESIGNED")) {
            message = "Account is no longer active";
        } else if (status.equals("CANCELLED")) {
            message = "Account is no longer active";
        } else if (status.equals("LAPSE (NON RENEW)")) {
            message = "Account is no longer active";
        } else if (status.equals("FOR REACTIVATION")) {
            message = "Account is subject to reactivation";
        }

        return message;
    }


}

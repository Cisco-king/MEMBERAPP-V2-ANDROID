package utilities;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;

/**
 * Created by window on 9/29/2016.
 */

public class SnackBar {

    public String INPUTCREDS = "Please input Username/Password" ;
    public String INCOMPLETE = "Please complete the details." ;
    public String NOTSAMEPASS = "Password is not the same" ;


    public void SnackBar(CoordinatorLayout coordinatorLayout , String message){

        Snackbar snackbar = Snackbar
                    .make(coordinatorLayout, message, Snackbar.LENGTH_LONG);
        snackbar.show();

    }
}

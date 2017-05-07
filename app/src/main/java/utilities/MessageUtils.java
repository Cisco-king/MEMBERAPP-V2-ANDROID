package utilities;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;

import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public class MessageUtils {

    /**
     *
     * @param view
     * The {$CoordinatorLayout} View
     * @param message
     * The Message to show
     */
    public static final void snackbarNotification(View view, String message) {
        if (view instanceof CoordinatorLayout) {
            Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
        } else {
            Timber.d("View must be a CoordinatorLayout to show snackbar");
        }
    }

}

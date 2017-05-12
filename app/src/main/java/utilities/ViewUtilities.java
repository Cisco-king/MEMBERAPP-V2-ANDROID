package utilities;

import android.view.View;
import android.widget.EditText;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public class ViewUtilities {

    /**
     *
     * @param view
     * The View to be hide
     */
    public static final void hideView(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        }
    }

    public static void showView(View view) {
        if (view.getVisibility() == View.INVISIBLE || view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }
    }

    /**
     *
     * @param view
     * The View must instance of {$EditText}
     * @return
     * The view String value
     */
    public static final String getEditValue(View view) {
        if (view instanceof EditText) {
            return ((EditText) view).getText().toString();
        }

        return null;
    }

}

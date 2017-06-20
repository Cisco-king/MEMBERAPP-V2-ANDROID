package utilities;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.EditText;

import com.medicard.member.R;

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
        if (view.getVisibility() == View.VISIBLE || view.getVisibility() == View.INVISIBLE) {
            view.setVisibility(View.GONE);
        }
    }

    public static final void hideToInvisibleView(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.INVISIBLE);
        }
    }

    public static void showView(View view) {
        if (view.getVisibility() == View.INVISIBLE || view.getVisibility() == View.GONE) {
            view.setVisibility(View.VISIBLE);
        }
    }

    public static void enableDisableView(Context context, View view, boolean isEnable) {
        if (isEnable) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
            view.setEnabled(true);
            view.setClickable(true);
        } else {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.grey));
            view.setEnabled(false);
            view.setClickable(false);
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

package utilities;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.medicard.member.R;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class DialogUtils {

    public static void showDialog(Context context, @StringRes int title, @StringRes int content, final OnDiaglogClickListener listener) {

        new MaterialDialog.Builder(context)
                .autoDismiss(false)
                .title(title)
                .content(content)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel)
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        listener.onOk();
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        listener.onCancel();
                    }
                })
                .show();
    }

    public interface OnDiaglogClickListener {

        void onOk();

        void onCancel();
    }

}

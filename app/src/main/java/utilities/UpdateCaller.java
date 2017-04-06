package utilities;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.medicard.member.R;


/**
 * Created by mpx-pawpaw on 4/5/17.
 */

public class UpdateCaller {


    public static void showUpdateCall(Context context, String message, final boolean isRequired, final DialogUpdateInterface callback) {
        TextView tv_message;
        Button btn_accept;

        final Dialog dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.alertshow);
        dialog.getWindow().setWindowAnimations(R.style.CustomDialogAnimation);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setCancelable(false);

        tv_message = (TextView) dialog.findViewById(R.id.tv_message);

        tv_message.setText(message);
        btn_accept = (Button) dialog.findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                if (isRequired)
                    callback.updateRequired();
                else
                    callback.updateNotRequired();

            }
        });


        dialog.show();


        int width = (int) (context.getResources().getDisplayMetrics().widthPixels * 0.70);

        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(dialog.getWindow().getAttributes());
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        lp.width = width;
        dialog.getWindow().setAttributes(lp);


    }


    public interface DialogUpdateInterface {


        void updateRequired();

        void updateNotRequired();
    }

}

package utilities;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import com.medicard.member.R;
import com.medicard.member.RegistrationActivity;
import android.view.View;
import android.widget.Button;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by mpx-pawpaw on 10/22/16.
 */

public class ShowTermsNCondition extends Application{
    final public   String TO_REGISTRATION = "REGISTER";

    public void showTerms(Context context) {

        Button btn_accept, btn_decline;

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.terms_condition);
        btn_accept = (Button) dialog.findViewById(R.id.btn_accept);
        btn_decline = (Button) dialog.findViewById(R.id.btn_decline);


        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                EventBus.getDefault().post(new RegistrationActivity.MessageEvent(TO_REGISTRATION));
            }
        });


        btn_decline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();

            }
        });


        dialog.setCancelable(false);
        dialog.show();


    }
}

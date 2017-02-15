package utilities;


import android.content.Context;
import android.content.DialogInterface;

import android.medicard.com.medicard.MemberAccountActivity;
import android.support.v7.app.AlertDialog;
import org.greenrobot.eventbus.EventBus;

/**
 * Created by mpx-pawpaw on 10/24/16.
 */

public class ShowImagePicker {


    public ShowImagePicker() {

    }


    public String GALLERY = "Gallery" ;
    public String CAMERA = "Camera" ;


    public void showMe(Context contex) {

        final CharSequence[] items = {CAMERA, GALLERY};

        AlertDialog.Builder builder = new AlertDialog.Builder(contex);
        builder.setTitle("Choose Option:");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                EventBus.getDefault().post(new MemberAccountActivity.MessageEvent(String.valueOf(items[item])));
            }
        }).show();
    }




}

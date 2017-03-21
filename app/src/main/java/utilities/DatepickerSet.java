package utilities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import com.medicard.member.RegistrationActivity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by window on 9/29/2016.
 */

public class DatepickerSet {


        DatePickerDialog datePickerDialog;
    String StringdatetoSend = "";
//BIRTHDAY FORMAT 19751213

    public void getDate(final Context context, final TextView setData, int mYear, int mMonth, int mDay) {


        datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        java.text.DateFormat datetoShow = new SimpleDateFormat("MMM dd , yyyy");
                        java.text.DateFormat datetoSend = new SimpleDateFormat("yyyyMMdd");
                        Calendar mDate = null;

                        mDate = Calendar.getInstance();
                        mDate.set(year, monthOfYear, dayOfMonth);
                        datetoShow.format(mDate.getTime());

                        if (mDate.getTime().after(new Date())){

                            Toast.makeText(context, "The selected date is greater than the current date.", Toast.LENGTH_SHORT);

                        }else{

                            SharedPref sharedPref = new SharedPref();
                            sharedPref.setStringValue(sharedPref.USER, sharedPref.DAY, dayOfMonth + "", context);
                            sharedPref.setStringValue(sharedPref.USER, sharedPref.MONTH, monthOfYear + "", context);
                            sharedPref.setStringValue(sharedPref.USER, sharedPref.YEAR, year + "", context);

                            setData.setText(datetoShow.format(mDate.getTime()));
                            StringdatetoSend = datetoSend.format(mDate.getTime());
                            Log.d("DATE", "onDateSet: " + StringdatetoSend);
                            EventBus.getDefault().post(new RegistrationActivity.MessageEvent(StringdatetoSend));

                        }





                    }
                }, mYear, mMonth, mDay);


        datePickerDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                if (which == DialogInterface.BUTTON_NEGATIVE) {



                }
            }
        });
        datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
        datePickerDialog.show();

    }
}
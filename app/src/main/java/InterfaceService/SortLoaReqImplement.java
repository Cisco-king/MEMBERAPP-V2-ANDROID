package InterfaceService;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.medicard.com.medicard.R;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import mehdi.sakout.fancybuttons.FancyButton;
import model.SimpleData;
import utilities.DateConverter;

/**
 * Created by mpx-pawpaw on 2/20/17.
 */

public class SortLoaReqImplement {

    private Context context;
    private SortLoaReqCallback callback;
    private Dialog dialog;
    private int month, day, year;


    public SortLoaReqImplement(Context context, SortLoaReqCallback callback) {
        this.callback = callback;
        this.context = context;
    }

    public void showSortBy() {


        FancyButton status;
        FancyButton req_date;
        FancyButton hospital_clinic;
        FancyButton service_type;
        FancyButton tv_close;


        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_sort_loa_req);

        status = (FancyButton) dialog.findViewById(R.id.status);
        req_date = (FancyButton) dialog.findViewById(R.id.req_date);
        hospital_clinic = (FancyButton) dialog.findViewById(R.id.hospital_clinic);
        service_type = (FancyButton) dialog.findViewById(R.id.service_type);
        tv_close = (FancyButton) dialog.findViewById(R.id.tv_close);

        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.status));
                dialog.dismiss();
            }
        });
        req_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.request_date));
                dialog.dismiss();
            }
        });
        hospital_clinic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.hospital_clinic));
                dialog.dismiss();
            }
        });

        service_type.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.service_type));
                dialog.dismiss();
            }
        });
        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

        DisplayMetrics metrics = this.context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout(width, Toolbar.LayoutParams.WRAP_CONTENT);


    }

    public void showStatusSort() {


        FancyButton approved;
        FancyButton pending;
        FancyButton expired;
        FancyButton availed;
        FancyButton cancelled;
        FancyButton disapproved;
        FancyButton close;


        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_sortby_status);

        approved = (FancyButton) dialog.findViewById(R.id.approved);
        pending = (FancyButton) dialog.findViewById(R.id.pending);
        expired = (FancyButton) dialog.findViewById(R.id.expired);
        availed = (FancyButton) dialog.findViewById(R.id.availed);
        cancelled = (FancyButton) dialog.findViewById(R.id.cancelled);
        disapproved = (FancyButton) dialog.findViewById(R.id.disapproved);
        close = (FancyButton) dialog.findViewById(R.id.close);

        approved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortStatus(context.getString(R.string.approved));
                dialog.dismiss();
            }
        });
        pending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortStatus(context.getString(R.string.pending));
                dialog.dismiss();
            }
        });
        expired.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortStatus(context.getString(R.string.expired));
                dialog.dismiss();
            }
        });

        availed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortStatus(context.getString(R.string.availed));
                dialog.dismiss();
            }
        });
        cancelled.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortStatus(context.getString(R.string.cancelled));
                dialog.dismiss();
            }
        });
        disapproved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortStatus(context.getString(R.string.disapproved));
                dialog.dismiss();
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.dismiss();
            }
        });

        dialog.show();

        DisplayMetrics metrics = this.context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout(width, Toolbar.LayoutParams.WRAP_CONTENT);


    }

    public void showServiceType() {


        FancyButton maternity_consultation;
        FancyButton basic;
        FancyButton other_test;
        FancyButton tv_close;


        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_sort_servicetype);

        maternity_consultation = (FancyButton) dialog.findViewById(R.id.maternity_consultation);
        basic = (FancyButton) dialog.findViewById(R.id.basic);
        other_test = (FancyButton) dialog.findViewById(R.id.other_test);
        tv_close = (FancyButton) dialog.findViewById(R.id.tv_close);

        maternity_consultation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortServiceType(context.getString(R.string.maternity));
                dialog.dismiss();
            }
        });
        basic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortServiceType(context.getString(R.string.basic_test));
                dialog.dismiss();
            }
        });
        other_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortServiceType(context.getString(R.string.other_test_proc));
                dialog.dismiss();
            }
        });

        tv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        dialog.show();

        DisplayMetrics metrics = this.context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        dialog.getWindow().setLayout(width, Toolbar.LayoutParams.WRAP_CONTENT);


    }

    public void setFetchHospitals(TextView tv_hosp_clinic, ArrayList<SimpleData> temp) {

        String data = "";
        tv_hosp_clinic.setText("");
        if (temp.size() != 0) {


            for (int x = 0; x < temp.size(); x++) {
                if (temp.get(x).getSelected().equals("true")) {
                    data = temp.get(x).getHospital() + " , " + data;
                }
            }

            if (data.length() != 0)
                tv_hosp_clinic.setText(data.substring(0, data.length() - 2));


        }
    }

    public String showDatePicker(final TextView tv_start, final boolean isSecond, final SortLoaReqCallback callback, final TextView tv_end, int[] dateStarter) {
        final String date = "";
        final int mDay, mMonth, mYear;
        mYear = dateStarter[2];
        mMonth = dateStarter[1] - 1; // current month
        mDay = dateStarter[0]; // current day


        Calendar c = Calendar.getInstance();
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH) + 3);//Year,Mounth -1,Day
        Log.d("DATE_DATE", c.get(Calendar.YEAR) + "");
        Log.d("DATE_DATE", c.get(Calendar.MONTH) + "");
        Log.d("DATE_DATE", (c.get(Calendar.DAY_OF_MONTH) + 3) + "");

        DatePickerDialog datePickerDialog;

        datePickerDialog = new DatePickerDialog(context,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(android.widget.DatePicker view, int getYear,
                                          int monthOfYear, int dayOfMonth) {

                        month = monthOfYear + 1;
                        year = getYear;
                        day = dayOfMonth;
                        String dateTaken = DateConverter.convertDateFromYYYYMDD(year + "," + month + "," + day);


                        if (isSecond) {
                            if (DateConverter.testDataStartAndEnd(getTextTrimmed(tv_start), dateTaken)) {
                                tv_end.setText(dateTaken);
                            } else {
                                callback.datePickerEndDateError();
                            }
                        } else {
                            if (tv_end.getText().toString().equals(""))
                                tv_start.setText(dateTaken);
                            else {
                                if (DateConverter.testDataStartAndEnd(dateTaken, getTextTrimmed(tv_end))) {
                                    tv_start.setText(dateTaken);
                                } else {
                                    callback.datePickerStartDateError();
                                }
                            }
                        }
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.getDatePicker().setMaxDate(c.getTimeInMillis());
        datePickerDialog.show();

        return date;
    }

    private int addZero(int i) {

//        if (i <= 9) {
//            return Integer.parseInt(String.valueOf(0 + i));
//        } else
        return Integer.parseInt(String.valueOf(0 + i));
    }


    @NonNull
    public String getTextTrimmed(TextView data) {
        return data.getText().toString().trim();
    }

    public boolean isNullValue(TextView tv_req_date_start) {
        return tv_req_date_start.getText().toString().trim().equals("");
    }

    /**
     * if true return current date else
     * get set date
     *
     * @param tv_req_date_start
     * @return
     */
    public int[] getDateStarter(TextView tv_req_date_start) {
        int[] dateSet = new int[3];

        if (isNullValue(tv_req_date_start)) {
            final Calendar c = Calendar.getInstance();
            final int mDay, mMonth, mYear;
            mYear = c.get(Calendar.YEAR); // current year
            mMonth = c.get(Calendar.MONTH); // current month
            mDay = c.get(Calendar.DAY_OF_MONTH); // current day
            dateSet[0] = mDay;
            dateSet[1] = mMonth + 1;
            dateSet[2] = mYear;
        } else {
            dateSet = DateConverter.getDates(getTextTrimmed(tv_req_date_start));
        }

        return dateSet;
    }


    public void replaceData(ArrayList<SimpleData> prevSelected, ArrayList<SimpleData> temp1) {

        prevSelected.clear();
        prevSelected.addAll(temp1);
        temp1.clear();
    }
}
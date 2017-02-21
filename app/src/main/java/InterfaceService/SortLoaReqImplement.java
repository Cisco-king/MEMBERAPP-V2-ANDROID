package InterfaceService;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.medicard.com.medicard.R;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.util.ArrayList;

import mehdi.sakout.fancybuttons.FancyButton;
import model.SimpleData;

/**
 * Created by mpx-pawpaw on 2/20/17.
 */

public class SortLoaReqImplement {

    private Context context;
    private SortLoaReqCallback callback;
    private Dialog dialog;


    public SortLoaReqImplement(Context context, SortLoaReqCallback callback) {
        this.callback = callback;
        this.context = context;
    }

    public void showSortBy() {


        FancyButton doctor_family;
        FancyButton tv_specialization;
        FancyButton tv_room;
        FancyButton tv_close;


        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_sortby_doctors);

        doctor_family = (FancyButton) dialog.findViewById(R.id.doctor_family);
        tv_specialization = (FancyButton) dialog.findViewById(R.id.tv_specialization);
        tv_room = (FancyButton) dialog.findViewById(R.id.tv_room);
        tv_close = (FancyButton) dialog.findViewById(R.id.tv_close);

        doctor_family.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.doctor_family));
                dialog.dismiss();
            }
        });
        tv_specialization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.specialization));
                dialog.dismiss();
            }
        });
        tv_room.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onSortListener(context.getString(R.string.room_number));
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

        for (int x = 0; x < temp.size(); x++) {
            if (temp.get(x).getSelected().equals("true")){
                data = temp.get(x).getHospital() + " , " + data;
            }
        }

        tv_hosp_clinic.setText(data.substring(0 , data.length() - 2));

    }
}

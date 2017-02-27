package adapter;

import android.content.Context;
import android.medicard.com.medicard.R;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;

import InterfaceService.LOARequestCallback;
import Sqlite.DatabaseHandler;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Doctor;
import model.DoctorsToHospital;
import model.LoaFetch;
import model.TheDoctor;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.DateConverter;


/**
 * Created by mpx-pawpaw on 1/18/17.
 */

public class LoaRequestAdapter extends RecyclerView.Adapter<LoaRequestAdapter.Holder> {

    DatabaseHandler databaseHandler;
    private Context context;
    private ArrayList<LoaFetch> arrayList;
    LOARequestCallback callback;


    public LoaRequestAdapter(Context context, ArrayList<LoaFetch> arrayList, DatabaseHandler databaseHandler, LOARequestCallback callback) {
        this.arrayList = arrayList;
        this.context = context;
        this.databaseHandler = databaseHandler;
        this.callback = callback;
    }

    @Override
    public LoaRequestAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = null;

        view = LayoutInflater.from(context).inflate(R.layout.row_loa_request, parent, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(LoaRequestAdapter.Holder viewHolder, final int position) {


        final Holder holder = (Holder) viewHolder;
        holder.tv_remark.setText(arrayList.get(position).getRemarks());
        holder.tv_req_date.setText("Request Date: " + DateConverter.convertDateToMMddyyyy(DateConverter.convertDatetoMMMddyyy(arrayList.get(position).getDateAdmitted())));
        holder.tv_status.setText(arrayList.get(position).getStatus());
        holder.tv_doctor.setText(arrayList.get(position).getDoctorName());
        holder.tv_spec.setText(arrayList.get(position).getDoctorSpec());
        holder.tv_hospname.setText(arrayList.get(position).getHospitalName());

        holder.tv_sched.setText(arrayList.get(position).getSchedule());
        holder.tv_room.setText(arrayList.get(position).getRoom());

        holder.tv_sched.setVisibility(View.GONE);
        holder.tv_room.setVisibility(View.GONE);
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_req_date)
        TextView tv_req_date;

        @BindView(R.id.tv_status)
        TextView tv_status;

        @BindView(R.id.tv_remark)
        TextView tv_remark;

        @BindView(R.id.tv_hospname)
        TextView tv_hospname;

        @BindView(R.id.tv_doctor)
        TextView tv_doctor;

        @BindView(R.id.tv_spec)
        TextView tv_spec;

        @BindView(R.id.tv_room)
        TextView tv_room;

        @BindView(R.id.tv_sched)
        TextView tv_sched;

        @BindView(R.id.cv_account)
        CardView cv_account;

        @BindView(R.id.loading1)
        ProgressBar loading1;
        @BindView(R.id.loading2)
        ProgressBar loading2;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);


            cv_account.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.gotoLoaPage(arrayList, getAdapterPosition());
                }
            });
        }
    }


}

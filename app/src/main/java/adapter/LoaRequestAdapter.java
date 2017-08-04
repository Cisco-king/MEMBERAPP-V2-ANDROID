package adapter;

import android.content.Context;

import com.medicard.member.R;
import com.medicard.member.module.viewLoa.ViewLoaRetrieve;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ProgressBar;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

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
import services.model.MaceRequest;
import timber.log.Timber;
import utilities.DateConverter;


/**
 * Created by mpx-pawpaw on 1/18/17.
 */

public class LoaRequestAdapter extends RecyclerView.Adapter<LoaRequestAdapter.Holder> {

    DatabaseHandler databaseHandler;
    private Context context;
    private List<MaceRequest> arrayList;
    ViewLoaRetrieve.ViewLoaRetrieveCallback callback;


    private LayoutInflater inflater;


    public LoaRequestAdapter(Context context, List<MaceRequest> arrayList, ViewLoaRetrieve.ViewLoaRetrieveCallback callback) {
        this.arrayList = arrayList;
        this.context = context;
        this.callback = callback;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public LoaRequestAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_loa_request, parent, false);
       /* View view = null;
        view = LayoutInflater.from(context).inflate(R.layout.row_loa_request, parent, false);*/
        return new Holder(view);

    }

    public void update(List<MaceRequest> loaFetches) {
        arrayList = loaFetches;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(LoaRequestAdapter.Holder holder, int position) {

        MaceRequest maceRequest = arrayList.get(position);
        //Timber.d("%s", loaFetch.toString());
        System.out.println("approval no." + maceRequest.getStatus());
// TODO REPLACE DUMMY DATA
        holder.tv_requestType.setText(maceRequest.getRequestType());
        holder.tv_req_date.setText("Request Date: " + DateConverter.convertDateToMMddyyyy(DateConverter.convertDatetoMMMddyyy(maceRequest.getRequestDatetime())));
        holder.tv_status.setText(maceRequest.getStatus());
        holder.tv_hospname.setText(maceRequest.getHospitalName());
        holder.tv_doctor.setText(maceRequest.getDoctorName());
        holder.tv_spec.setText(maceRequest.getDoctorSpec());
//        holder.tv_hospname.setText(maceRequest.getHospitalName());
//        holder.tv_hospname.setText(loaFetch.getId());


//        holder.tv_sched.setText(arrayList.get(position).getSchedule());
//        holder.tv_room.setText(arrayList.get(position).getRoom());

//        holder.tv_sched.setVisibility(View.GONE);
//        holder.tv_room.setVisibility(View.GONE);
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

        @BindView(R.id.tv_requestType)
        TextView tv_requestType;

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

package adapter;

import android.content.Context;
import android.medicard.com.medicard.R;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;


import java.util.ArrayList;

import InterfaceService.LOARequestCallback;
import Sqlite.DatabaseHandler;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.Doctor;
import model.LoaList;
import model.TheDoctor;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;


/**
 * Created by mpx-pawpaw on 1/18/17.
 */

public class LoaRequestAdapter extends RecyclerView.Adapter<LoaRequestAdapter.Holder> {

    DatabaseHandler databaseHandler;
    private Context context;
    private ArrayList<LoaList> arrayList;
    LOARequestCallback callback;

    public LoaRequestAdapter(Context context, ArrayList<LoaList> arrayList, DatabaseHandler databaseHandler, LOARequestCallback callback) {
        this.arrayList = arrayList;
        this.context = context;
        this.databaseHandler = databaseHandler;
        this.callback = callback;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_loa_request, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {


        getDataAndDisplay(holder.tv_hospname, arrayList.get(position).getHospitalCode(), databaseHandler);
        holder.tv_remark.setText(arrayList.get(position).getRemarks());
        holder.tv_req_date.setText(arrayList.get(position).getApprovalDate());
        holder.tv_room.setText(arrayList.get(position).getRoom());
        holder.tv_sched.setVisibility(View.GONE);
        holder.tv_status.setText(arrayList.get(position).getStatus());
        getDoctorData(holder.tv_doctor, holder.tv_spec, arrayList.get(position).getDoctorCode());
//        holder.gotoRequest.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("LOAD_REQ", "CLICKED" + position);
//            }
//        });

    }

    private void getDoctorData(TextView tv_doctor, TextView tv_spec, String doctorCode) {
        fetchData(doctorCode, tv_doctor, tv_spec);
    }

    private void fetchData(final String doctorCode, final TextView tv_doctor, final TextView tv_spec) {

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getDoctorData(doctorCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<TheDoctor>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.d("DOCTOR_CODE", doctorCode);
                        Log.d("DOCTOR_CODE", e.getMessage());
                        doctorNotFound(doctorCode, tv_doctor, tv_spec);
                    }

                    @Override
                    public void onNext(TheDoctor theDoctor) {

                        if (theDoctor.getResponseCode().equals("210")) {
                            doctorNotFound(doctorCode, tv_doctor, tv_spec);
                        } else {
                            onSuccessListener(theDoctor.getDoctor(), tv_doctor, tv_spec);
                        }
                    }
                });
    }

    private void getDataAndDisplay(TextView tv_hospname, String hospitalCode, DatabaseHandler databaseHandler) {
        tv_hospname.setText(databaseHandler.getHospitalName(hospitalCode));
    }


    public void onSuccessListener(Doctor theDoctor, TextView tv_doctor, TextView tv_spec) {
        tv_doctor.setText("---" + theDoctor.getDocFname() + " " +
                theDoctor.getDocLname());

        tv_spec.setText("---" + theDoctor.getSpecDesc());
    }


    public void onErrorListener(TextView tv_doctor, TextView tv_spec) {
        tv_doctor.setText("error");
        tv_spec.setText("error");
    }


    public void doctorNotFound(String doctorCode, TextView tv_doctor, TextView tv_spec) {
        tv_doctor.setText(doctorCode);
        tv_spec.setText("Not Specified");
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

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}

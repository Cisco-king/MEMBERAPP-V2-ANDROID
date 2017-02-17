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
import model.LoaFetch;
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
        getDataAndDisplay(holder.tv_hospname, arrayList.get(position).getHospitalCode(), databaseHandler);
        holder.tv_remark.setText(arrayList.get(position).getRemarks());
        holder.tv_req_date.setText(arrayList.get(position).getApprovalDate());
        holder.tv_room.setText(arrayList.get(position).getRoom());
        holder.tv_sched.setVisibility(View.GONE);
        holder.tv_status.setText(arrayList.get(position).getStatus());
        getDoctorData(holder.tv_doctor, holder.tv_spec, arrayList.get(position).getDoctorCode(), position, holder.loading1, holder.loading2);


    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }


    private void getDoctorData(TextView tv_doctor, TextView tv_spec, String doctorCode, int position, ProgressBar loading1, ProgressBar loading2) {
        if (arrayList.get(position).getDoctorSpec().equals(""))
            fetchData(doctorCode, tv_doctor, tv_spec, arrayList, position, loading1, loading2);
        else
            dataFetchAndReadyToDisp(tv_doctor, tv_spec, arrayList, position , loading1 , loading2);

    }

    private void dataFetchAndReadyToDisp(TextView tv_doctor, TextView tv_spec, ArrayList<LoaFetch> arrayList, int position, ProgressBar loading2, ProgressBar loading1) {
        tv_doctor.setText(arrayList.get(position).getDoctorName());
        tv_spec.setText(arrayList.get(position).getDoctorSpec());


        tv_doctor.setVisibility(View.VISIBLE);
        tv_spec.setVisibility(View.VISIBLE);
        loading1.setVisibility(View.GONE);
        loading2.setVisibility(View.GONE);
    }

    private void fetchData(final String doctorCode, final TextView tv_doctor, final TextView tv_spec, final ArrayList<LoaFetch> arrayList, final int position, final ProgressBar loading1, final ProgressBar loading2) {

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
                        Log.d("DOCTOR_CODE", e.getMessage());

                        if (e.getMessage().contains("Expected BEGIN_OBJECT but was STRING")) {
                            doctorNotFound(doctorCode, tv_doctor, tv_spec, arrayList, position);
                        } else {
                            onErrorListener(tv_doctor, tv_spec);
                        }

                        tv_doctor.setVisibility(View.VISIBLE);
                        tv_spec.setVisibility(View.VISIBLE);
                        loading1.setVisibility(View.GONE);
                        loading2.setVisibility(View.GONE);

                    }

                    @Override
                    public void onNext(TheDoctor theDoctor) {

                        if (theDoctor.getResponseCode().equals("210")) {
                            doctorNotFound(doctorCode, tv_doctor, tv_spec, arrayList, position);
                            tv_doctor.setVisibility(View.VISIBLE);
                            tv_spec.setVisibility(View.VISIBLE);
                            loading1.setVisibility(View.GONE);
                            loading2.setVisibility(View.GONE);
                        } else {
                            onSuccessListener(theDoctor.getDoctor(), tv_doctor, tv_spec, arrayList, position, loading1, loading2);
                        }
                    }
                });
    }

    private void getDataAndDisplay(TextView tv_hospname, String hospitalCode, DatabaseHandler databaseHandler) {
        tv_hospname.setText(databaseHandler.getHospitalName(hospitalCode));
    }


    public void onSuccessListener(Doctor theDoctor, TextView tv_doctor, TextView tv_spec, ArrayList<LoaFetch> arrayList, int position, ProgressBar loading1, ProgressBar loading2) {
        tv_doctor.setText(theDoctor.getDocFname() + " " +
                theDoctor.getDocLname());

        tv_spec.setText(theDoctor.getSpecDesc());
        arrayList.get(position).setDoctorName(theDoctor.getDocFname() + " " +
                theDoctor.getDocLname());
        arrayList.get(position).setDoctorSpec(theDoctor.getSpecDesc());
        arrayList.get(position).setDoctorSpecCode(theDoctor.getSpecCode());

        tv_doctor.setVisibility(View.VISIBLE);
        tv_spec.setVisibility(View.VISIBLE);
        loading1.setVisibility(View.GONE);
        loading2.setVisibility(View.GONE);
    }


    public void onErrorListener(TextView tv_doctor, TextView tv_spec) {
        tv_doctor.setText("error");
        tv_spec.setText("error");
    }


    public void doctorNotFound(String doctorCode, TextView tv_doctor, TextView tv_spec, ArrayList<LoaFetch> arrayList, int position) {
        tv_doctor.setText(doctorCode);
        tv_spec.setText("Not Specified");

        arrayList.get(position).setDoctorName(doctorCode);
        arrayList.get(position).setDoctorSpec("Not Specified");
        arrayList.get(position).setDoctorSpecCode("Not Specified");

    }


//    public class Holder_unLoad extends RecyclerView.ViewHolder {
//
//        @BindView(R.id.tv_remark)
//        TextView tv_remark;
//
//        @BindView(R.id.loading)
//        ProgressBar loading;
//
//        @BindView(R.id.tv_status)
//        TextView tv_status;
//
//        public Holder_unLoad(View itemView) {
//            super(itemView);
//            ButterKnife.bind(this, itemView);
//        }
//    }

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

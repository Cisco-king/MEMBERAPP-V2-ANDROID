package modules.doctor.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import database.entity.Doctor;
import services.model.HospitalsToDoctor;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public class DoctorsAdapter extends RecyclerView.Adapter<DoctorsAdapter.ViewHolder> {


    private Context context;
    private List<HospitalsToDoctor> doctors;

    private LayoutInflater inflater;

    private OnItemClickListener listener;

    public DoctorsAdapter(Context context, List<HospitalsToDoctor> doctors, DoctorsAdapter.OnItemClickListener listener) {
        this.context = context;
        this.doctors = doctors;

        inflater = LayoutInflater.from(context);

        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_doctor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HospitalsToDoctor doctor = doctors.get(position);
        holder.tvDoctorName.setText(doctor.getFullName());
        holder.tvSpecialization.setText(doctor.getSpecDesc());
        holder.tvHospitalClinic.setText(doctor.getHospitalName());
    }

    public void update(List<HospitalsToDoctor> doctors) {
        this.doctors = doctors;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvDoctorName) TextView tvDoctorName;
        @BindView(R.id.tvSpecialization) TextView tvSpecialization;
        @BindView(R.id.tvHospitalClinic) TextView tvHospitalClinic;

        @BindView(R.id.btnNext) ImageButton btnNext;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }

    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

}

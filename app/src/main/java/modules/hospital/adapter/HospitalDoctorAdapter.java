package modules.hospital.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.callback.RecyclerViewOnClickListener;
import model.HospitalList;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public class HospitalDoctorAdapter extends
        RecyclerView.Adapter<HospitalDoctorAdapter.ViewHolder> {


    private Context context;
    private List<HospitalList> hospitals;

    private LayoutInflater inflater;

    private RecyclerViewOnClickListener listener;

    public HospitalDoctorAdapter(Context context, List<HospitalList> hospitals, RecyclerViewOnClickListener listener) {
        this.context = context;
        this.hospitals = hospitals;

        inflater = LayoutInflater.from(context);

        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_hospital, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HospitalList hospital = hospitals.get(position);
        String hospitalName = (hospital.getHospitalName() != null) ? hospital.getHospitalName() : "";
        holder.tvHospitalOrClinicName.setText(hospitalName);
    }

    public void update(List<HospitalList> hospitals) {
        this.hospitals = hospitals;
        notifyDataSetChanged();
    }

    public HospitalList getHospital(int position) {
        return hospitals.get(position);
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.tvHospitalOrClinicName) TextView tvHospitalOrClinicName;

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

}

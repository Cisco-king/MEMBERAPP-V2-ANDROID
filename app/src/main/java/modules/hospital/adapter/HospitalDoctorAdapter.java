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
        View view = inflater.inflate(R.layout.item_doctor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HospitalList hospital = hospitals.get(position);
        holder.tvHospitalCLinicName.setText(hospital.getHospitalName());
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvHospitalClinicName) TextView tvHospitalCLinicName;

        public ViewHolder(View view) {
            super(view);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(getAdapterPosition());
        }
    }

}

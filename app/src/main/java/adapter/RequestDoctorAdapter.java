package adapter;

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
import model.Doctor;

/**
 * Created by casjohnpaul on 4/27/2017.
 */

public class RequestDoctorAdapter extends
        RecyclerView.Adapter<RequestDoctorAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;
    private List<String> doctors;

    public RequestDoctorAdapter(Context context, List<String> doctors) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.doctors = doctors;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = inflater.inflate(R.layout.item_doctor, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String doctor = doctors.get(position);
        holder.tvDoctorName.setText(doctor);
    }

    @Override
    public int getItemCount() {
        return doctors.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDoctorName) TextView tvDoctorName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}

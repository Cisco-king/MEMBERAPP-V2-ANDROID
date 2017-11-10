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
import model.DoctorTest;

/**
 * Created by John Paul Cas on 4/11/2017.
 */

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.ViewHolder> {

    private Context context;
    private LayoutInflater inflater;

    private List<DoctorTest> tests;
    private TestAdapter.OnClickListener listener;

    public TestAdapter(Context context, List<DoctorTest> tests, TestAdapter.OnClickListener listener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.tests = tests;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = inflater.inflate(R.layout.item_test, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DoctorTest doctor = tests.get(position);
        holder.tvHospitalName.setText(doctor.getHospitalName());
        holder.tvDoctorName.setText(doctor.getDoctorName());
        holder.tvDateOfConsultation.setText(doctor.getDateOfConsultation());
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.tvHospitalName)
        TextView tvHospitalName;
        @BindView(R.id.tvDoctorName)
        TextView tvDoctorName;
        @BindView(R.id.tvDateOfConsultation)
        TextView tvDateOfConsultation;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onClick(getAdapterPosition());
        }
    }

    public interface OnClickListener {
        void onClick(int position);
    }

}

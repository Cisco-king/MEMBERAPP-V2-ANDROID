package modules.diagnosis.adapter;

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
import services.model.Diagnosis;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class DiagnosisAdapter extends
        RecyclerView.Adapter<DiagnosisAdapter.ViewHolder> {


    private Context context;

    private List<Diagnosis> diagnosisList;

    private RecyclerViewOnClickListener listener;
    private LayoutInflater inflater;

    public DiagnosisAdapter(Context context, List<Diagnosis> diagnosisList, RecyclerViewOnClickListener listener) {
        this.context = context;
        this.listener = listener;

        this.diagnosisList = diagnosisList;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_diagnosis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Diagnosis diagnosis = diagnosisList.get(position);
        holder.tvDiagnosisName.setText(diagnosis.getDiagDesc());
    }

    public void update(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return diagnosisList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tvDiagnosisName) TextView tvDiagnosisName;

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

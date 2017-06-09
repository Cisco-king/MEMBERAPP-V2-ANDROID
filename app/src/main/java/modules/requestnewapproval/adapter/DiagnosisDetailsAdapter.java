package modules.requestnewapproval.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.newtest.DiagnosisDetails;
import services.model.Procedure;

/**
 * Created by casjohnpaul on 6/5/2017.
 */

public class DiagnosisDetailsAdapter extends RecyclerView.Adapter<DiagnosisDetailsAdapter.ViewHolder> {


    private List<DiagnosisDetails> diagnosisDetails;

    public DiagnosisDetailsAdapter(List<DiagnosisDetails> diagnosisDetails) {
        this.diagnosisDetails = diagnosisDetails;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_diagnosis_list, parent, false);
        ViewHolder row = new ViewHolder(view);
        return row;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DiagnosisDetails diagnosisDetails = this.diagnosisDetails.get(position);
        holder.bind(diagnosisDetails.getDiagnosis().getDiagDesc(),
                getDiagnosisProceduresDetails(diagnosisDetails.getProcedures()));
    }

    @Override
    public int getItemCount() {
        return diagnosisDetails.size();
    }

    public String getDiagnosisProceduresDetails(List<Procedure> procedures) {
        StringBuilder procedureDetails = new StringBuilder();
        if (procedures.size() == 1) {
            procedureDetails.append(procedures.get(0).getProcedureDesc())
                    .append("\n");
        } else {
            for (Procedure procedure : procedures) {
                procedureDetails.append(procedure.getProcedureDesc())
                        .append("\n");
            }
        }

        return procedureDetails.toString();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvPrimaryDiagnosisName) TextView tvPrimaryDiagnosisName;
        @BindView(R.id.tvPrimaryDiagnosisDetails) TextView tvPrimaryDiagnosisDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String primaryDiagnosisName, String primaryDiagnosisDetails) {
            tvPrimaryDiagnosisName.setText(primaryDiagnosisName);
            tvPrimaryDiagnosisDetails.setText(primaryDiagnosisDetails);
        }
    }

}

package modules.requestnewapproval.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import services.model.Procedure;
import services.model.Test;

/**
 * Created by casjohnpaul on 6/5/2017.
 */

public class DiagnosisDetailsAdapter extends RecyclerView.Adapter<DiagnosisDetailsAdapter.ViewHolder> {


    private List<DiagnosisTests> diagnosisDetails;

    public DiagnosisDetailsAdapter(List<DiagnosisTests> diagnosisDetails) {
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
        DiagnosisTests diagnosisDetails = this.diagnosisDetails.get(position);
        holder.bind(diagnosisDetails.getDiagnosis().getDiagDesc(),
                getDiagnosisTestsDetails(diagnosisDetails.getTests()));
    }

    @Override
    public int getItemCount() {
        return diagnosisDetails.size();
    }

    public String getDiagnosisTestsDetails(List<Test> tests) {
        StringBuilder procedureDetails = new StringBuilder();
        if (tests.size() == 1) {
            procedureDetails.append(tests.get(0).getProcedureName())
                    .append("\n");
        } else {
            for (Test test : tests) {
                procedureDetails.append(test.getProcedureName())
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

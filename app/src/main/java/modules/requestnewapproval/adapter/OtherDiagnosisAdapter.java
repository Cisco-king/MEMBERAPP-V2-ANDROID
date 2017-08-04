package modules.requestnewapproval.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;
import com.medicard.member.module.DiagnosisTallyActivity.adapter.DiagnosisTallyAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import services.model.Test;

/**
 * Created by macbookpro on 7/28/17.
 */

public class OtherDiagnosisAdapter extends RecyclerView.Adapter<OtherDiagnosisAdapter.ViewHolder> {


    private List<DiagnosisTests> diagnosisDetails;

    public OtherDiagnosisAdapter(List<DiagnosisTests> diagnosisDetails) {
        this.diagnosisDetails = diagnosisDetails;

    }

    @Override
    public OtherDiagnosisAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_other_diagnosis, parent, false);
        ViewHolder row = new ViewHolder(view);
        return row;
    }

    @Override
    public void onBindViewHolder(OtherDiagnosisAdapter.ViewHolder holder, int position) {
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

        @BindView(R.id.tvOtherDiagnosisName)
        TextView tvOtherDiagnosisName;
        @BindView(R.id.tvOtherDiagnosisDetails)
        TextView tvOtherDiagnosisDetails;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String otherDiagnosisName, String otherDiagnosisDetails) {
            tvOtherDiagnosisName.setText(otherDiagnosisName);
            tvOtherDiagnosisDetails.setText(otherDiagnosisDetails);
        }
    }

}

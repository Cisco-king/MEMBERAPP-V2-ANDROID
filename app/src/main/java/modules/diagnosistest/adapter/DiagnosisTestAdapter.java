package modules.diagnosistest.adapter;

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

/**
 * Created by casjohnpaul on 5/2/2017.
 */

public class DiagnosisTestAdapter extends RecyclerView.Adapter<DiagnosisTestAdapter.ViewHolder> {


    private Context context;

    private List<String> testDiagnosis;
    private LayoutInflater inflater;

    public DiagnosisTestAdapter(Context context, List<String> testDiagnosis) {
        this.context = context;
        this.testDiagnosis = testDiagnosis;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_diagnosis_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String diagnosisName = testDiagnosis.get(position);
        holder.tvDiagnosisTestName.setText(diagnosisName);
    }

    @Override
    public int getItemCount() {
        return testDiagnosis.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvDiagnosisTestName) TextView tvDiagnosisTestName;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}

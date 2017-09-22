package com.medicard.member.module.DiagnosisTallyActivity.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.core.model.DiagnosisTests;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import modules.base.activities.TestTrackableActivity;
import modules.hospital.adapter.HospitalDoctorAdapter;
import services.model.Diagnosis;
import services.model.Test;

/**
 * Created by Aquino Francisco on 7/21/17.
 */

public class DiagnosisTallyAdapter extends RecyclerView.Adapter<DiagnosisTallyAdapter.ViewHolder> {


    private LayoutInflater inflater;
    private Context context1;


    List<DiagnosisTests> diagnosisList;
    private OnDiagnosisClickListener onDiagnosisClickListener;


    public DiagnosisTallyAdapter(Context context, List<DiagnosisTests> diagnosisList, OnDiagnosisClickListener onDiagnosisClickListener){
        System.out.println("======================= LIST OF DIAGNOSIS " + diagnosisList);
        this.context1 = context;
        this.diagnosisList = diagnosisList;
        this.onDiagnosisClickListener = onDiagnosisClickListener;

        inflater  = LayoutInflater.from(context);

    }
    @Override
    public int getItemCount() {
        return diagnosisList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position){
        DiagnosisTests diagTest  = diagnosisList.get(position);
        System.out.println("=================== Description " + diagTest.getDiagnosis().getDiagDesc());
        System.out.println("=================== SIZE " + diagnosisList.size());
        holder.bind(diagTest.getDiagnosis().getDiagDesc(),
                getDiagnosisTestsDetails(diagTest.getTests()));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_diagnosistally, parent,false);
        return new ViewHolder(view);
    }

    public String getDiagnosisTestsDetails(List<Test> tests) {
        System.out.println("=================== TESTS " + tests.get(0).getProcedureName());
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

        System.out.println("=================== PROCEDURE DETAILS" + procedureDetails.toString());
        return procedureDetails.toString();
    }

    public void update(List<DiagnosisTests> diagnosistestsList) {
        this.diagnosisList = diagnosistestsList;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        @BindView(R.id.tv_diagnosisName) TextView diagnosisName;
        @BindView(R.id.tv_testName) TextView testName;
        @BindView(R.id.btn_deleteDiagnosis)
        ImageButton btn_deleteDiagnosis;
        @BindView(R.id.ibDiagnosis)
        ImageButton ibDiagnosis;



        public ViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            btn_deleteDiagnosis.setOnClickListener(this);
            ibDiagnosis.setOnClickListener(this);
        }
        public void bind(String primaryDiagnosisName, String diagnosisDetails){
            diagnosisName.setText(primaryDiagnosisName);
            testName.setText(diagnosisDetails);

        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.btn_deleteDiagnosis){
                onDiagnosisClickListener.onRemoveDiagnosis(getAdapterPosition());
            }else if(v.getId() == R.id.ibDiagnosis){
                onDiagnosisClickListener.onEditDiagnosis(getAdapterPosition());
            }
        }
    }
    public interface OnDiagnosisClickListener{
        void onRemoveDiagnosis(int position);

        void onEditDiagnosis(int position);
    }
}

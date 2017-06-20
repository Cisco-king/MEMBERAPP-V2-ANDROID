package com.medicard.member.module.diagnosistest.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import services.model.Procedure;
import services.model.Test;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class TestProcedureAdapter extends RecyclerView.Adapter<TestProcedureAdapter.ViewHolder> {


    private List<Test> tests;

    public TestProcedureAdapter(List<Test> tests) {
        this.tests = tests;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_procedure, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindData(tests.get(position));

        holder.cbProcedure.setOnCheckedChangeListener(null);

        holder.cbProcedure.setChecked(tests.get(position).isSelected());

        holder.cbProcedure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                tests.get(holder.getAdapterPosition()).setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvProcedureName)
        TextView tvProcedureName;
        @BindView(R.id.tvProcedureType)
        TextView tvProcedureType;

        @BindView(R.id.cbProcedure)
        CheckBox cbProcedure;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindData(Test procedure) {
            tvProcedureName.setText(procedure.getProcedureName());
//            tvProcedureType.setText(procedure.getServiceClassCode());
            tvProcedureType.setText(procedure.getCostCenter());

        }
    }
}

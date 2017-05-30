package modules.procedure.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import services.model.Procedure;

/**
 * Created by casjohnpaul on 5/14/2017.
 */

public class ProcedureAdapter extends RecyclerView.Adapter<ProcedureAdapter.ViewHolder> {


    private List<Procedure> procedures;

    public ProcedureAdapter(List<Procedure> procedures) {
        this.procedures = filterByUniqueSet(procedures);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_procedure, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.bindData(procedures.get(position));

        holder.cbProcedure.setOnCheckedChangeListener(null);

        holder.cbProcedure.setChecked(procedures.get(position).isSelected());

        holder.cbProcedure.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                procedures.get(holder.getAdapterPosition()).setSelected(isChecked);
            }
        });
    }

    @Override
    public int getItemCount() {
        return procedures.size();
    }

    public List<Procedure> filterByUniqueSet(List<Procedure> procedures) {
        Set<Procedure> procedureSet = new LinkedHashSet<>(procedures);

        procedures = new ArrayList<>(procedureSet);
        return procedures;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tvProcedureName) TextView tvProcedureName;
        @BindView(R.id.tvProcedureType) TextView tvProcedureType;

        @BindView(R.id.cbProcedure) CheckBox cbProcedure;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void bindData(Procedure procedure) {
            tvProcedureName.setText(procedure.getProcedureDesc());
            tvProcedureType.setText(procedure.getServiceClassCode());
        }
    }

}

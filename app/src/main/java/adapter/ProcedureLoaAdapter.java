package adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import services.model.MaceRequest;

/**
 * Created by IPC on 12/11/2017.
 */

public class ProcedureLoaAdapter extends RecyclerView.Adapter<ProcedureLoaAdapter.Holder> {
    private Context context;
    ArrayList<MaceRequest.MappedTest> mappedTests = new ArrayList<>();


    public ProcedureLoaAdapter(Context context, ArrayList<MaceRequest.MappedTest> mappedTests) {
        this.context = context;
        this.mappedTests = mappedTests;
    }


    @Override
    public ProcedureLoaAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loa_procedure_row, parent, false);
        ButterKnife.bind(this, view);
        return new ProcedureLoaAdapter.Holder(view);

    }

    @Override
    public void onBindViewHolder(ProcedureLoaAdapter.Holder holder, int position) {
        MaceRequest.MappedTest mappedTest = mappedTests.get(position);
        holder.ll_row.setBackgroundColor((position%2) != 0 ? ContextCompat.getColor(context, R.color.grey_1):ContextCompat.getColor(context, R.color.white));
        holder.tv_proc_desc.setText(mappedTest.getProcDesc());
        holder.tv_proc_amount.setText("P " + mappedTest.getAmount());

    }

    @Override
    public int getItemCount() {
        return mappedTests.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_proc_desc)
        TextView tv_proc_desc;
        @BindView(R.id.tv_proc_amount)
        TextView tv_proc_amount;
        @BindView(R.id.ll_row)
        LinearLayout ll_row;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
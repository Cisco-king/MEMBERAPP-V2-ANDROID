package modules.selecttest.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.ItemSelectTest;

/**
 * Created by John Paul Cas on 4/21/2017.
 */

public class SelectTestAdapter extends RecyclerView.Adapter<SelectTestAdapter.ViewHolder> {


    private Context context;
    private LayoutInflater inflater;

    private List<ItemSelectTest> selectTests;
    private static OnItemClickListener clickListener;

    public SelectTestAdapter(Context context, List<ItemSelectTest> selectTests, SelectTestAdapter.OnItemClickListener listener) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.selectTests = selectTests;
        clickListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = inflater.inflate(R.layout.item_select_test, parent, false);
        ViewHolder holder = new ViewHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemSelectTest item = selectTests.get(position);
        holder.tvTestName.setText(item.getTestName());
        holder.tvCostCenter.setText(item.getCostCenter());
        holder.tvDiagnosis.setText(item.getDiagnosis());
    }

    @Override
    public int getItemCount() {
        return selectTests.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        @BindView(R.id.tvTestName)
        TextView tvTestName;
        @BindView(R.id.tvCostCenter)
        TextView tvCostCenter;
        @BindView(R.id.tvDiagnosis)
        TextView tvDiagnosis;
        @BindView(R.id.cbTestSelect)
        CheckBox cbTestSelect;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
            cbTestSelect.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v instanceof CheckBox && v.getId() == R.id.cbTestSelect) {
                CheckBox checkBox = (CheckBox) v;
                clickListener.onItemCheck(getAdapterPosition(), checkBox.isChecked() ? true : false);
            } else {
                clickListener.onItemClick(getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);

        void onItemCheck(int position, boolean isCheck);
    }

}

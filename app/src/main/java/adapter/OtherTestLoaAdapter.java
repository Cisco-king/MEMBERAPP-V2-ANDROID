package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import services.model.MaceRequest;

/**
 * Created by IPC on 11/10/2017.
 * <p>
 * used for display of selected data for other test
 * <p>
 * -- jhay
 */

public class OtherTestLoaAdapter extends RecyclerView.Adapter<OtherTestLoaAdapter.Holder> {
    private Context context;
    ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> arrayListGroupedByDiag = new ArrayList<>();



    public OtherTestLoaAdapter(Context context, ArrayList<MaceRequest.GroupedByCostCenter.GroupedByDiag> arrayListGroupedByDiag) {
        this.context = context;
        this.arrayListGroupedByDiag = arrayListGroupedByDiag;
    }


    @Override
    public OtherTestLoaAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.loa_procedure_row_nested, parent, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {

        ArrayAdapter<String> adapterProcedures, adapterPrice;
        ArrayList<String> arrayListProcedures = new ArrayList<String>();
        ArrayList<String> arrayListPrice = new ArrayList<String>();


        MaceRequest.GroupedByCostCenter.GroupedByDiag groupedByDiag = arrayListGroupedByDiag.get(position);

        try {
            for (MaceRequest.MappedTest mappedTest : groupedByDiag.getMappedTest()) {
                arrayListProcedures.add(mappedTest.getProcDesc());
                arrayListPrice.add("P " + mappedTest.getAmount());
            }
            adapterProcedures = new ArrayAdapter<>(context, R.layout.list_view_text_size, arrayListProcedures);
            holder.lv_procedure.setAdapter(adapterProcedures);
            holder.lv_procedure.setEnabled(false);
            setListViewHeightBasedOnItems(holder.lv_procedure);

            adapterPrice = new ArrayAdapter<>(context, R.layout.list_view_text_size, arrayListPrice);
            holder.lv_price.setAdapter(adapterPrice);
            holder.lv_price.setEnabled(false);
            setListViewHeightBasedOnItems(holder.lv_price);

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (groupedByDiag.isFirstInstance()) {
            holder.tv_procedure_name.setVisibility(View.VISIBLE);
            holder.tv_processName.setVisibility(View.VISIBLE);
            holder.tv_procedure_name.setText(groupedByDiag.getDiagType() == 1 ? "PRIMARY DIAGNOSIS:" : "OTHER DIAGNOSIS:");
            holder.tv_processName.setText(groupedByDiag.getDiagDesc());
        } else {
            holder.tv_procedure_name.setVisibility(View.GONE);
            holder.tv_processName.setVisibility(View.GONE);
        }
        if (!groupedByDiag.isLastInstance()) {
            holder.tv_subTotal.setVisibility(View.GONE);
            holder.tv_price_subtotal.setVisibility(View.GONE);
        }

//        holder.tv_approvalCode.setText(groupedByDiag.getApprovalNo());
//        holder.tv_approvalCode.setVisibility(groupedByDiag.getStatus().equals("PENDING")? View.GONE: View.VISIBLE);
        holder.tv_status.setText(groupedByDiag.getApprovalNo());
        holder.tv_status.setVisibility(groupedByDiag.getStatus().equals("PENDING") ? View.GONE : View.VISIBLE);

        holder.tv_costcenter.setText(groupedByDiag.getCostCenter());
        holder.tv_price_subtotal.setText("P " + groupedByDiag.getSubTotal());

    }

    @Override
    public int getItemCount() {
        return arrayListGroupedByDiag.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_processName)
        TextView tv_processName;
        @BindView(R.id.tv_cost_center)
        TextView tv_cost_center;
        @BindView(R.id.tv_procedure_name)
        TextView tv_procedure_name;
        @BindView(R.id.tv_costcenter)
        TextView tv_costcenter;
        @BindView(R.id.lv_procedure)
        ListView lv_procedure;
        @BindView(R.id.lv_price)
        ListView lv_price;
        @BindView(R.id.tv_status)
        TextView tv_status;
        @BindView(R.id.tv_price_subtotal)
        TextView tv_price_subtotal;
        @BindView(R.id.tv_subTotal)
        TextView tv_subTotal;

        //        @BindView(R.id.tv_approvalCode)
//        TextView tv_approvalCode;
//        @BindView(R.id.lv_status)
//        ListView lv_status;
        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static boolean setListViewHeightBasedOnItems(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter != null) {

            int numberOfItems = listAdapter.getCount();

            // Get total height of all items.
            int totalItemsHeight = 0;
            for (int itemPos = 0; itemPos < numberOfItems; itemPos++) {
                View item = listAdapter.getView(itemPos, null, listView);
                float px = 500 * (listView.getResources().getDisplayMetrics().density);
                item.measure(View.MeasureSpec.makeMeasureSpec((int) px, View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
                totalItemsHeight += item.getMeasuredHeight();
            }

            // Get total height of all item dividers.
            int totalDividersHeight = listView.getDividerHeight() *
                    (numberOfItems - 1);
            // Get padding
            int totalPadding = listView.getPaddingTop() + listView.getPaddingBottom();

            // Set list height.
            ViewGroup.LayoutParams params = listView.getLayoutParams();
            params.height = totalItemsHeight + totalDividersHeight + totalPadding;
            listView.setLayoutParams(params);
            listView.requestLayout();
            return true;

        } else {
            return false;
        }

    }
}
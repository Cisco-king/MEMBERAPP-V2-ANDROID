package adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.TextView;

import com.medicard.member.R;

import net.cachapa.expandablelayout.ExpandableLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.MaceFAQs;

/**
 * Created by IPC on 11/22/2017.
 */

public class FAQsAdapter extends RecyclerView.Adapter<FAQsAdapter.ViewHolder> {
    private static final int UNSELECTED = -1;

    private RecyclerView recyclerView;
    private int selectedItem = UNSELECTED;
    Context context;
    ArrayList<MaceFAQs> maceFAQ;

    public FAQsAdapter(RecyclerView recyclerView, Context context, ArrayList<MaceFAQs> maceFAQ) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.maceFAQ = maceFAQ;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.mace_faqs_layout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return maceFAQ.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
        @BindView(R.id.expandable_layout)
        ExpandableLayout expandable_layout;
        @BindView(R.id.tv_index)
        TextView tv_index;
        @BindView(R.id.tv_content)
        TextView tv_content;
        private int position;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            expandable_layout.setInterpolator(new OvershootInterpolator());
            expandable_layout.setOnExpansionUpdateListener(this);
            tv_index.setOnClickListener(this);
        }

        public void bind(int position) {
            this.position = position;
            tv_index.setText(maceFAQ.get(position).getIndex() + " " + maceFAQ.get(position).getHeader());
            tv_index.setSelected(false);
            expandable_layout.collapse(false);
            tv_content.setText(maceFAQ.get(position).getBody());
        }

        @Override
        public void onClick(View view) {
            ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
            if (holder != null) {
                holder.tv_index.setSelected(false);
//                holder.tv_index.setTextColor(context.getColor(R.color.BLACK));
                holder.expandable_layout.collapse();
            }
            if (position == selectedItem) {
                selectedItem = UNSELECTED;
            } else {
                tv_index.setSelected(true);
//                tv_index.setTextColor(context.getColor(R.color.white));
                expandable_layout.expand();
                selectedItem = position;
            }
        }
        @Override
        public void onExpansionUpdate(float expansionFraction, int state) {
            recyclerView.smoothScrollToPosition(getAdapterPosition());
        }
    }
}

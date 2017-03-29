package adapter;

import android.content.Context;
import com.medicard.member.R;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import model.SpecsAdapter;

/**
 * Created by mpx-pawpaw on 1/26/17.
 */

public class SpecializationAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private int TYPE_SELECTED = 0;
    private int TYPE_NOT_SELECTED = 1;

    private Context context;
    private ArrayList<SpecsAdapter> array = new ArrayList<>();
     private ArrayList<SpecsAdapter> selected = new ArrayList<>();


    public SpecializationAdapter(Context context, ArrayList<SpecsAdapter> array, ArrayList<SpecsAdapter> selected ) {
        this.context = context;
        this.array = array;

        this.selected = selected;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        if (viewType == TYPE_SELECTED) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.row_province_selected, parent, false);
            return new  HolderSelected(itemView);
        } else if (viewType == TYPE_NOT_SELECTED) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.row_province, parent, false);
            return new  Holder(itemView);
        }


        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");
        // return new Holder(LayoutInflater.from(context).inflate(R.layout.row_province, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int pos = position;
        if (holder instanceof Holder) {
            final  Holder Holder = ( Holder) holder;
            Holder.name.setText(array.get(position).getSpecializationDescription().trim());
            Log.d("PROViNCE", array.get(position).getSpecializationDescription().trim() + "//");


            Holder.ll_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setSelected(pos);
                }
            });
        } else if (holder instanceof HolderSelected) {
            final  HolderSelected HolderSelected = ( HolderSelected) holder;
            HolderSelected.name.setText(array.get(position).getSpecializationDescription().trim());
            Log.d("PROViNCE", array.get(position).getSpecializationDescription().trim() + "//");

            HolderSelected.ll_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    setDeSelected(pos);
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        return array.size();
    }

    @Override
    public int getItemViewType(int position) {


        if (array.get(position).getSelected().equals("true"))
            return TYPE_SELECTED;
        else
            return TYPE_NOT_SELECTED;


    }


    public class HolderSelected extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView name;

        @BindView(R.id.ll_bg)
        LinearLayout ll_bg;

        public HolderSelected(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView name;

        @BindView(R.id.ll_bg)
        LinearLayout ll_bg;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


    private void setSelected(int position) {
        array.get(position).setSelected("true");
        notifyDataSetChanged();
        Log.d("sel", array.get(position).getSelected().trim() + "//");
        Log.d("sel", position + "//");
        selected.add(array.get(position));

    }

    private void setDeSelected(int position) {
        array.get(position).setSelected("false");
        notifyDataSetChanged();
        Log.d("desel", array.get(position).getSelected().trim() + "//");
        Log.d("desel", selected.indexOf(array.get(position).getSpecializationCode()) + "");


        for (int x = 0; x < selected.size(); x++) {
            if (selected.get(x).getSpecializationCode().equals(array.get(position).getSpecializationCode())) {
                Log.d("sel", selected.get(x).getSpecializationDescription() + "");
                selected.remove(x);
            }
        }

    }
}

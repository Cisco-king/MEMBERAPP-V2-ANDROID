package adapter;

import android.content.Context;
import com.medicard.member.R;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.HospitalList;
import model.LoaFetch;
import model.Province;
import model.SimpleData;

/**
 * Created by mpx-pawpaw on 2/20/17.
 */

public class LoaReqAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private int TYPE_SELECTED = 0;
    private int TYPE_NOT_SELECTED = 1;


    private Context context;
     private ArrayList<SimpleData> arrayList;
    private List<SimpleData> simpleDatas;


    public LoaReqAdapter(Context context, ArrayList<SimpleData> arrayList ) {
        this.context = context;
        this.arrayList = arrayList;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_SELECTED) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.row_province_selected, parent, false);
            return new HolderSelected(itemView);
        } else if (viewType == TYPE_NOT_SELECTED) {
            View itemView = LayoutInflater.from(context).inflate(R.layout.row_province, parent, false);
            return new Holder(itemView);
        }


        throw new RuntimeException("there is no type that matches the type " + viewType + " + make sure your using types correctly");

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final int pos = position;
        if (holder instanceof Holder) {
            final Holder Holder = (Holder) holder;
            Holder.name.setText(arrayList.get(position).getHospital());
            Log.d("PROViNCE", arrayList.get(position) + "//");


            Holder.ll_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectData(pos);
                }
            });


        } else if (holder instanceof HolderSelected) {
            final HolderSelected HolderSelected = (HolderSelected) holder;
            HolderSelected.name.setText(arrayList.get(position).getHospital().trim());
            Log.d("PROViNCE", arrayList.get(position).getHospital().trim() + "//");


            HolderSelected.ll_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deselect(pos);
                }
            });
        }
    }

    private void deselect(int pos) {

        arrayList.get(pos).setSelected("false");
        notifyDataSetChanged();
    }

    private void selectData(int pos) {
        arrayList.get(pos).setSelected("true");
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    @Override
    public int getItemViewType(int position) {


        if (arrayList.get(position).getSelected().equals("true"))
            return TYPE_SELECTED;
        else
            return TYPE_NOT_SELECTED;


    }

    public void filterList(ArrayList<SimpleData> list) {
        arrayList = list;
        notifyDataSetChanged();
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

}

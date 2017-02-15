package adapter;

import android.content.Context;
import android.medicard.com.medicard.R;
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
import model.Provinces;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class ProvinceAdapter extends RecyclerView.Adapter<ProvinceAdapter.Holder> {
    Context context;
    ArrayList<Provinces> array = new ArrayList<>();
    ProvinceInterface callback;

    public ProvinceAdapter(Context context, ArrayList<Provinces> array, ProvinceInterface callback) {
        this.context = context;
        this.array = array;
        this.callback = callback;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_province, parent, false);
        return new Holder(v);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.name.setText(array.get(position).getProvinceName().trim());
        Log.d("PROViNCE", array.get(position).getProvinceName().trim() + "//");


        holder.ll_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.onCLickItemListenerProvince(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
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


    public interface ProvinceInterface {

        void onCLickItemListenerProvince(int position);


    }
}

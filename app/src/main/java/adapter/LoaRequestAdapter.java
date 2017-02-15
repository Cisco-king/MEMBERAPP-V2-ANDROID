package adapter;

import android.content.Context;
import android.medicard.com.medicard.R;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Loa;
import model.LoaRequest;

/**
 * Created by mpx-pawpaw on 1/18/17.
 */

public class LoaRequestAdapter extends RecyclerView.Adapter<LoaRequestAdapter.Holder> {


    private Context context;
    private ArrayList<Loa> arrayList;

    public LoaRequestAdapter(Context context, ArrayList<Loa> arrayList) {
        this.arrayList = arrayList;
        this.context = context;

    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.row_loa_request, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, final int position) {

        holder.tv_address.setText(arrayList.get(0).getLoaList().get(position).get);
        holder.tv_doctor.setText(arrayList.get(position).getDate());
        holder.tv_remark.setText(arrayList.get(position).getStatus());
        holder.tv_req_date.setText(arrayList.get(position).getStatus());
        holder.tv_room.setText(arrayList.get(position).getStatus());
        holder.tv_sched.setText(arrayList.get(position).getStatus());
        holder.tv_spec.setText(arrayList.get(position).getStatus());
        holder.tv_status.setText(arrayList.get(position).getStatus());


        holder.gotoRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LOAD_REQ", "CLICKED" + position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_req_date)
        TextView tv_req_date;

        @BindView(R.id.tv_status)
        TextView tv_status;

        @BindView(R.id.tv_remark)
        TextView tv_remark;

        @BindView(R.id.tv_address)
        TextView tv_address;

        @BindView(R.id.tv_doctor)
        TextView tv_doctor;

        @BindView(R.id.tv_spec)
        TextView tv_spec;

        @BindView(R.id.tv_room)
        TextView tv_room;

        @BindView(R.id.tv_sched)
        TextView tv_sched;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}

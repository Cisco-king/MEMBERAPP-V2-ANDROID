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
import model.LoaRequest;

/**
 * Created by mpx-pawpaw on 1/18/17.
 */

public class LoaRequestAdapter extends RecyclerView.Adapter<LoaRequestAdapter.Holder> {


    private Context context;
    private ArrayList<LoaRequest> arrayList;

    public LoaRequestAdapter(Context context, ArrayList<LoaRequest> arrayList) {
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

        holder.name.setText(arrayList.get(position).getName());
        holder.date.setText(arrayList.get(position).getDate());
        holder.status.setText(arrayList.get(position).getStatus());


        holder.gotoRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("LOAD_REQ" ,"CLICKED" + position );
            }
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_date)
        TextView date;
        @BindView(R.id.tv_status)
        TextView status;
        @BindView(R.id.imageButton)
        ImageButton gotoRequest;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this ,itemView) ;
        }
    }
}

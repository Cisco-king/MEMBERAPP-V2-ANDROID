package adapter;

import android.content.Context;
import android.medicard.com.medicard.R;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import model.GetDoctorsToHospital;
import services.OnClicklistener;
import utilities.SetUnfilledField;

/**
 * Created by mpx-pawpaw on 12/29/16.
 */

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {

    private Context context;
    private ArrayList<GetDoctorsToHospital> array = new ArrayList<>();
    private OnClicklistener clicklistener;

    public DoctorAdapter(Context context, ArrayList<GetDoctorsToHospital> array) {
        this.context = context;
        this.array = array;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_doctor, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(array.get(position).getDocLname() + ", " + array.get(position).getDocFname());
        holder.position.setText(array.get(position).getSpecDesc());
        holder.tv_sched.setText("Schedule: " + SetUnfilledField.setData(array.get(position).getSchedule()));
        holder.tv_room.setText("Room: " + SetUnfilledField.setData(array.get(position).getRoom()));
    }



    public void setClickListener(OnClicklistener itemClickListener) {
        this.clicklistener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, position, tv_sched, tv_room, tv_spec;
        CardView cv_item;

        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            position = (TextView) itemView.findViewById(R.id.tv_spec);
            tv_sched = (TextView) itemView.findViewById(R.id.tv_sched);
            tv_room = (TextView) itemView.findViewById(R.id.tv_room);
            cv_item = (CardView) itemView.findViewById(R.id.cv_item);

            cv_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clicklistener != null)
                        clicklistener.onClickListener(getAdapterPosition());
                }
            });
        }

    }
}

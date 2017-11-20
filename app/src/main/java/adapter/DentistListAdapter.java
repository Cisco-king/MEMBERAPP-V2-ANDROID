package adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;

import InterfaceService.FragmentApiDentistCallback;
import Sqlite.DatabaseHandler;
import model.DentistList;
import utilities.SetUnfilledField;

/**
 * Created by IPC on 11/17/2017.
 */

public class DentistListAdapter extends RecyclerView.Adapter<DentistListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DentistList> array = new ArrayList<>();
    FragmentApiDentistCallback callback;
    DatabaseHandler databaseHandler;
    public DentistListAdapter(Context context, ArrayList<DentistList> array, FragmentApiDentistCallback callback,DatabaseHandler databaseHandler) {
        this.context = context;
        this.array = array;
        this.callback = callback;
        this.databaseHandler = databaseHandler;
    }

    @Override
    public DentistListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_doctor, parent, false);
        return new DentistListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DentistListAdapter.ViewHolder holder, int position) {
        holder.name.setText(array.get(position).getLastName() + ", " + array.get(position).getFirstName() + " " + array.get(position).getMiddleName());
        holder.position.setText("Contact No: " + SetUnfilledField.setData(array.get(position).getContactNo()));
        holder.tv_sched.setText("Schedule: " + SetUnfilledField.setData(array.get(position).getSchedule()));
        holder.tv_room.setText("Clinic: " + SetUnfilledField.setData(array.get(position).getClinic()));
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
                    callback.onDentistSelect(array, getAdapterPosition());
                }
            });
        }

    }
}
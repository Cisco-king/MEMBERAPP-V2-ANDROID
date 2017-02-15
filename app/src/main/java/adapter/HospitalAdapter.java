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

import model.HospitalList;
import services.OnClicklistener;
import utilities.SetUnfilledField;

/**
 * Created by mpx-pawpaw on 12/29/16.
 */

public class HospitalAdapter extends RecyclerView.Adapter<HospitalAdapter.ViewHolder> {

    private Context context;
    private ArrayList<HospitalList> array = new ArrayList<>();
    private OnClicklistener clicklistener;

    public HospitalAdapter(Context context, ArrayList<HospitalList> array) {
        this.context = context;
        this.array = array;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_hospital, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.name.setText(array.get(position).getHospitalName());
        holder.tv_address.setText(array.get(position).getStreetAddress());
        holder.contact.setText("Tel. No: " + SetUnfilledField.setData(array.get(position).getPhoneNo()));
        holder.person.setText("Contact Person: " + SetUnfilledField.setData(array.get(position).getContactPerson()));
        holder.time.setText("Clinic Hours: " + SetUnfilledField.setData(""));
    }


    public void setClickListener(OnClicklistener itemClickListener) {
        this.clicklistener = itemClickListener;
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, tv_address, time, person, contact;
        CardView cv_item;

        public ViewHolder(View itemView) {
            super(itemView);
            contact = (TextView) itemView.findViewById(R.id.tv_contact_no);
            name = (TextView) itemView.findViewById(R.id.tv_name);
            time = (TextView) itemView.findViewById(R.id.tv_hours);
            person = (TextView) itemView.findViewById(R.id.tv_contact_person);
            tv_address = (TextView) itemView.findViewById(R.id.tv_address);
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

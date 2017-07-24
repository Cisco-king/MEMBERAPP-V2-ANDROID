package modules.hospital.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import core.callback.RecyclerViewOnClickListener;
import model.HospitalList;
import utilities.SetUnfilledField;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public class HospitalDoctorAdapter extends
        RecyclerView.Adapter<HospitalDoctorAdapter.ViewHolder> {


    private Context context;
    private List<HospitalList> hospitals;

    private LayoutInflater inflater;

    private RecyclerViewOnClickListener listener;

    public HospitalDoctorAdapter(Context context, List<HospitalList> hospitals, RecyclerViewOnClickListener listener) {
        this.context = context;
        this.hospitals = hospitals;

        inflater = LayoutInflater.from(context);

        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_hospital, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        HospitalList hospital = hospitals.get(position);
        String hospitalName = (hospital.getHospitalName() != null) ? hospital.getHospitalName() : "";
        holder.name.setText(hospitalName);
        holder.tv_address.setText(getStringName(hospital.getStreetAddress()) + ", " +
                getStringName(hospital.getCity()) + ", " +
                getStringName(hospital.getProvince()) + ", " +
                getStringName(hospital.getRegion()));
        holder.contact.setText("Tel. No: " + hospital.getPhoneNo());
    }

    private String getStringName(String getData) {
        String data = setNull(getData);


        return data.trim();
    }

    public String setNull(String getData) {
        String data = " ";

        try {
            if (!getData.equals("null"))
                data = getData + " letmeout ";
        } catch (Exception e) {

        }


        String regex2 = "\\s*\\bletmeout\\b\\s*";
        data = data.replaceAll(regex2, "");
        Log.d("RETURN_withProvider", data);
        return data;
    }

    public void update(List<HospitalList> hospitals) {
        this.hospitals = hospitals;
        notifyDataSetChanged();
    }

    public HospitalList getHospital(int position) {
        return hospitals.get(position);
    }

    @Override
    public int getItemCount() {
        return hospitals.size();
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
                    if (listener != null)
                        listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

}

package adapter;

import android.content.Context;

import com.medicard.member.R;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);

    }

    public HospitalAdapter(Context context, ArrayList<HospitalList> array) {
        this.setHasStableIds(false);
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
        HospitalList hospitalModel = array.get(position);
        holder.name.setText(hospitalModel.getHospitalName());
        holder.tv_address.setText(getStringName(hospitalModel.getStreetAddress()) + ", " +
                getStringName(hospitalModel.getCity()) + ", " +
                getStringName(hospitalModel.getProvince()) + ", " +
                getStringName(hospitalModel.getRegion()));
        try {
            if (hospitalModel.getPhoneNo().isEmpty())
                holder.contact.setText("NO CONTACT NUMBER");
            else
                holder.contact.setText("Tel. No: " + hospitalModel.getPhoneNo());

            if (null == hospitalModel.getContactPerson() || (hospitalModel.getContactPerson().isEmpty() && hospitalModel.getContactPerson().length() == 0)) {
                holder.person.setVisibility(View.GONE);
            } else {
                holder.person.setText("Contact Person: " + hospitalModel.getContactPerson());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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

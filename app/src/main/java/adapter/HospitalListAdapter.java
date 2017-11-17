package adapter;

import android.content.Context;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;

import InterfaceService.FragmentApiHospCallback;
import butterknife.BindView;
import butterknife.ButterKnife;
import model.HospitalList;

/**
 * Created by IPC on 11/17/2017.
 */

public class HospitalListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<HospitalList> array;
    private Context context;
    FragmentApiHospCallback callback;


    public HospitalListAdapter(Context context, ArrayList<HospitalList> array, FragmentApiHospCallback callback) {
        this.setHasStableIds(false);
        this.context = context;
        this.callback = callback;
        this.array = array;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(hasStableIds);

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.row_hospital, parent, false);
        return new Holder(itemView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder hold, final int position) {
        HospitalList hospitalModel = array.get(position);
        if (hold instanceof Holder) {
            final Holder holder = (Holder) hold;
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


    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return array.size();
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

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.tv_address)
        TextView tv_address;

        @BindView(R.id.tv_hours)
        TextView time;

        @BindView(R.id.tv_contact_person)
        TextView person;

        @BindView(R.id.tv_contact_no)
        TextView contact;

        @BindView(R.id.cv_item)
        CardView cv_item;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            cv_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onHospitalSelect(array, getAdapterPosition());
                }
            });
        }
    }

}
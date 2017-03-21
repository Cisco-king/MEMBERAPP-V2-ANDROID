package InterfaceService;

import android.content.Context;
import com.medicard.member.R;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import Sqlite.DatabaseHandler;
import adapter.HospitalAdapter;
import model.CitiesAdapter;
import model.HospitalList;

/**
 * Created by mpx-pawpaw on 1/26/17.
 */

public class HospitalListRetrieve {

    Context context;
    DatabaseHandler handler;

    public HospitalListRetrieve(Context context, DatabaseHandler databaseHandler) {
        this.context = context;
        handler = databaseHandler;
    }

    public void updateList(String provinceName, String sortBy, ArrayList<CitiesAdapter> selectedCity, HospitalAdapter hospitalAdapter, ArrayList<HospitalList> array, String s) {

        String data_sort = "";
        if (sortBy.equals(context.getString(R.string.hospital_clinic_name))) {
            data_sort = "hospitalName";
        } else if (sortBy.equals(context.getString(R.string.medicard_first))) {
            data_sort = "category";
        } else if (sortBy.equals(context.getString(R.string.province))) {
            data_sort = "province";
        } else if (sortBy.equals(context.getString(R.string.city))) {
            data_sort = "city";
        }else{
            data_sort = "hospitalName";
        }

        array.clear();
        array.addAll(handler.retrieveHospital(provinceName, data_sort, selectedCity , s));
        hospitalAdapter.notifyDataSetChanged();


    }

    public void updateListUI(ArrayList<HospitalList> array, RecyclerView rv_hospital, TextView tv_hosp_not_found) {


        if (array.size() > 0) {
            rv_hospital.setVisibility(View.VISIBLE);
            tv_hosp_not_found.setVisibility(View.GONE);
        } else {
            rv_hospital.setVisibility(View.GONE);
            tv_hosp_not_found.setVisibility(View.VISIBLE);
        }
    }
}

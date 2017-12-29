package InterfaceService;

import android.app.ProgressDialog;
import android.content.Context;

import com.medicard.member.R;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import Sqlite.DatabaseHandler;
import adapter.HospitalAdapter;
import adapter.HospitalListAdapter;
import model.CitiesAdapter;
import model.HospitalList;
import model.Provinces;
import model.ProvincesAdapter;
import modules.hospital.adapter.HospitalDoctorAdapter;

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

    public void updateList(String isMedicardOnly, ArrayList<ProvincesAdapter> selectedProvince, String sortBy, ArrayList<CitiesAdapter> selectedCity, HospitalAdapter hospitalAdapter, ArrayList<HospitalList> array, String s) {

        String data_sort = "";
        if (sortBy.equals(context.getString(R.string.hospital_clinic_name))) {
            data_sort = "hospitalName";
        } else if (sortBy.equals(context.getString(R.string.medicard_first))) {
            data_sort = "category";
        } else if (sortBy.equals(context.getString(R.string.province))) {
            data_sort = "province";
        } else if (sortBy.equals(context.getString(R.string.city))) {
            data_sort = "city";
        } else if (sortBy.equals("")) {
            data_sort = "category";
        }

        array.clear();

        //ONLY MEDICARD
        if (isMedicardOnly.equals("true")) {
            if (data_sort.equals("") || data_sort.equals("category")) {
                data_sort = "hospitalName";
            }
            array.addAll(handler.getOnlyMedicardClinics(selectedProvince, data_sort, selectedCity, isMedicardOnly, s));
        } else
            array.addAll(handler.retrieveHospital(isMedicardOnly, selectedProvince, data_sort, selectedCity, s));
        hospitalAdapter.notifyDataSetChanged();
    }

    //used for hospital list in nav
    public void updateHospitalList(String isMedicardOnly, ArrayList<ProvincesAdapter> selectedProvince, String sortBy, ArrayList<CitiesAdapter> selectedCity, ArrayList<HospitalList> array, String s) {

        String data_sort = "";
        if (sortBy.equals(context.getString(R.string.hospital_clinic_name))) {
            data_sort = "hospitalName";
        } else if (sortBy.equals(context.getString(R.string.medicard_first))) {
            data_sort = "category";
        } else if (sortBy.equals(context.getString(R.string.province))) {
            data_sort = "province";
        } else if (sortBy.equals(context.getString(R.string.city))) {
            data_sort = "city";
        } else if (sortBy.equals("")) {
            data_sort = "category";
        }

        array.clear();
        //ONLY MEDICARD
        if (isMedicardOnly.equals("true")) {
            if (data_sort.equals("") || data_sort.equals("category")) {
                data_sort = "hospitalName";
            }
            array.addAll(handler.getOnlyMedicardClinics(selectedProvince, data_sort, selectedCity, isMedicardOnly, s));
        } else
            array.addAll(handler.retrieveHospital(isMedicardOnly, selectedProvince, data_sort, selectedCity, s));
    }

    public void updateListForTest(String isMedicardOnly, ArrayList<ProvincesAdapter> selectedProvince, String sortBy, ArrayList<CitiesAdapter> selectedCity, HospitalDoctorAdapter hospitalAdapter, List<HospitalList> array, String s) {

        String data_sort = "";
        if (sortBy.equals(context.getString(R.string.hospital_clinic_name))) {
            data_sort = "hospitalName";
        } else if (sortBy.equals(context.getString(R.string.medicard_first))) {
            data_sort = "category";
        } else if (sortBy.equals(context.getString(R.string.province))) {
            data_sort = "province";
        } else if (sortBy.equals(context.getString(R.string.city))) {
            data_sort = "city";
        } else if (sortBy.equals("")) {
            data_sort = "category";
        }

        array.clear();
        //ONLY MEDICARD
        if (isMedicardOnly.equals("true")) {
            if (data_sort.equals("") || data_sort.equals("category")) {
                data_sort = "hospitalName";
            }
            array.addAll(handler.getOnlyMedicardClinics(selectedProvince, data_sort, selectedCity, isMedicardOnly, s));
        } else
            array.addAll(handler.retrieveHospital(isMedicardOnly, selectedProvince, data_sort, selectedCity, s));
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
    public void updateListUIForTest(List<HospitalList> array, RecyclerView rv_hospital) {
        if (array.size() > 0) {
            rv_hospital.setVisibility(View.VISIBLE);

        } else {
            rv_hospital.setVisibility(View.GONE);
        }
    }
}

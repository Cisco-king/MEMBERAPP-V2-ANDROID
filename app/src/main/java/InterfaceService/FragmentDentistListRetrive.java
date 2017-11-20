package InterfaceService;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;

import Sqlite.DatabaseHandler;
import model.CitiesAdapter;
import model.DentistList;
import model.DentistModel;
import model.HospitalList;
import model.ProvincesAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import utilities.NetworkTest;

/**
 * Created by IPC on 11/17/2017.
 */

public class FragmentDentistListRetrive {

    Context context;
    FragmentApiDentistCallback callback;
    DatabaseHandler databaseHandler;

    public FragmentDentistListRetrive(Context context, FragmentApiDentistCallback callback, DatabaseHandler databaseHandler) {
        this.context = context;
        this.callback = callback;
        this.databaseHandler = databaseHandler;
    }

    public void getDentistConnection() {
        if (NetworkTest.isOnline(context)) {
            callback.internetConnected();
        } else
            callback.noInternetConnection();
    }

    public void getDentistList() {
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getDentist()
                .enqueue(new Callback<DentistModel>() {
                    @Override
                    public void onResponse(Call<DentistModel> call, Response<DentistModel> response) {
                        try {
                            if (response.body() != null) {
                                callback.onSuccess(response.body().getDentistList());
                            } else {
                                callback.onError("no response to server");
                            }
                        } catch (Exception e) {
                            callback.onError("");
                        }
                    }

                    @Override
                    public void onFailure(Call<DentistModel> call, Throwable t) {
                        try {
                            callback.onError(t.getMessage());
                        } catch (Exception e) {
                            callback.onError("");
                        }
                    }
                });
    }

    public void dropDentist() {
        databaseHandler.dropDentist();
    }

    public void updateDentistList(final ArrayList<DentistList> doctors) {
        AsyncTask updateDoc = new AsyncTask() {
            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callback.onSuccessDataInsert(doctors);
            }

            @Override
            protected Object doInBackground(Object[] params) {
                databaseHandler.setDentistsDataToDatabase(doctors);
                return null;
            }
        };
        updateDoc.execute();
    }


    public void updateDentistList(String isMedicardOnly, ArrayList<ProvincesAdapter> selectedProvince, String sortBy, ArrayList<CitiesAdapter> selectedCity, ArrayList<DentistList> array, String s) {

        String data_sort = "";
        if (sortBy.equals(context.getString(R.string.hospital_clinic_name))) {
            data_sort = "clinic";
        } else if (sortBy.equals(context.getString(R.string.medicard_first))) {
            data_sort = "category";
        } else if (sortBy.equals(context.getString(R.string.province))) {
            data_sort = "provinceCode";
        } else if (sortBy.equals(context.getString(R.string.city))) {
            data_sort = "cityCode";
        } else if (sortBy.equals("")) {
            data_sort = "clinic";
        }

        array.clear();
        //ONLY MEDICARD
        if (isMedicardOnly.equals("true")) {
            if (data_sort.equals("") || data_sort.equals("category")) {
                data_sort = "hospitalName";
            }
//            array.addAll(databaseHandler.getOnlyMedicardClinics(selectedProvince, data_sort, selectedCity, isMedicardOnly, s));
            array.addAll(databaseHandler.getOnlyMedicardClinics_dentist(selectedProvince, data_sort, selectedCity, isMedicardOnly, s));
        }
//        else
//            array.addAll(databaseHandler.retrieveHospital(isMedicardOnly, selectedProvince, data_sort, selectedCity, s));
            array.addAll(databaseHandler.retrieveDentist(isMedicardOnly, selectedProvince, data_sort, selectedCity, s));
    }

    public void updateListUI(ArrayList<DentistList> array, RecyclerView rv_doctor, TextView tv_doc_not_found) {

        if (array.size() > 0) {
            rv_doctor.setVisibility(View.VISIBLE);
            tv_doc_not_found.setVisibility(View.GONE);
        } else {
            rv_doctor.setVisibility(View.GONE);
            tv_doc_not_found.setVisibility(View.VISIBLE);
        }

    }
}

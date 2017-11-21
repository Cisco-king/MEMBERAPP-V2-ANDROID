package InterfaceService;

import android.content.Context;

import com.medicard.member.R;

import android.widget.EditText;

import Sqlite.DatabaseHandler;
import model.Doctors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import utilities.NetworkTest;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */

public class DoctorRetrieve {

    Context context;
    DoctorInterface callback;
    DatabaseHandler databaseHandler;

    public DoctorRetrieve(Context context, DoctorInterface callback, DatabaseHandler databaseHandler) {
        this.callback = callback;
        this.context = context;
        this.databaseHandler = databaseHandler;
    }

    public void getDoctors(String hospCode) {

        if (NetworkTest.isOnline(context)) {
            callback.internetConnected(hospCode);
        } else
            callback.noInternetConnection();

    }

    public void getDoctorList(String hospCode) {
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getDoctors(hospCode,"1900-01-01")
                .enqueue(new Callback<Doctors>() {
                    @Override
                    public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                        try {
                            if (response.body() != null) {
                                callback.onDoctorSuccess(response.body());
                            } else {
                                callback.onDoctorError("no response to server");
                            }
                        } catch (Exception e) {
                            callback.onDoctorError("");
                        }
                    }
                    @Override
                    public void onFailure(Call<Doctors> call, Throwable t) {
                        try {
                            callback.onDoctorError(t.getMessage());
                        } catch (Exception e) {
                            callback.onDoctorError("");
                        }
                    }
                });

    }


    public void dropDoctors() {

        databaseHandler.dropDoctor();
    }

    public void setSearchStringtoUI(String search_string, EditText ed_searchDoctor) {
        ed_searchDoctor.setText(search_string);
    }

    public String testSort(String sort_by) {

        if (sort_by.equals(context.getString(R.string.doctor_family))) {
            return "docLname";
        } else if (sort_by.equals(context.getString(R.string.room_number))) {
            return "room";
        } else if (sort_by.equals(context.getString(R.string.specialization))) {
            return "specDesc";
        } else {
            return "docLname";
        }
    }


}

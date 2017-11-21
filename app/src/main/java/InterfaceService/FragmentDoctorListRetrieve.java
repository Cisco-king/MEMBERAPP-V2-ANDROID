package InterfaceService;

import android.content.Context;
import android.util.Log;
import android.widget.EditText;

import com.medicard.member.R;

import Sqlite.DatabaseHandler;
import Sqlite.SetDoctorToDatabase;
import model.Doctors;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.AlertDialogCustom;
import utilities.ErrorMessage;
import utilities.NetworkTest;

/**
 * Created by IPC on 11/17/2017.
 */

public class FragmentDoctorListRetrieve {

    Context context;
    FragmentApiDocCallback callback;
    DatabaseHandler databaseHandler;

    public FragmentDoctorListRetrieve(Context context, FragmentApiDocCallback callback, DatabaseHandler databaseHandler) {
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
                                callback.onFragDoctorSuccess(response.body());
                            } else {
                                callback.onFragDoctorError("no response to server");
                            }
                        } catch (Exception e) {
                            callback.onFragDoctorError("");
                        }
                    }

                    @Override
                    public void onFailure(Call<Doctors> call, Throwable t) {
                        try {
                            callback.onFragDoctorError(t.getMessage());
                        } catch (Exception e) {
                            callback.onFragDoctorError("");
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

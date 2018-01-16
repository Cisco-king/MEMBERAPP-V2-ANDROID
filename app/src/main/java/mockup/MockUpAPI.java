package mockup;

import java.util.ArrayList;

import model.Doctor;
import model.Doctors;
import model.GetDoctorsToHospital;
import model.LogIn;
import model.SignInDetails;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;

/**
 * Created by IPC on 1/16/2018.
 */

public class MockUpAPI {

    public static void getFirstLoad(String hospitalCode, String count, String offset, String searchString, final MockUpDocImplement.MockUpCallback callback) {
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getDoctorsToHospitalPaginated(hospitalCode, count, offset, searchString)
                .enqueue(new Callback<Doctors>() {
                    @Override
                    public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                        if (response.body() != null) {
                            callback.onSuccessFirstLoad(response.body());
                        } else {
                            callback.onError("no response to server");
                        }
                    }

                    @Override
                    public void onFailure(Call<Doctors> call, Throwable e) {
                        try {
                            callback.onError(e.getMessage());
                        } catch (Exception error) {
                            callback.onError("");
                        }

                    }
                });

    }


    public static void getDoctorsToHospitalPaginated(String hospitalCode, String count, String offset, String searchString, final MockUpDocImplement.MockUpCallback callback) {
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getDoctorsToHospitalPaginated(hospitalCode, count, offset, searchString)
                .enqueue(new Callback<Doctors>() {
                    @Override
                    public void onResponse(Call<Doctors> call, Response<Doctors> response) {
                        if (response.body() != null) {
                            callback.onSuccessLoadMore(response.body());
                        } else {
                            callback.onError("no response to server");
                        }
                    }

                    @Override
                    public void onFailure(Call<Doctors> call, Throwable e) {
                        try {
                            callback.onError(e.getMessage());
                        } catch (Exception error) {
                            callback.onError("");
                        }

                    }
                });

    }

}

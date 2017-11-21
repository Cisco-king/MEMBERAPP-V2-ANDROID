package InterfaceService;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Sqlite.DatabaseHandler;
import Sqlite.SetHospitalToDatabase;
import database.dao.ProcedureDao;
import model.City;
import model.Hospital;
import model.Province;
import model.SignInDetails;
import model.SpecializationList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.App;
import services.AppInterface;
import services.AppService;
import services.client.ProcedureClient;
import services.model.Procedure;
import services.response.ProcedureResponse;
import timber.log.Timber;
import utilities.SharedPref;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class SignInRetrieve {
    Context context;
    SignInCallback callback;

    public SignInRetrieve(Context context, SignInCallback callback) {
        this.context = context;
        this.callback = callback;
    }


    public void getProvince() {

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getProvinces()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Province>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        try {
                            callback.onErrorProvince(e.getMessage());

                        } catch (Exception error) {
                            callback.onErrorProvince(e.getMessage());

                            Log.e("Rx_ERROR", error.getMessage());
                        }

                    }

                    @Override
                    public void onNext(Province province) {
                        callback.onSuccessProvince(province);
                    }
                });


    }

    public void getCity() {

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getCity()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<City>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        try {
                            callback.onErrorCity(e.getMessage());
                        } catch (Exception error) {
                            callback.onErrorCity(e.getMessage());

                            Log.e("Rx_ERROR", error.getMessage());

                        }


                    }

                    @Override
                    public void onNext(City city) {
                        callback.onSuccessCity(city);
                    }
                });


    }


    public void getSpecialization() {

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getSpecialization()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<SpecializationList>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        try {
                            callback.onErrorSpecs(e.getMessage());
                        } catch (Exception error) {
                            callback.onErrorSpecs(e.getMessage());

                            Log.e("Rx_ERROR", error.getMessage());

                        }


                    }

                    @Override
                    public void onNext(SpecializationList specializationList) {
                        callback.onSuccessSpecs(specializationList);
                    }
                });
    }

    public void loadProcedures(final SignInDetails signInDetails) {

        final ProcedureClient procedureClient = AppService.createApiService(ProcedureClient.class, AppInterface.ENDPOINT);
        procedureClient.getAllProceduresRx()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ProcedureResponse>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("load procedure completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d("error message %s", e.toString());
                        callback.onLoadProcedureError(e.toString());
                    }

                    @Override
                    public void onNext(ProcedureResponse procedureResponse) {
                        saveAllToDataabse(signInDetails, procedureResponse);
                    }
                });
    }


    public void saveAllToDataabse(final SignInDetails signInDetails, final ProcedureResponse procedureResponse) {
        Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                ProcedureDao procedureDao = new ProcedureDao(context);

                List<Procedure> procedures = procedureResponse.getProceduresList();

                procedureDao.deleteAll();

                Boolean success = procedureDao.insertAllProcedues(procedures);

                subscriber.onNext(success);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Boolean>() {
                    @Override
                    public void onCompleted() {
                        Timber.d("doctor load complete");
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onLoadProcedureError(e.toString());
                        Timber.d("something happen while inseting all doctor to database %s", e.toString());
                    }

                    @Override
                    public void onNext(Boolean success) {
                        if (success == Boolean.TRUE) {
                            Timber.d("all doctor data is inserted");
                        } else {
                            Timber.d("kindly check the log in DoctorDao for more information");
                        }

                        callback.onLoadProcedureSuccess(signInDetails);
                    }

                });
    }

    public void getHospitalList(final SignInDetails responseBody, final String username, final String password) {
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getHospital("1900-01-01")
                .enqueue(new Callback<Hospital>() {
                    @Override
                    public void onResponse(Call<Hospital> call, Response<Hospital> response) {
                        try {
                            if (response.body() != null) {
                                setToDatabase(response.body(),responseBody);
                            } else {
                                callback.onHospitalError("no response to server");
                            }
                        } catch (Exception e) {
                            callback.onHospitalError("");
                        }
                    }

                    @Override
                    public void onFailure(Call<Hospital> call, Throwable t) {
                        try {
                            callback.onHospitalError(t.getMessage());
                        } catch (Exception e) {
                            callback.onHospitalError("");
                        }
                    }
                });

    }

    private void setToDatabase(final Hospital hospital, final SignInDetails responseBody) {
        final DatabaseHandler databaseHandler = new DatabaseHandler(context);
        AsyncTask setHosp = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                databaseHandler.dropHospital();
                SetHospitalToDatabase.setHospToDb(hospital.getHospitalList(), databaseHandler);
                return null;
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callback.onHospitalSuccess();


            }

        };

        setHosp.execute();
    }


}

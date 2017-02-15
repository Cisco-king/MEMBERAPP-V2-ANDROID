package InterfaceService;

import android.content.Context;
import android.util.Log;

import model.City;
import model.Province;
import model.SpecializationList;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.App;
import services.AppInterface;
import services.AppService;

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
}

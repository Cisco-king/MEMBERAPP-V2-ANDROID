package InterfaceService;

import android.content.Context;

import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.App;
import services.AppInterface;
import services.AppService;
import utilities.AlertDialogCustom;

/**
 * Created by mpx-pawpaw on 3/6/17.
 */

public class LoaPageRetieve {

    private Context context;
    LoaPageInterface callback;

    public LoaPageRetieve(Context context, LoaPageInterface callback) {
        this.context = context;
        this.callback = callback;
    }


    public void cancelRequest(String approvalNo) {


        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.setRequestCancel(approvalNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        if (e.getMessage().contains("failed to connect to"))
                            callback.noInternet();
                        else
                            callback.onError(e.getMessage()) ;


                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                            callback.onSuccess();
                    }
                });

    }

}

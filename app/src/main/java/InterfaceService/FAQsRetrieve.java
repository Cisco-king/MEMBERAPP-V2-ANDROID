package InterfaceService;

import android.content.Context;

import Sqlite.DatabaseHandler;
import model.Doctors;
import model.FAQsModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;

/**
 * Created by IPC on 11/22/2017.
 */

public class FAQsRetrieve {

    Context context;
    FAQsCallback callback;
    DatabaseHandler db;

    public FAQsRetrieve(Context context, FAQsCallback callback, DatabaseHandler db) {
        this.context = context;
        this.callback = callback;
        this.db = db;
    }

    public void getMaceFAQs() {
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getMaceFAQs()
                .enqueue(new Callback<FAQsModel>() {
                    @Override
                    public void onResponse(Call<FAQsModel> call, Response<FAQsModel> response) {
                        try {
                            if (response.body() != null) {
                                callback.onSuccess(response.body());
                            } else {
                                callback.onError("no response to server");
                            }
                        } catch (Exception e) {
                            callback.onError("");
                        }
                    }
                    @Override
                    public void onFailure(Call<FAQsModel> call, Throwable t) {
                        try {
                            callback.onError(t.getMessage());
                        } catch (Exception e) {
                            callback.onError("");
                        }
                    }
                });
    }
}

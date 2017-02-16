package InterfaceService;

import android.content.Context;
import android.medicard.com.medicard.R;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

import java.util.ArrayList;

import Sqlite.DatabaseHandler;
import Sqlite.SetLoaToDatabase;
import model.Loa;
import model.LoaList;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.SharedPref;

/**
 * Created by mpx-pawpaw on 1/18/17.
 */

public class LoaRequestRetrieve {

    Context context;
    LOARequestCallback callback;

    public LoaRequestRetrieve(Context context, LOARequestCallback callback) {
        this.context = context;
        this.callback = callback;
    }

    public void changeButtonColorSelected(Button btn) {

        btn.setBackgroundColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
        btn.setTextColor(ContextCompat.getColor(context, R.color.white));
    }

    public void changeButtonColorDeselect(Button btn) {
        btn.setBackgroundColor(ContextCompat.getColor(context, R.color.white));
        btn.setTextColor(ContextCompat.getColor(context, R.color.colorPrimaryLight));
    }

    public void getLoa(String memberCode) {

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getLoaList(memberCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Loa>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onErrorLoaListener(e.getMessage());
                    }

                    @Override
                    public void onNext(Loa loa) {
                        callback.onSuccessLoaListener(loa);
                    }
                });
    }

    public void getData(Loa loa, DatabaseHandler databaseHandler) {
        SetLoaToDatabase.setLoaToDb(loa, databaseHandler, callback);
    }

//    public void testDataDownLoadRequirement(ArrayList<LoaList> arrayListfromDB, DatabaseHandler databaseHandler) {
//
//
//        if (arrayListfromDB.size() == 0) {
//            getLoa(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context));
//        } else {
//            ArrayList<LoaList> newFetch = new ArrayList<>();
//            newFetch.addAll(getLoaOnly(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context)));
//            if (arrayListfromDB.size() == newFetch.size()) {
//                if (testIDNotSame(arrayListfromDB, newFetch)) {
//                    arrayListfromDB.clear();
//                    arrayListfromDB.addAll(newFetch);
//                }
//            }
//        }
//    }

//    private boolean testIDNotSame(ArrayList<LoaList> arrayListfromDB, ArrayList<LoaList> newFetch) {
//        boolean isNotSame = false;
//
//        for (int db = 0; db < arrayListfromDB.size(); db++) {
//            for (int fetch = 0; fetch < newFetch.size(); fetch++) {
//                if (!arrayListfromDB.get(db).getId().equals(newFetch.get(db).getId())) {
//                    isNotSame = true;
//                    break;
//                }
//            }
//        }
//
//        return boolean;
//    }


    /**
     * UPDATE WITH NO INTERNET CONNECTION ASAP
     *
     * @param memberCode
     * @return
     */
//    public ArrayList<LoaList> getLoaOnly(String memberCode) {
//        final ArrayList<LoaList> array = new ArrayList<>();
//
//
//        AppInterface appInterface;
//        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
//        appInterface.getLoaList(memberCode)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .unsubscribeOn(Schedulers.io())
//                .subscribe(new Subscriber<Loa>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        getLoaOnly(SharedPref.getStringValue(SharedPref.USER, SharedPref.MEMBERCODE, context));
//                    }
//
//                    @Override
//                    public void onNext(Loa loa) {
//                        array.addAll(loa.getLoaList());
//                    }
//                });
//
//        return array;
//    }
}

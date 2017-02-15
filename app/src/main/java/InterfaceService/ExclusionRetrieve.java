package InterfaceService;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.medicard.com.medicard.R;
import android.os.AsyncTask;
import android.util.Log;

import Sqlite.DatabaseHandler;
import model.Exclusions;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import utilities.AlertDialogCustom;
import v2.HospitalListAcitivity;

/**
 * Created by mpx-pawpaw on 1/5/17.
 */

public class ExclusionRetrieve {


    public static void getHospInPatient(final Context context, final DatabaseHandler databaseHandler, final String memberId, HospitalListAcitivity hospitalListAcitivity) {
        final ApiHospCallback apiHospCallback;
        final AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
        apiHospCallback = hospitalListAcitivity;
        final ProgressDialog pd;
        pd = new ProgressDialog(context, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Loading Hospitals...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pd.show();


        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getInpatientExclusions(memberId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Exclusions>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        try {

                            Log.e("EXCLUSIONS", e.getMessage());
                            pd.dismiss();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);

                        }catch (Exception error){
                            pd.dismiss();
                             alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);


                            Log.e("Rx_ERROR" , error.getMessage());
                        }

                    }

                    @Override
                    public void onNext(Exclusions exclusions) {

                        getHospOutPatient(context, alertDialogCustom, memberId, apiHospCallback, pd, databaseHandler, exclusions);
                    }
                });

    }

    private static void getHospOutPatient(final Context context, final AlertDialogCustom alertDialogCustom, final String memberId, final ApiHospCallback apiHospCallback, final ProgressDialog pd, final DatabaseHandler databaseHandler, final Exclusions exclusionsInPatient) {


        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getOutpatientExclusions(memberId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<Exclusions>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {


                        try {

                            Log.e("EXCLUSIONS", e.getMessage());
                            pd.dismiss();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);

                        }catch (Exception error){
                            pd.dismiss();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);


                            Log.e("Rx_ERROR" , error.getMessage());
                        }

                    }

                    @Override
                    public void onNext(Exclusions exclusions) {
                        setExcludedHosp(apiHospCallback, pd, databaseHandler, exclusionsInPatient , exclusions);
                    }
                });



    }

    private static void setExcludedHosp(final ApiHospCallback apiHospCallback, final ProgressDialog pd, final DatabaseHandler databaseHandler, final Exclusions exclusions, final Exclusions exclusions1) {

        final String[] excluded = exclusions.getExclusions();
        final String[] excluded1 = exclusions1.getExclusions();

        AsyncTask setExclusion = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {

                for (int x = 0; x < exclusions.getExclusions().length; x++) {

                    databaseHandler.setExcludedToTrue(excluded[x]);
                    Log.d("EXCLUSIONS", excluded[x]);

                }


                for (int x = 0; x < exclusions1.getExclusions().length; x++) {

                    databaseHandler.setExcludedToTrue(excluded1[x]);
                    Log.d("EXCLUSIONS1", excluded1[x]);

                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                apiHospCallback.onSuccess();
                pd.dismiss();
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        };


        setExclusion.execute();


    }


}

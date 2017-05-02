package InterfaceService;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.medicard.member.R;

import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import Sqlite.DatabaseHandler;
import Sqlite.SetLoaToDatabase;
import Sqlite.UpdateDoctorCode;
import adapter.LoaRequestAdapter;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Doctor;
import model.DoctorNORoom;
import model.DoctorsToHospital;
import model.Loa;
import model.LoaFetch;
import model.SimpleData;
import model.TheDoctor;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.AppInterface;
import services.AppService;
import timber.log.Timber;

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

    //show textview if no list available
    public void updateUIList(RecyclerView list, TextView tv_list, ArrayList<LoaFetch> arrayList) {

        if (arrayList.size() == 0) {
            list.setVisibility(View.GONE);
            tv_list.setVisibility(View.VISIBLE);
        }else{
            list.setVisibility(View.VISIBLE);
            tv_list.setVisibility(View.GONE);
        }
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

    public void getDoctorCreds(final ArrayList<LoaFetch> arrayList, final DatabaseHandler databaseHandler) {

        AsyncTask asyncTask = new AsyncTask() {


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callback.doneFetchingDoctorData();


            }

            @Override
            protected Object doInBackground(Object[] objects) {
                for (int x = 0; x < arrayList.size(); x++) {
                    fetchDoctor(arrayList.get(x).getDoctorCode(), x, arrayList, databaseHandler);

                }

                return null;
            }
        };


        asyncTask.execute();
    }

    private void fetchDoctor(final String doctorCode, final int position, final ArrayList<LoaFetch> arrayList, final DatabaseHandler databaseHandler) {
        Log.d("DOCTOR_CODE", doctorCode);
        Log.d("DOCTOR_CODE", doctorCode);


        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {


                AppInterface appInterface;
                appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
                appInterface.getDoctorData(doctorCode)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .unsubscribeOn(Schedulers.io())
                        .subscribe(new Subscriber<DoctorNORoom>() {
                            @Override
                            public void onCompleted() {

                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.d("DOCTOR_CODE", e.getMessage());

                                if (e.getMessage().contains("Expected BEGIN_OBJECT but was STRING")) {
                                    doctorNotFound(doctorCode, arrayList, position, databaseHandler);
                                } else {
                                    callback.onErrorFetchingDoctorCreds(e.getMessage());
                                }


                            }

                            @Override
                            public void onNext(DoctorNORoom doctorNORoom) {

                                if (doctorNORoom.getResponseCode().equals("210")) {
                                    doctorNotFound(doctorCode, arrayList, position, databaseHandler);
                                } else {
                                    //     if (.getDoctorsToHospital().size() == 0)
                                    //        doctorNotFound(doctorCode, arrayList, position, dbHandler);
                                    //      else
                                    onSuccessListener(databaseHandler, doctorNORoom.getDoctor(), arrayList, position);
                                }
                            }
                        });


                return null;
            }
        };


        asyncTask.execute();

    }


    public void onSuccessListener(DatabaseHandler databaseHandler, Doctor theDoctor, ArrayList<LoaFetch> arrayList, int position) {
        Log.d("DOCTOR_CODE_COUNT", position + "");

        arrayList.get(position).setDoctorName(theDoctor.getDocFname() + " " +
                theDoctor.getDocLname());
        arrayList.get(position).setDoctorSpec(theDoctor.getSpecDesc());
        arrayList.get(position).setDoctorSpecCode(theDoctor.getSpecCode());


        databaseHandler.setDoctorToLoaReq(
                arrayList.get(position).getId(),
                theDoctor.getDocFname() + " " + theDoctor.getDocLname(),
                theDoctor.getSpecDesc(),
                theDoctor.getSpecCode(), "", "");
        // theDoctor.getSchedule(),
        // theDoctor.getRoom());

    }


    public void doctorNotFound(String doctorCode, ArrayList<LoaFetch> arrayList, int position, DatabaseHandler databaseHandler) {

        arrayList.get(position).setDoctorName(doctorCode);
        arrayList.get(position).setDoctorSpec("Not Specified");
        arrayList.get(position).setDoctorSpecCode("Not Specified");

        databaseHandler.setDoctorToLoaReq(
                arrayList.get(position).getId(),
                doctorCode,
                "Not Specified",
                "Not Specified",
                "Not Specified",
                "Not Specified");
    }

    public void updateHospitals(final ArrayList<LoaFetch> arrayList, final DatabaseHandler databaseHandler) {
        AsyncTask asyncTask = new AsyncTask() {
            @Nullable
            @Override
            protected Object doInBackground(Object[] objects) {
                for (int x = 0; x < arrayList.size(); x++) {
                    String hospName = databaseHandler.getHospitalName(arrayList.get(x).getHospitalCode());
                    Timber.d("%s", hospName);
                    databaseHandler.setHospitalToLoaReq(arrayList.get(x).getId(), hospName);
                }
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callback.doneUpdatingHosp();
            }
        };
        asyncTask.execute();


    }

    public void updateList(ArrayList<LoaFetch> arrayList,
                           DatabaseHandler databaseHandler, String sort_by, String status_sort,
                           String service_type_sort, String date_start_sort, String date_end_sort,
                           ArrayList<SimpleData> doctor_sort, ArrayList<SimpleData> hospital_sort, String seachedData) {
        arrayList.clear();
        arrayList.addAll(databaseHandler.retrieveLoa(dateSortUpdate(sort_by), status_sort, service_type_sort,
                date_start_sort, date_end_sort, doctor_sort, hospital_sort, seachedData));

    }

    private String dateSortUpdate(String sort_by) {
        String returnData = "";
        if (sort_by.equals(context.getString(R.string.status))) {
            returnData = "status";
        } else if (sort_by.equals(context.getString(R.string.request_date))) {
            // returnData = "DATETIME(dateAdmitted)";
            returnData = "approvalNo";
        } else if (sort_by.equals(context.getString(R.string.hospital_clinic))) {
            returnData = "hospitalName";
        } else if (sort_by.equals(context.getString(R.string.service_type))) {
            returnData = "remarks";
        } else if (sort_by.equals("")) {
            returnData = "status";
        }
        return returnData;
    }

    public void replactDataArray(ArrayList<SimpleData> masterList, ArrayList<SimpleData> temp) {
        masterList.clear();
        masterList.addAll(temp);
        temp.clear();
    }

    public void UIUpdateShowLoad(boolean b, ProgressBar pb, RecyclerView rv_loa_request, FancyButton btn_sort) {

        if (b) {
            pb.setVisibility(View.VISIBLE);
            rv_loa_request.setVisibility(View.GONE);
            btn_sort.setVisibility(View.GONE);
        } else {
            pb.setVisibility(View.GONE);
            rv_loa_request.setVisibility(View.VISIBLE);
            btn_sort.setVisibility(View.VISIBLE);
        }
    }




//    public void testDataDownLoadRequirement(ArrayList<LoaList> arrayListfromDB, DatabaseHandler dbHandler) {
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

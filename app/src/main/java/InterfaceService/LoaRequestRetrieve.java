package InterfaceService;

import android.content.Context;
import android.medicard.com.medicard.R;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Button;

import java.util.ArrayList;

import Sqlite.DatabaseHandler;
import Sqlite.SetLoaToDatabase;
import adapter.LoaRequestAdapter;
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

        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getDoctorDataWithRoom(doctorCode)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<TheDoctor>() {
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
                    public void onNext(TheDoctor theDoctor) {

                        if (theDoctor.getResponseCode().equals("210")) {
                            doctorNotFound(doctorCode, arrayList, position, databaseHandler);
                        } else {
                            if (theDoctor.getDoctorsToHospital().size() == 0)
                                doctorNotFound(doctorCode, arrayList, position, databaseHandler);
                            else
                                onSuccessListener(databaseHandler, theDoctor.getDoctorsToHospital().get(0), arrayList, position);
                        }
                    }
                });

    }


    public void onSuccessListener(DatabaseHandler databaseHandler, DoctorsToHospital theDoctor, ArrayList<LoaFetch> arrayList, int position) {
        Log.d("DOCTOR_CODE_COUNT", position + "");

        arrayList.get(position).setDoctorName(theDoctor.getDocFname() + " " +
                theDoctor.getDocLname());
        arrayList.get(position).setDoctorSpec(theDoctor.getSpecDesc());
        arrayList.get(position).setDoctorSpecCode(theDoctor.getSpecCode());

        databaseHandler.setDoctorToLoaReq(
                arrayList.get(position).getId(),
                theDoctor.getDocFname() + " " + theDoctor.getDocLname(),
                theDoctor.getSpecDesc(),
                theDoctor.getSpecCode(),
                theDoctor.getSchedule(),
                theDoctor.getRoom());
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


        for (int x = 0; x < arrayList.size(); x++) {
            String hospName = databaseHandler.getHospitalName(arrayList.get(x).getHospitalCode());
            databaseHandler.setHospitalToLoaReq(arrayList.get(x).getId(), hospName);
        }

        callback.doneUpdatingHosp();
//        AsyncTask asyncTask = new AsyncTask() {
//            @Override
//            protected Object doInBackground(Object[] objects) {
//                for (int x = 0; x < arrayList.size(); x++) {
//                    String hospName = databaseHandler.getHospitalName(arrayList.get(x).getHospitalCode());
//                    databaseHandler.setHospitalToLoaReq(arrayList.get(x).getId(), hospName);
//                }
//                return null;
//            }
//
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(Object o) {
//                super.onPostExecute(o);
//                callback.doneUpdatingHosp();
//            }
//        };
//
//        asyncTask.execute();
    }

    public void updateList(ArrayList<LoaFetch> arrayList, LoaRequestAdapter adapter,
                           DatabaseHandler databaseHandler, String sort_by, String status_sort,
                           String service_type_sort, String date_start_sort, String date_end_sort,
                           ArrayList<SimpleData> doctor_sort, ArrayList<SimpleData> hospital_sort) {


        arrayList.clear();


        arrayList.addAll(databaseHandler.retrieveLoa(dateSortUpdate(sort_by), status_sort, service_type_sort,
                date_start_sort, date_end_sort, doctor_sort, hospital_sort));
        adapter.notifyDataSetChanged();
    }

    private String dateSortUpdate(String sort_by) {
        String returnData = "";
        if (sort_by.equals(context.getString(R.string.doctor_family))) {
            returnData = "docName";
        } else if (sort_by.equals(context.getString(R.string.room))) {
            returnData = "room";
        } else if (sort_by.equals(context.getString(R.string.specialization))) {
            returnData = "docSpec";
        } else if (sort_by.equals("")){
            returnData = "docName";
        }
        return returnData;
    }

    public void replactDataArray(ArrayList<SimpleData> masterList, ArrayList<SimpleData> temp) {
        masterList.addAll(temp);
        temp.clear();
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

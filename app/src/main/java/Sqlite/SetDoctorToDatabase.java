package Sqlite;

import android.os.AsyncTask;
import android.util.Log;

import java.util.ArrayList;

import InterfaceService.DoctorInterface;
import InterfaceService.FragmentApiDocCallback;
import model.Doctors;
import model.GetDoctorsToHospital;

/**
 * Created by mpx-pawpaw on 1/4/17.
 */

public class SetDoctorToDatabase {

    public static void insertToDb(final DatabaseHandler databaseHandler, final Doctors doctors, final DoctorInterface callback) {
        Log.e("DOCTOR", doctors.getGetDoctorsToHospital().size() + "");
        AsyncTask insertion = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                SetDoctorToDatabase.setDocToDb(doctors.getGetDoctorsToHospital(), databaseHandler);
                return null;
            }


            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
//                callback.onDoctorSuccess(doctors);

            }


            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        };


        insertion.execute();
    }

    public static void setDocToDb(ArrayList<GetDoctorsToHospital> doc, DatabaseHandler databaseH) {
        for (int x = 0; x < doc.size(); x++) {
            databaseH.insertDoctorList(new GetDoctorsToHospital(
                            doc.get(x).getRegion(),
                            doc.get(x).getStreetAddress(),
                            doc.get(x).getSpecialRem(),
                            doc.get(x).getDocFname(),
                            doc.get(x).getSpecDesc(),
                            doc.get(x).getHospRemarks(),
                            doc.get(x).getDoctorCode(),
                            doc.get(x).getDocMname(),
                            doc.get(x).getVat(),
                            doc.get(x).getWtax(),
                            doc.get(x).getRemarks(),
                            doc.get(x).getCity(),
                            doc.get(x).getGracePeriod(),
                            doc.get(x).getPhoneNo(),
                            doc.get(x).getSpecCode(),
                            doc.get(x).getSchedule(),
                            doc.get(x).getFaxno(),
                            doc.get(x).getProvince(),
                            doc.get(x).getHospitalName(),
                            doc.get(x).getDocLname(),
                            doc.get(x).getHospitalCode(),
                            doc.get(x).getContactPerson(),
                            doc.get(x).getRoomBoard(),
                            doc.get(x).getRemarks2(),
                            doc.get(x).getRoom()
                    )
            );

        }

    }

    //used for nav doctor list
    public static void insertToDb(final DatabaseHandler databaseHandler, final Doctors doctors, final FragmentApiDocCallback callback) {
        Log.e("DOCTOR", doctors.getGetDoctorsToHospital().size() + "");
        AsyncTask insertion = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                SetDoctorToDatabase.setDocToDb(doctors.getGetDoctorsToHospital(), databaseHandler);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callback.onSuccess(doctors.getGetDoctorsToHospital());
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }
        };

        insertion.execute();
    }


}

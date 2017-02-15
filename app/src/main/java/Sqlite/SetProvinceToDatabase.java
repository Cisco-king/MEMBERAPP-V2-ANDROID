package Sqlite;

import android.os.AsyncTask;

import java.util.ArrayList;

import InterfaceService.SignInCallback;

import model.Provinces;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class SetProvinceToDatabase {

    public static void setProvince(final ArrayList<Provinces> provinces, final DatabaseHandler databaseHandler, final SignInCallback callback) {


        AsyncTask asyncTask = new AsyncTask() {


            @Override
            protected void onPostExecute(Object o) {
                callback.onProvincetoDbListener();
                super.onPostExecute(o);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] params) {
                setProvincetoDb(provinces , databaseHandler);
                return null;
            }
        };


        asyncTask.execute();


    }


    private static void setProvincetoDb(ArrayList<Provinces> provinces , final DatabaseHandler databaseHandler) {


        for (int x = 0 ; x < provinces.size() ; x++){
            databaseHandler.insertProvince(new Provinces(
                    provinces.get(x).getRegionCode() ,
                    provinces.get(x).getProvinceCode() ,
                    provinces.get(x).getProvinceName()));
        }
    }

}

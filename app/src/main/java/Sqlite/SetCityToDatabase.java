package Sqlite;

import android.os.AsyncTask;

import java.util.ArrayList;

import InterfaceService.SignInCallback;
import model.Cities;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class SetCityToDatabase {


    public static void setCity(final ArrayList<Cities> cities, final DatabaseHandler databaseHandler, final SignInCallback callback) {
        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected void onPostExecute(Object o) {
                callback.onCitytoDbListener();
                super.onPostExecute(o);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] params) {
                setCitytoDb(cities, databaseHandler);
                return null;
            }
        };


        asyncTask.execute();

    }

    private static void setCitytoDb(ArrayList<Cities> cities, DatabaseHandler databaseHandler) {


        for (int x = 0; x < cities.size(); x++) {


            databaseHandler.insertCity(new Cities(
                    cities.get(x).getRegionCode(),
                    cities.get(x).getCountryName(),
                    cities.get(x).getProvinceCode(),
                    cities.get(x).getCityName(),
                    cities.get(x).getCountryCode(),
                    cities.get(x).getCityCode()
            ));


        }


    }
}

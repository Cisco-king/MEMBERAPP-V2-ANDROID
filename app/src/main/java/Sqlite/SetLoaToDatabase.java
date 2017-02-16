package Sqlite;

import android.os.AsyncTask;

import java.util.ArrayList;

import InterfaceService.LOARequestCallback;
import model.Loa;
import model.LoaList;

/**
 * Created by mpx-pawpaw on 2/16/17.
 */

public class SetLoaToDatabase {


    public static void setLoaToDb(final Loa loa, final DatabaseHandler databaseHandler, final LOARequestCallback callback) {

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {

                setToDB(loa.getLoaList(), databaseHandler);

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callback.onDbLoaSuccessListener();
            }
        };


        asyncTask.execute();

    }

    private static void setToDB(ArrayList<LoaList> loa, DatabaseHandler databaseHandler) {


        for (int x = 0; x < loa.size(); x++) {
            databaseHandler.insertLoa(loa.get(x));
        }

    }
}

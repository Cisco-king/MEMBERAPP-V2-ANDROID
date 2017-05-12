package Sqlite;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import InterfaceService.LOARequestCallback;
import model.Loa;
import model.LoaList;
import services.response.LoaListResponse;
import timber.log.Timber;

/**
 * Created by mpx-pawpaw on 2/16/17.
 */

public class SetLoaToDatabase {


    public static void setLoaToDb(final LoaListResponse loa, final DatabaseHandler databaseHandler, final LOARequestCallback callback) {

        AsyncTask asyncTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {

                setToDB(loa, databaseHandler);

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                callback.onDbLoaSuccessListener(loa);
            }
        };


        asyncTask.execute();

    }

    private static void setToDB(LoaListResponse loa, DatabaseHandler databaseHandler) {


        for (int x = 0; x < loa.getLoaList().size(); x++) {
            Timber.d("loa list to db %s", loa.getLoaList().get(x).getStatus());
            databaseHandler.insertLoa(loa.getLoaList().get(x));
        }

    }
}

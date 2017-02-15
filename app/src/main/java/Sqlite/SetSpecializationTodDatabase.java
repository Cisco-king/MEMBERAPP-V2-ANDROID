package Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.os.AsyncTask;

import java.util.ArrayList;

import InterfaceService.SignInCallback;
import model.SpecializationList;
import model.Specializations;

/**
 * Created by mpx-pawpaw on 1/30/17.
 */
public class SetSpecializationTodDatabase {


    public static void setSpecs(final ArrayList<Specializations> specializationList, Context context, final SignInCallback callback) {

        final DatabaseHandler databaseHandler = new DatabaseHandler(context);

        AsyncTask asyncTask = new AsyncTask() {

            @Override
            protected void onPostExecute(Object o) {
                callback.onSpecsToDBListener();
                super.onPostExecute(o);
            }

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
            }

            @Override
            protected Object doInBackground(Object[] objects) {

           setSpecs(specializationList , databaseHandler);

                return null;
            }
        };

        asyncTask.execute();

    }

    private static void setSpecs(ArrayList<Specializations> specializationList, DatabaseHandler databaseHandler) {



        for (int x = 0 ; x < specializationList.size() ; x++){

       databaseHandler.insertSpecialization(new Specializations(
               specializationList.get(x).getSpecializationCode() ,
               specializationList.get(x).getSpecializationDescription()));

        }




    }
}

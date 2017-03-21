package utilities;

import android.app.ProgressDialog;
import android.content.Context;
import com.medicard.member.R;
import android.os.AsyncTask;

import Sqlite.DatabaseHandler;

/**
 * Created by mpx-pawpaw on 1/5/17.
 */

public class ResetDatabase {


    public static void resetDB(Context context, final DatabaseHandler databaseHandler) {
        final ProgressDialog pd;
        pd = new ProgressDialog(context, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Loading Hospitals...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
        pd.show();


        AsyncTask setFalse = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] params) {
                databaseHandler.getAlltoFalse();

                return null;
            }


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

            }

            @Override
            protected void onPostExecute(Object o) {
                super.onPostExecute(o);
                pd.dismiss();
            }
        };


        setFalse.execute();

    }
}

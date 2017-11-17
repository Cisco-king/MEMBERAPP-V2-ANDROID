package InterfaceService;

import android.content.Context;

import Sqlite.DatabaseHandler;
import utilities.NetworkTest;

/**
 * Created by IPC on 11/17/2017.
 */

public class FragmentDentistListRetrive {

    Context context;
    FragmentApiDentistCallback callback;
    DatabaseHandler databaseHandler;

    public FragmentDentistListRetrive(Context context, FragmentApiDentistCallback callback, DatabaseHandler databaseHandler) {
        this.context = context;
        this.callback = callback;
        this.databaseHandler = databaseHandler;
    }

    public void getDentist() {
        if (NetworkTest.isOnline(context)) {
            callback.internetConnected();
        } else
            callback.noInternetConnection();
    }

    public void getDentistList() {
        if (NetworkTest.isOnline(context)) {
            callback.internetConnected();
        } else
            callback.noInternetConnection();
    }

    public void dropDentist() {
        databaseHandler.dropDentist();
    }
}

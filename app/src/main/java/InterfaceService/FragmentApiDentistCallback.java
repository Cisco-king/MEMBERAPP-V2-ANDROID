package InterfaceService;

import java.util.ArrayList;

import model.DentistList;

/**
 * Created by IPC on 11/17/2017.
 */

public interface FragmentApiDentistCallback {
    void onDentistSelect(ArrayList<DentistList> array, int position);

    void internetConnected();

    void noInternetConnection();

    void onError(String message);

    void onSuccess(DentistList doctors);
}

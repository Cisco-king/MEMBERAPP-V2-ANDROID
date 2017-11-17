package InterfaceService;

import java.util.ArrayList;

import model.HospitalList;

/**
 * Created by IPC on 11/16/2017.
 */

public interface FragmentApiHospCallback {

    void onError(String message);
    void onSuccess();
    void onHospitalSelect(ArrayList<HospitalList> array, int adapterPosition);
}

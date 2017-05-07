package InterfaceService;

import java.util.ArrayList;

import model.Loa;
import model.LoaFetch;
import services.response.LoaListResponse;

/**
 * Created by mpx-pawpaw on 1/17/17.
 */

public interface LOARequestCallback {


    void onErrorLoaListener(String message);

    void onSuccessLoaListener(LoaListResponse loa);

    void onDbLoaSuccessListener(LoaListResponse loa);

    void gotoLoaPage(ArrayList<LoaFetch> arrayList, int adapterPosition);

    void onErrorFetchingDoctorCreds(String message);

    void doneFetchingDoctorData();

    void doneUpdatingHosp();
}

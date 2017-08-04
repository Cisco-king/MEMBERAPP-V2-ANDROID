package InterfaceService;

import java.util.ArrayList;
import java.util.List;

import model.Loa;
import model.LoaFetch;
import services.model.MaceRequest;
import services.response.LoaListResponse;

/**
 * Created by mpx-pawpaw on 1/17/17.
 */

public interface LOARequestCallback {


    void onErrorLoaListener(String message);

    void onSuccessLoaListener(List<MaceRequest> maceRequestList);

    void onDbLoaSuccessListener(LoaListResponse loa);

    void gotoLoaPage(List<MaceRequest> arrayList, int adapterPosition);

    void onErrorFetchingDoctorCreds(String message);

    void doneFetchingDoctorData();

    void doneUpdatingHosp();
}

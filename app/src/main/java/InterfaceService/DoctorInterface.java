package InterfaceService;

import model.Doctors;

/**
 * Created by mpx-pawpaw on 1/31/17.
 */

public interface DoctorInterface {

    void onError(String message);
    void onSuccess(Doctors data);

    void noInternetConnection();

    void internetConnected(String hospCode);
}

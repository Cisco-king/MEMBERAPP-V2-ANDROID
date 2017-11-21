package InterfaceService;

import model.Doctors;

/**
 * Created by mpx-pawpaw on 1/31/17.
 */

public interface DoctorInterface {

    void onDoctorError(String message);

    void onDoctorSuccess(Doctors data);

    void noInternetConnection();

    void internetConnected(String hospCode);
}

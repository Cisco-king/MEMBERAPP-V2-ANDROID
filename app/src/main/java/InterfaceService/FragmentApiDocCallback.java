package InterfaceService;

import java.util.ArrayList;

import model.Doctors;
import model.GetDoctorsToHospital;

/**
 * Created by IPC on 11/17/2017.
 */

public interface FragmentApiDocCallback {

    void onDoctorSelect(ArrayList<GetDoctorsToHospital> array, int adapterPosition);

    void internetConnected(String hospCode);

    void noInternetConnection();

    void onFragDoctorError(String message);

    void onFragDoctorSuccess(Doctors doctors);

    void onSuccess(ArrayList<GetDoctorsToHospital> getDoctorsToHospital);
}

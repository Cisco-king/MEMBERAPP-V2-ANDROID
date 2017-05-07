package modules.doctor;

import java.util.List;

import database.entity.Doctor;
import modules.base.Mvp;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public interface DoctorMvp {

    interface View extends Mvp.View {

        void displayDoctors(List<Doctor> doctors);

        void displayFilteredDoctors(List<Doctor> doctors);

    }

    interface Presenter extends Mvp.Presenter<View> {

        void getAllDoctors();

        void filterDoctors(List<Doctor> doctors, String query);

    }

}

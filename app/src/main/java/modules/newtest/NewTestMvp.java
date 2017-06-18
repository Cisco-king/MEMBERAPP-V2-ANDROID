package modules.newtest;

import database.entity.Doctor;
import model.HospitalList;
import modules.base.Mvp;
import services.model.Diagnosis;
import services.model.HospitalsToDoctor;

/**
 * Created by casjohnpaul on 5/6/2017.
 */

public interface NewTestMvp {

    interface View extends Mvp.View {

        void displayDoctorView();

        void displayHospitalView(HospitalsToDoctor doctor);

        void displayDiagnosis(HospitalList hospitalList);

        void displayAllDiagnosisProcedure();

        void onStartDiagnosisProcedure(Diagnosis diagnosis);

    }

    interface Presenter extends Mvp.Presenter<View> {

    }

}

package modules.requestnewapproval;

import java.util.List;

import model.newtest.DiagnosisDetails;
import model.newtest.DiagnosisProcedure;
import modules.base.Mvp;
import services.model.HospitalsToDoctor;

/**
 * Created by casjohnpaul on 5/30/2017.
 */

public interface RequestNewMvp {

    interface View extends Mvp.View {

        void displayDoctorDetails(String doctorDetails);

        void displayDiagnosisDetails(List<DiagnosisDetails> diagnosisDetails);

    }

    interface Presenter extends Mvp.Presenter<RequestNewMvp.View> {

        void loadDoctorDetails(HospitalsToDoctor doctor);

        void loadDiagnosisTest(List<DiagnosisProcedure> diagnosisProcedures);

    }

}

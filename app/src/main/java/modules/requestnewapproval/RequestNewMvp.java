package modules.requestnewapproval;

import com.medicard.member.core.model.DiagnosisTests;

import java.util.List;

import model.Attachment;
import model.newtest.DiagnosisDetails;
import model.newtest.DiagnosisProcedure;
import model.newtest.NewTestRequest;
import modules.base.Mvp;
import services.model.DiagnosisTestRequest;
import services.model.HospitalsToDoctor;
import utilities.Loader;

/**
 * Created by casjohnpaul on 5/30/2017.
 */

public interface RequestNewMvp {

    interface View extends Mvp.View {

        void displayDoctorDetails(String doctorDetails);

        void displayDiagnosisDetails(List<DiagnosisDetails> diagnosisDetails);

        void displayDiagnosisTests(List<DiagnosisTests> diagnosisTests);

        void onRequestError(String message);

        void onRequestSuccess();

    }

    interface Presenter extends Mvp.Presenter<RequestNewMvp.View> {

        void loadDoctorDetails(HospitalsToDoctor doctor);

        void loadDiagnosisTests();

        void loadDiagnosisTest(List<DiagnosisProcedure> diagnosisProcedures);

        void submitNewRequest(NewTestRequest newTestRequest);

        void submitTestRequest(DiagnosisTestRequest request, List<Attachment> attachments);

    }

}

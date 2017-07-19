package utilities;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import model.newtest.DiagnosisProcedure;
import services.model.Diagnosis;

/**
 * Created by casjohnpaul on 6/3/2017.
 */

public class DiagnosisUtils {

    public static final String DIAGNOSIS = "DIAGNOSIS";

    public static final String TEST_PROCEDURES = "TEST_PROCEDURES";

    public static final List<String> getAllOriginalDiagnosisCode(List<DiagnosisProcedure> diagnosisProcedures) {
        Set<String> diagnosisCodes = new LinkedHashSet<>();
        for (DiagnosisProcedure diagnosisProcedure : diagnosisProcedures) {
            diagnosisCodes.add(diagnosisProcedure.getDiagnosisCode());
        }

        return new ArrayList<>(diagnosisCodes);
    }

    public static final List<Diagnosis> getAllDignosisByCode(List<Diagnosis> diagnosisList, List<String> diagnosisCodes) {
        Set<Diagnosis> setDiagnosis = new LinkedHashSet<>();
        for (String diagnosisCode : diagnosisCodes) {
            for (Diagnosis diagnosis : diagnosisList) {
                if (diagnosisCode.equals(diagnosis.getDiagCode())) {
                    setDiagnosis.add(diagnosis);
                    break;
                }
            }
        }

        return new ArrayList<>(setDiagnosis);
    }

    public static final void getAllProcedureByDiagnosis(List<DiagnosisProcedure> diagnosisProcedures, List<Diagnosis> diagnosisList) {

    }


}

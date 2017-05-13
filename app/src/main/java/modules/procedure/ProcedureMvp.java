package modules.procedure;

import java.util.List;

import modules.base.Mvp;
import services.model.Procedure;

/**
 * Created by casjohnpaul on 5/13/2017.
 */

public interface ProcedureMvp {

    interface View extends Mvp.View {

        void displayProcedureByCodeResult(List<Procedure> procedures);

    }

    interface Presenter extends Mvp.Presenter<ProcedureMvp.View> {

        void loadProcedureByDiagnosisCode(final String diagnosisCode);

    }

}

package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by casjohnpaul on 6/9/2017.
 */

public class MaceRequestData {

    @SerializedName("maceRequest")
    @Expose
    private MaceRequest maceRequest;
    @SerializedName("diagnosisProcedures")
    @Expose
    private List<DiagnosisProcedure> diagnosisProcedures = null;

    public MaceRequest getMaceRequest() {
        return maceRequest;
    }

    public void setMaceRequest(MaceRequest maceRequest) {
        this.maceRequest = maceRequest;
    }

    public List<DiagnosisProcedure> getDiagnosisProcedures() {
        return diagnosisProcedures;
    }

    public void setDiagnosisProcedures(List<DiagnosisProcedure> diagnosisProcedures) {
        this.diagnosisProcedures = diagnosisProcedures;
    }

}

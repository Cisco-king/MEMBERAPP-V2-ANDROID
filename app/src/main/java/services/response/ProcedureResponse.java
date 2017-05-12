package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import services.model.Procedure;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class ProcedureResponse {

    @SerializedName("proceduresList")
    @Expose
    private List<Procedure> proceduresList = null;

    public List<Procedure> getProceduresList() {
        return proceduresList;
    }

    public void setProceduresList(List<Procedure> proceduresList) {
        this.proceduresList = proceduresList;
    }

}

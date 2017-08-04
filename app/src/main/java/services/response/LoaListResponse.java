package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

import services.model.LoaList;
import services.model.MaceRequest;

/**
 * Created by Francisco F. Aquino III =)
 */

public class LoaListResponse implements Serializable {

    @SerializedName("loaList")
    @Expose
    private List<MaceRequest> loaList = null;

    public List<MaceRequest> getLoaList() {
        return loaList;
    }

    public void setLoaList(List<MaceRequest> loaList) {
        this.loaList = loaList;
    }

}

package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import services.model.LoaList;
import services.model.MaceRequest;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public class LoaListResponse {

    @SerializedName("loaList")
    @Expose
    private List<LoaList> loaList = null;

    public List<LoaList> getLoaList() {
        return loaList;
    }

    public void setLoaList(List<LoaList> loaList) {
        this.loaList = loaList;
    }

}

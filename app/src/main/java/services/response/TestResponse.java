package services.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import services.model.Test;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class TestResponse {

    @SerializedName("testsList")
    @Expose
    private List<Test> testsList = null;

    public List<Test> getTestsList() {
        return testsList;
    }

    public void setTestsList(List<Test> testsList) {
        this.testsList = testsList;
    }

}

package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 6/21/2017.
 */

public class DiagnosisTest {

    @SerializedName("diagnosisCode")
    @Expose
    private String diagnosisCode;
    @SerializedName("testCode")
    @Expose
    private String testCode;

    public DiagnosisTest(String diagnosisCode, String testCode) {
        this.diagnosisCode = diagnosisCode;
        this.testCode = testCode;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getTestCode() {
        return testCode;
    }

    public void setTestCode(String testCode) {
        this.testCode = testCode;
    }

}
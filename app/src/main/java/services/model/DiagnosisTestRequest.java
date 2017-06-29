package services.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by casjohnpaul on 6/21/2017.
 */

public class DiagnosisTestRequest {

    @SerializedName("consultationDate")
    @Expose
    private String consultationDate;
    @SerializedName("consultationReason")
    @Expose
    private String consultationReason;
    @SerializedName("diagnosisTests")
    @Expose
    private List<DiagnosisTest> diagnosisTests = null;
    @SerializedName("doctorCode")
    @Expose
    private String doctorCode;
    @SerializedName("hospitalCode")
    @Expose
    private String hospitalCode;
    @SerializedName("memberCode")
    @Expose
    private String memberCode;
    @SerializedName("primaryDiagnosisCode")
    @Expose
    private String primaryDiagnosisCode;
    @SerializedName("requestBy")
    @Expose
    private String requestBy;
    @SerializedName("requestDeviceId")
    @Expose
    private String requestDeviceId;
    @SerializedName("requestOrigin")
    @Expose
    private String requestOrigin;

    public DiagnosisTestRequest() {
    }

    private DiagnosisTestRequest(Builder builder) {
        setConsultationDate(builder.consultationDate);
        setConsultationReason(builder.consultationReason);
        setDiagnosisTests(builder.diagnosisTests);
        setDoctorCode(builder.doctorCode);
        setHospitalCode(builder.hospitalCode);
        setMemberCode(builder.memberCode);
        setPrimaryDiagnosisCode(builder.primaryDiagnosisCode);
        setRequestBy(builder.requestBy);
        setRequestDeviceId(builder.requestDeviceId);
        setRequestOrigin(builder.requestOrigin);
    }

    public String getConsultationDate() {
        return consultationDate;
    }

    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getConsultationReason() {
        return consultationReason;
    }

    public void setConsultationReason(String consultationReason) {
        this.consultationReason = consultationReason;
    }

    public List<DiagnosisTest> getDiagnosisTests() {
        return diagnosisTests;
    }

    public void setDiagnosisTests(List<DiagnosisTest> diagnosisTests) {
        this.diagnosisTests = diagnosisTests;
    }

    public String getDoctorCode() {
        return doctorCode;
    }

    public void setDoctorCode(String doctorCode) {
        this.doctorCode = doctorCode;
    }

    public String getHospitalCode() {
        return hospitalCode;
    }

    public void setHospitalCode(String hospitalCode) {
        this.hospitalCode = hospitalCode;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    public String getPrimaryDiagnosisCode() {
        return primaryDiagnosisCode;
    }

    public void setPrimaryDiagnosisCode(String primaryDiagnosisCode) {
        this.primaryDiagnosisCode = primaryDiagnosisCode;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    public String getRequestDeviceId() {
        return requestDeviceId;
    }

    public void setRequestDeviceId(String requestDeviceId) {
        this.requestDeviceId = requestDeviceId;
    }

    public String getRequestOrigin() {
        return requestOrigin;
    }

    public void setRequestOrigin(String requestOrigin) {
        this.requestOrigin = requestOrigin;
    }


    public static final class Builder {
        private String consultationDate;
        private String consultationReason;
        private List<DiagnosisTest> diagnosisTests;
        private String doctorCode;
        private String hospitalCode;
        private String memberCode;
        private String primaryDiagnosisCode;
        private String requestBy;
        private String requestDeviceId;
        private String requestOrigin;

        public Builder() {
        }

        public Builder consultationDate(String val) {
            consultationDate = val;
            return this;
        }

        public Builder consultationReason(String val) {
            consultationReason = val;
            return this;
        }

        public Builder diagnosisTests(List<DiagnosisTest> val) {
            diagnosisTests = val;
            return this;
        }

        public Builder doctorCode(String val) {
            doctorCode = val;
            return this;
        }

        public Builder hospitalCode(String val) {
            hospitalCode = val;
            return this;
        }

        public Builder memberCode(String val) {
            memberCode = val;
            return this;
        }

        public Builder primaryDiagnosisCode(String val) {
            primaryDiagnosisCode = val;
            return this;
        }

        public Builder requestBy(String val) {
            requestBy = val;
            return this;
        }

        public Builder requestDeviceId(String val) {
            requestDeviceId = val;
            return this;
        }

        public Builder requestOrigin(String val) {
            requestOrigin = val;
            return this;
        }

        public DiagnosisTestRequest build() {
            return new DiagnosisTestRequest(this);
        }
    }
}

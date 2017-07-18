package model.newtest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by casjohnpaul on 6/3/2017.
 */

public class DiagnosisProcedure {

    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("diagnosisCode")
    @Expose
    private String diagnosisCode;
    @SerializedName("procedureCode")
    @Expose
    private String procedureCode;
    @SerializedName("serviceType")
    @Expose
    private Integer serviceType;

    /**
     *
     * @param amount
     * @param diagnosisCode
     * @param procedureCode
     * @param serviceType
     */
    public DiagnosisProcedure(double amount, String diagnosisCode, String procedureCode, Integer serviceType) {
        this.amount = amount;
        this.diagnosisCode = diagnosisCode;
        this.procedureCode = procedureCode;
        this.serviceType = serviceType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getDiagnosisCode() {
        return diagnosisCode;
    }

    public void setDiagnosisCode(String diagnosisCode) {
        this.diagnosisCode = diagnosisCode;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiagnosisProcedure)) return false;

        DiagnosisProcedure that = (DiagnosisProcedure) o;

        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (diagnosisCode != null ? !diagnosisCode.equals(that.diagnosisCode) : that.diagnosisCode != null)
            return false;
        if (procedureCode != null ? !procedureCode.equals(that.procedureCode) : that.procedureCode != null)
            return false;
        return serviceType != null ? serviceType.equals(that.serviceType) : that.serviceType == null;

    }

    @Override
    public int hashCode() {
        int result = amount != null ? amount.hashCode() : 0;
        result = 31 * result + (diagnosisCode != null ? diagnosisCode.hashCode() : 0);
        result = 31 * result + (procedureCode != null ? procedureCode.hashCode() : 0);
        result = 31 * result + (serviceType != null ? serviceType.hashCode() : 0);
        return result;
    }
}

package services.model;

import android.content.ContentValues;
import android.database.Cursor;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import database.table.Table;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class Test implements Table.Test {

    @SerializedName("diagCode")
    @Expose
    private String diagCode;
    @SerializedName("diagDesc")
    @Expose
    private String diagDesc;
    @SerializedName("icd10Code")
    @Expose
    private String icd10Code;
    @SerializedName("procCode")
    @Expose
    private String procCode;
    @SerializedName("procedureName")
    @Expose
    private String procedureName;
    @SerializedName("approvalId")
    @Expose
    private String approvalId;
    @SerializedName("approvalType")
    @Expose
    private String approvalType;
    @SerializedName("amount")
    @Expose
    private Double amount;
    @SerializedName("costCenterID")
    @Expose
    private String costCenterID;
    @SerializedName("costCenter")
    @Expose
    private String costCenter;
    @SerializedName("procedureGroupId")
    @Expose
    private String procedureGroupId;
    @SerializedName("procedureGroup")
    @Expose
    private String procedureGroup;
    @SerializedName("active")
    @Expose
    private String active;

    private boolean isSelected;

    public Test(Cursor cursor) {
        Timber.d("converting cursor");
        this.diagCode = cursor.getString(cursor.getColumnIndex(DIAGNOSIS_CODE));
        this.diagDesc = cursor.getString(cursor.getColumnIndex(DIAGNOSIS_DESCRIPTION));
        this.icd10Code = cursor.getString(cursor.getColumnIndex(ICD_CODE));
        this.procCode = cursor.getString(cursor.getColumnIndex(PROCEDURE_CODE));
        this.procedureName = cursor.getString(cursor.getColumnIndex(PROCEDURE_NAME));
        this.approvalId = cursor.getString(cursor.getColumnIndex(APPROVAL_ID));
        this.approvalType = cursor.getString(cursor.getColumnIndex(APPROVAL_TYPE));
        this.amount = cursor.getDouble(cursor.getColumnIndex(AMOUNT));
        this.costCenter = cursor.getString(cursor.getColumnIndex(COST_CENTER));
        this.procedureGroupId = cursor.getString(cursor.getColumnIndex(PROCEDURE_GROUP_ID));
        this.active = cursor.getString(cursor.getColumnIndex(ACTIVE));
        setSelected(cursor.getInt(cursor.getColumnIndex(IS_SELECTED)) > 0);
    }


    public String getDiagCode() {
        return diagCode;
    }

    public void setDiagCode(String diagCode) {
        this.diagCode = diagCode;
    }

    public String getDiagDesc() {
        return diagDesc;
    }

    public void setDiagDesc(String diagDesc) {
        this.diagDesc = diagDesc;
    }

    public String getIcd10Code() {
        return icd10Code;
    }

    public void setIcd10Code(String icd10Code) {
        this.icd10Code = icd10Code;
    }

    public String getProcCode() {
        return procCode;
    }

    public void setProcCode(String procCode) {
        this.procCode = procCode;
    }

    public String getProcedureName() {
        return procedureName;
    }

    public void setProcedureName(String procedureName) {
        this.procedureName = procedureName;
    }

    public String getApprovalId() {
        return approvalId;
    }

    public void setApprovalId(String approvalId) {
        this.approvalId = approvalId;
    }

    public String getApprovalType() {
        return approvalType;
    }

    public void setApprovalType(String approvalType) {
        this.approvalType = approvalType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCostCenterID() {
        return costCenterID;
    }

    public void setCostCenterID(String costCenterID) {
        this.costCenterID = costCenterID;
    }

    public String getCostCenter() {
        return costCenter;
    }

    public void setCostCenter(String costCenter) {
        this.costCenter = costCenter;
    }

    public String getProcedureGroupId() {
        return procedureGroupId;
    }

    public void setProcedureGroupId(String procedureGroupId) {
        this.procedureGroupId = procedureGroupId;
    }

    public String getProcedureGroup() {
        return procedureGroup;
    }

    public void setProcedureGroup(String procedureGroup) {
        this.procedureGroup = procedureGroup;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public boolean isSelected() {
        return this.isSelected;
    }

    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public static final String getTableStructure() {
        return "CREATE TABLE " +
                TABLE_NAME + " ( " +
                DIAGNOSIS_CODE + " TEXT, " +
                DIAGNOSIS_DESCRIPTION + " TEXT, " +
                ICD_CODE + " TEXT, " +
                PROCEDURE_CODE + " TEXT, " +
                PROCEDURE_NAME + " TEXT, " +
                APPROVAL_ID + " TEXT, " +
                APPROVAL_TYPE + " TEXT, " +
                AMOUNT + " DOUBLE, " +
                COST_CENTER + " TEXT, " +
                PROCEDURE_GROUP_ID + " TEXT, " +
                ACTIVE + " TEXT, " +
                IS_SELECTED + " INTEGER DEFAULT 0 )";
    }

    public static final class Builder {

        ContentValues values = new ContentValues();

        public Builder() {
        }

        public Builder diagCode(String val) {
            values.put(Test.DIAGNOSIS_CODE, val);
            return this;
        }

        public Builder diagDesc(String val) {
            values.put(Test.DIAGNOSIS_DESCRIPTION, val);
            return this;
        }

        public Builder icd10Code(String val) {
            values.put(Test.ICD_CODE, val);
            return this;
        }

        public Builder procCode(String val) {
            values.put(Test.PROCEDURE_CODE, val);
            return this;
        }

        public Builder procedureName(String val) {
            values.put(Test.PROCEDURE_NAME, val);
            return this;
        }

        public Builder approvalId(String val) {
            values.put(Test.APPROVAL_ID, val);
            return this;
        }

        public Builder approvalType(String val) {
            values.put(Test.APPROVAL_TYPE, val);
            return this;
        }

        public Builder amount(Double val) {
            values.put(Test.AMOUNT, val);
            return this;
        }

        public Builder costCenter(String val) {
            values.put(Test.COST_CENTER, val);
            return this;
        }

        public Builder procedureGroupId(String val) {
            values.put(Test.PROCEDURE_GROUP_ID, val);
            return this;
        }

        public Builder active(String val) {
            values.put(Test.ACTIVE, val);
            return this;
        }

        public ContentValues build() {
            return values;
        }
    }
}

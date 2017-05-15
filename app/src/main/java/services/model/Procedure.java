package services.model;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import database.table.Table;

/**
 * <p>
 *     This Procedure serve also as database model
 * </p>
 */
public class Procedure implements Parcelable, Table.Procedure {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("serviceClassCode")
    @Expose
    private String serviceClassCode;
    @SerializedName("procedureCode")
    @Expose
    private String procedureCode;
    @SerializedName("procedureDesc")
    @Expose
    private String procedureDesc;
    @SerializedName("procedureAmount")
    @Expose
    private Integer procedureAmount;

    private boolean isSelected;

    public Procedure(Cursor cursor) {
        setId(cursor.getInt(cursor.getColumnIndex(ID)));
        setServiceClassCode(cursor.getString(cursor.getColumnIndex(SERVICE_CLASS_CODE)));
        setProcedureCode(cursor.getString(cursor.getColumnIndex(CODE)));
        setProcedureDesc(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
        setProcedureAmount(cursor.getInt(cursor.getColumnIndex(AMOUNT)));
    }

    protected Procedure(Parcel in) {
        serviceClassCode = in.readString();
        procedureCode = in.readString();
        procedureDesc = in.readString();
        isSelected = in.readByte() != 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(serviceClassCode);
        dest.writeString(procedureCode);
        dest.writeString(procedureDesc);
        dest.writeByte((byte) (isSelected ? 1 : 0));
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Procedure> CREATOR = new Creator<Procedure>() {
        @Override
        public Procedure createFromParcel(Parcel in) {
            return new Procedure(in);
        }

        @Override
        public Procedure[] newArray(int size) {
            return new Procedure[size];
        }
    };

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getServiceClassCode() {
        return serviceClassCode;
    }

    public void setServiceClassCode(String serviceClassCode) {
        this.serviceClassCode = serviceClassCode;
    }

    public String getProcedureCode() {
        return procedureCode;
    }

    public void setProcedureCode(String procedureCode) {
        this.procedureCode = procedureCode;
    }

    public String getProcedureDesc() {
        return procedureDesc;
    }

    public void setProcedureDesc(String procedureDesc) {
        this.procedureDesc = procedureDesc;
    }

    public Integer getProcedureAmount() {
        return procedureAmount;
    }

    public void setProcedureAmount(Integer procedureAmount) {
        this.procedureAmount = procedureAmount;
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
                ID + " INTEGER, " +
                SERVICE_CLASS_CODE + " TEXT ," +
                CODE + " TEXT ," +
                DESCRIPTION + " TEXT ," +
                AMOUNT + " INTEGER  )";
    }

}

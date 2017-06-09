package model.newtest;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

import services.model.Diagnosis;
import services.model.Procedure;

/**
 * Created by casjohnpaul on 6/3/2017.
 */

public class DiagnosisDetails implements Parcelable {

    private Diagnosis diagnosis;
    private List<Procedure> procedures;

    public DiagnosisDetails(Diagnosis diagnosis, List<Procedure> procedures) {
        this.diagnosis = diagnosis;
        this.procedures = procedures;
    }

    protected DiagnosisDetails(Parcel in) {
        diagnosis = in.readParcelable(Diagnosis.class.getClassLoader());
        procedures = in.createTypedArrayList(Procedure.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(diagnosis, flags);
        dest.writeTypedList(procedures);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<DiagnosisDetails> CREATOR = new Creator<DiagnosisDetails>() {
        @Override
        public DiagnosisDetails createFromParcel(Parcel in) {
            return new DiagnosisDetails(in);
        }

        @Override
        public DiagnosisDetails[] newArray(int size) {
            return new DiagnosisDetails[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiagnosisDetails)) return false;

        DiagnosisDetails that = (DiagnosisDetails) o;

        if (diagnosis != null ? !diagnosis.equals(that.diagnosis) : that.diagnosis != null)
            return false;
        return procedures != null ? procedures.equals(that.procedures) : that.procedures == null;

    }

    @Override
    public int hashCode() {
        int result = diagnosis != null ? diagnosis.hashCode() : 0;
        result = 31 * result + (procedures != null ? procedures.hashCode() : 0);
        return result;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Procedure> getProcedures() {
        return procedures;
    }

    public void setProcedures(List<Procedure> procedures) {
        this.procedures = procedures;
    }

}

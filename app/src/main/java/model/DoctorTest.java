package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John Paul Cas on 4/11/2017.
 */
public class DoctorTest implements Parcelable {

    private String hospitalName;
    private String doctorName;
    private String dateOfConsultation;

    public DoctorTest(Builder builder) {
        setHospitalName(builder.hospitalName);
        setDoctorName(builder.doctorName);
        setDateOfConsultation(builder.dateOfConsultation);
    }

    protected DoctorTest(Parcel in) {
        readToParcel(in);
    }

    public static final Creator<DoctorTest> CREATOR = new Creator<DoctorTest>() {
        @Override
        public DoctorTest createFromParcel(Parcel in) {
            return new DoctorTest(in);
        }

        @Override
        public DoctorTest[] newArray(int size) {
            return new DoctorTest[size];
        }
    };

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getDateOfConsultation() {
        return dateOfConsultation;
    }

    public void setDateOfConsultation(String dateOfConsultation) {
        this.dateOfConsultation = dateOfConsultation;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(hospitalName);
        dest.writeString(doctorName);
        dest.writeString(dateOfConsultation);
    }

    public void readToParcel(Parcel in) {
        hospitalName = in.readString();
        doctorName = in.readString();
        dateOfConsultation = in.readString();
    }

    @Override
    public String toString() {
        return new StringBuilder("Tests")
                .append("Doctor Name : " + getDoctorName())
                .append("Hospital Name : " + getHospitalName())
                .append("Date of Consultation " + getDateOfConsultation())
                .toString();
    }

    public static final class Builder {

        private String hospitalName;
        private String doctorName;
        private String dateOfConsultation;

        public Builder hospitalName(String hospitalName) {
            this.hospitalName = hospitalName;
            return this;
        }

        public Builder doctorName(String doctorName) {
            this.doctorName = doctorName;
            return this;
        }

        public Builder dateOfConsultation(String dateOfConsultation) {
            this.dateOfConsultation = dateOfConsultation;
            return this;
        }

        public DoctorTest build() {
            return new DoctorTest(this);
        }

    }

}

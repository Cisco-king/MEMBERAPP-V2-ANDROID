package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mpx-pawpaw on 1/30/17.
 */
public class SpecsAdapter implements Parcelable {

    private String specializationDescription;

    private String specializationCode;

    private String selected ;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public SpecsAdapter(String specializationCode, String specializationDescription ,String selected) {
        this.specializationDescription = specializationDescription;
        this.specializationCode = specializationCode;
        this.selected = selected;
    }

    public String getSpecializationDescription ()
    {
        return specializationDescription;
    }

    public void setSpecializationDescription (String specializationDescription)
    {
        this.specializationDescription = specializationDescription;
    }

    public String getSpecializationCode ()
    {
        return specializationCode;
    }

    public void setSpecializationCode (String specializationCode)
    {
        this.specializationCode = specializationCode;
    }



    protected SpecsAdapter(Parcel in) {
        specializationDescription = in.readString();
        specializationCode = in.readString();
        selected = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(specializationDescription);
        dest.writeString(specializationCode);
        dest.writeString(selected);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SpecsAdapter> CREATOR = new Parcelable.Creator<SpecsAdapter>() {
        @Override
        public SpecsAdapter createFromParcel(Parcel in) {
            return new SpecsAdapter(in);
        }

        @Override
        public SpecsAdapter[] newArray(int size) {
            return new SpecsAdapter[size];
        }
    };
}
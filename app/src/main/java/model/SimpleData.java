package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mpx-pawpaw on 2/21/17.
 */
public class SimpleData implements Parcelable {

    private String selected ;

    public String getHospital() {
        return Hospital;
    }

    public void setHospital(String hospital) {
        Hospital = hospital;
    }

    private String Hospital ;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public SimpleData() {
    }

    public SimpleData(Parcel in) {
        selected = in.readString();
        Hospital = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(selected);
        dest.writeString(Hospital);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<SimpleData> CREATOR = new Parcelable.Creator<SimpleData>() {
        @Override
        public SimpleData createFromParcel(Parcel in) {
            return new SimpleData(in);
        }

        @Override
        public SimpleData[] newArray(int size) {
            return new SimpleData[size];
        }
    };
}
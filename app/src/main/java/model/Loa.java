package model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

import services.model.LoaList;

/**
 * Created by mpx-pawpaw on 2/15/17.
 */
public class Loa implements Parcelable {
    private ArrayList<LoaList> loaList;

    public ArrayList<LoaList> getLoaList() {
        return loaList;
    }

    public void setLoaList(ArrayList<LoaList> loaList) {
        this.loaList = loaList;
    }

    @Override
    public String toString() {
        return "ClassPojo [loaList = " + loaList + "]";
    }

    protected Loa(Parcel in) {
        if (in.readByte() == 0x01) {
            loaList = new ArrayList<LoaList>();
            in.readList(loaList, LoaList.class.getClassLoader());
        } else {
            loaList = null;
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (loaList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(loaList);
        }
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Loa> CREATOR = new Parcelable.Creator<Loa>() {
        @Override
        public Loa createFromParcel(Parcel in) {
            return new Loa(in);
        }

        @Override
        public Loa[] newArray(int size) {
            return new Loa[size];
        }
    };
}
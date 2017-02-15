package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class Provinces implements Parcelable {


    private String regionCode;

    private String provinceCode;

    private String provinceName;

    public Provinces(String regionCode, String provinceCode, String provinceName) {
        this.regionCode = regionCode;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
    }

    public String getRegionCode ()
    {
        return regionCode;
    }

    public void setRegionCode (String regionCode)
    {
        this.regionCode = regionCode;
    }

    public String getProvinceCode ()
    {
        return provinceCode;
    }

    public void setProvinceCode (String provinceCode)
    {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName ()
    {
        return provinceName;
    }

    public void setProvinceName (String provinceName)
    {
        this.provinceName = provinceName;
    }



    protected Provinces(Parcel in) {
        regionCode = in.readString();
        provinceCode = in.readString();
        provinceName = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(regionCode);
        dest.writeString(provinceCode);
        dest.writeString(provinceName);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Provinces> CREATOR = new Parcelable.Creator<Provinces>() {
        @Override
        public Provinces createFromParcel(Parcel in) {
            return new Provinces(in);
        }

        @Override
        public Provinces[] newArray(int size) {
            return new Provinces[size];
        }
    };
}
package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class ProvincesAdapter implements Parcelable {


    private String regionCode;

    private String provinceCode;

    private String provinceName;

    private String selected;


    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public ProvincesAdapter(String regionCode, String provinceCode, String provinceName, String selected) {
        this.regionCode = regionCode;
        this.provinceCode = provinceCode;
        this.provinceName = provinceName;
        this.selected = selected;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }


    protected ProvincesAdapter(Parcel in) {
        regionCode = in.readString();
        provinceCode = in.readString();
        provinceName = in.readString();
        selected = in.readString();
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
        dest.writeString(selected);
    }

    @SuppressWarnings("unused")
    public static final Creator<ProvincesAdapter> CREATOR = new Creator<ProvincesAdapter>() {
        @Override
        public ProvincesAdapter createFromParcel(Parcel in) {
            return new ProvincesAdapter(in);
        }

        @Override
        public ProvincesAdapter[] newArray(int size) {
            return new ProvincesAdapter[size];
        }
    };
}
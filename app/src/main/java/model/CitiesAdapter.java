package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by mpx-pawpaw on 1/25/17.
 */

public class CitiesAdapter implements Parcelable {

    private String regionCode;

    private String countryName;

    private String provinceCode;

    private String cityName;

    private String countryCode;

    private String cityCode;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    private String selected;

    public CitiesAdapter(String regionCode, String countryName, String provinceCode, String cityName, String countryCode, String cityCode, String selected) {
        this.regionCode = regionCode;
        this.countryName = countryName;
        this.provinceCode = provinceCode;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.cityCode = cityCode;
        this.selected = selected;
    }

    public String getRegionCode ()
    {
        return regionCode;
    }

    public void setRegionCode (String regionCode)
    {
        this.regionCode = regionCode;
    }

    public String getCountryName ()
    {
        return countryName;
    }

    public void setCountryName (String countryName)
    {
        this.countryName = countryName;
    }

    public String getProvinceCode ()
    {
        return provinceCode;
    }

    public void setProvinceCode (String provinceCode)
    {
        this.provinceCode = provinceCode;
    }

    public String getCityName ()
    {
        return cityName;
    }

    public void setCityName (String cityName)
    {
        this.cityName = cityName;
    }

    public String getCountryCode ()
    {
        return countryCode;
    }

    public void setCountryCode (String countryCode)
    {
        this.countryCode = countryCode;
    }

    public String getCityCode ()
    {
        return cityCode;
    }

    public void setCityCode (String cityCode)
    {
        this.cityCode = cityCode;
    }




    protected CitiesAdapter(Parcel in) {
        regionCode = in.readString();
        countryName = in.readString();
        provinceCode = in.readString();
        cityName = in.readString();
        countryCode = in.readString();
        cityCode = in.readString();
        selected = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(regionCode);
        dest.writeString(countryName);
        dest.writeString(provinceCode);
        dest.writeString(cityName);
        dest.writeString(countryCode);
        dest.writeString(cityCode);
        dest.writeString(selected);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<CitiesAdapter> CREATOR = new Parcelable.Creator<CitiesAdapter>() {
        @Override
        public CitiesAdapter createFromParcel(Parcel in) {
            return new CitiesAdapter(in);
        }

        @Override
        public CitiesAdapter[] newArray(int size) {
            return new CitiesAdapter[size];
        }
    };
}
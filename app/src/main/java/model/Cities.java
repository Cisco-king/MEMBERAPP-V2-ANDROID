package model;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class Cities {

    private String regionCode;

    private String countryName;

    private String provinceCode;

    private String cityName;

    private String countryCode;

    private String cityCode;

    public Cities(String regionCode, String countryName, String provinceCode, String cityName, String countryCode, String cityCode) {
        this.regionCode = regionCode;
        this.countryName = countryName;
        this.provinceCode = provinceCode;
        this.cityName = cityName;
        this.countryCode = countryCode;
        this.cityCode = cityCode;
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

    @Override
    public String toString()
    {
        return "ClassPojo [regionCode = "+regionCode+", countryName = "+countryName+", provinceCode = "+provinceCode+", cityName = "+cityName+", countryCode = "+countryCode+", cityCode = "+cityCode+"]";
    }

}

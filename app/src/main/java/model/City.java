package model;

import java.util.ArrayList;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class City {

    private ArrayList<Cities> cities;

    public ArrayList<Cities> getCities ()
    {
        return cities;
    }

    public void setCities (ArrayList<Cities> cities)
    {
        this.cities = cities;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [cities = "+cities+"]";
    }
}

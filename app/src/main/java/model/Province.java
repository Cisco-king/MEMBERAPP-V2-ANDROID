package model;

import java.util.ArrayList;

/**
 * Created by mpx-pawpaw on 1/24/17.
 */

public class Province {
    private ArrayList<Provinces> provinces;

    public ArrayList<Provinces> getProvinces ()
    {
        return provinces;
    }

    public void setProvinces (ArrayList<Provinces> provinces)
    {
        this.provinces = provinces;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [provinces = "+provinces+"]";
    }

}

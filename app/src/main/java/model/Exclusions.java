package model;

/**
 * Created by mpx-pawpaw on 1/5/17.
 */

public class Exclusions {


    private String[] exclusions;

    public String[] getExclusions ()
    {
        return exclusions;
    }

    public void setExclusions (String[] exclusions)
    {
        this.exclusions = exclusions;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [exclusions = "+exclusions+"]";
    }
}

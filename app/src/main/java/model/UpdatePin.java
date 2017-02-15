package model;

/**
 * Created by mpx-pawpaw on 2/6/17.
 */

public class UpdatePin {

    private String username;

    private String oldPin;

    private String newPin;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getOldPin ()
    {
        return oldPin;
    }

    public void setOldPin (String oldPin)
    {
        this.oldPin = oldPin;
    }

    public String getNewPin ()
    {
        return newPin;
    }

    public void setNewPin (String newPin)
    {
        this.newPin = newPin;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [username = "+username+", oldPin = "+oldPin+", newPin = "+newPin+"]";
    }
}

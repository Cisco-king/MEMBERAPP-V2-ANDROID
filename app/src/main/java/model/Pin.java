package model;

/**
 * Created by mpx-pawpaw on 2/3/17.
 */

public class Pin {


    private String username;

    private String pin;

    private String password;

    public String getUsername ()
    {
        return username;
    }

    public void setUsername (String username)
    {
        this.username = username;
    }

    public String getPin ()
    {
        return pin;
    }

    public void setPin (String pin)
    {
        this.pin = pin;
    }

    public String getPassword ()
    {
        return password;
    }

    public void setPassword (String password)
    {
        this.password = password;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [username = "+username+", pin = "+pin+", password = "+password+"]";
    }

}

package model;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by mpx-pawpaw on 1/18/17.
 */

public class LoaRequest {


    private String name;
    private String date;
    private String status;



    public LoaRequest(String name , String date , String status)    {
        this.name = name ;
        this.date  = date ;
        this.status = status ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

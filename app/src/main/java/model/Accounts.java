package model;

import android.graphics.Bitmap;

import java.util.BitSet;

/**
 * Created by window on 9/29/2016.
 */

public class Accounts {


    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String company;
    String status;
    String name;
    String id;

    public Accounts() {

    }
}

package model;


import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by mpx-pawpaw on 10/13/16.
 */

public class Member_info_header {

    String name, id, status, company;
    String user_pict;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getUser_pict() {
        return user_pict;
    }

    public void setUser_pict(String user_pict) {
        this.user_pict = user_pict;
    }
}

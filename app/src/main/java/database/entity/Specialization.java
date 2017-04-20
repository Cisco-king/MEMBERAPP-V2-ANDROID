package database.entity;

import android.database.Cursor;

import database.table.Table;

/**
 * Created by John Paul Cas on 4/19/2017.
 */

public class Specialization implements Table.Specialization {

    private String code;
    private String description;


    public Specialization(Cursor cursor) {
        setCode(cursor.getString(cursor.getColumnIndex(CODE)));
        setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
    }

    /**
     *
     * @return
     * The Code
     */
    public String getCode() {
        return code;
    }

    /**
     *
     * @param code
     * The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     *
     * @return
     * The Description
     */
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description
     * The Description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}

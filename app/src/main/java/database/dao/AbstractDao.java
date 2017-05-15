package database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import Sqlite.DatabaseHandler;
import database.EntityConvertable;

/**
 * @author John Paul Cas
 * @since 4/20/2017
 */
public abstract class AbstractDao<T> implements EntityConvertable<T> {

    private SQLiteDatabase database;
    private DatabaseHandler dbHandler;

    private final String TABLE_NAME;
    protected final String FIELDS[];
    protected static final String EQUALS = "=";

    public AbstractDao(Context context, String tableName, String[] fields) {
        dbHandler = DatabaseHandler.getInstance(context);
        TABLE_NAME = tableName;
        FIELDS = fields;
    }

    /**
     * Open Database handler
     */
    protected void open() {
        database = dbHandler.getWritableDatabase();
    }

    /**
     * Close Database handler
     */
    protected void close() {
        dbHandler.close();
    }

    /**
     * @param values The ContentValues to be inserted
     */
    protected long _insert(ContentValues values) {
        try {
            open();

            long val = database.insert(TABLE_NAME, null, values);
            return val;
        } finally {
            close();
        }
    }

    /**
     *
     * @param values
     * The Values to be update
     * @param conditions
     * The Condition
     * @return
     * <b>TRUE</b> if successfully inserted otherwise <b>FALSE</b>
     */
    protected boolean _update(ContentValues values, String conditions) {
        try {
            open();

            boolean isInserted = database.update(TABLE_NAME, values, conditions, null) > 0 ? true : false;
            return isInserted;
        } finally {
            close();
        }
    }

    /**
     *
     * @param conditions
     * The Delete Conditions
     * @return
     * TRUE if successfully deleted otherwise FALSE
     */
    protected boolean _delete(String conditions) {
        try {
            open();
            boolean ret = database.delete(TABLE_NAME, conditions, null) > 0 ? true : false;
            return ret;
        } finally {
            close();
        }
    }

    /**
     * <p>
     *     Delete all TABLE data
     * </p>
     */
    protected void _deleteAll() {
        try {
            open();
            database.delete(TABLE_NAME, null, null);
        } finally {
            close();
        }
    }

    protected Cursor _findAll() {
        return _findAllByFieldsWithOrder(null, null);
    }

    protected Cursor _findAllByFields(String conditions) {
        return _findAllByFieldsWithOrder(conditions, null);
    }

    /**
     *
     * @param conditions
     *  The SQL condition
     * @param orderBy
     *  ASC, DESC
     * @return
     *  The Cursor
     */
    protected Cursor _findAllByFieldsWithOrder(String conditions, String orderBy) {
        return database.query(TABLE_NAME, FIELDS, conditions, null, null, null, orderBy);
    }

    /**
     *
     * @return
     * Total Count of TABLE
     */
    public int getTotalCount() {
        String countQuery = "SELECT * FROM " + TABLE_NAME ;

        open();
        Cursor cursor = database.rawQuery(countQuery, null);

        int count =  cursor.getCount();
        cursor.close();
        close();

        return count;
    }

}

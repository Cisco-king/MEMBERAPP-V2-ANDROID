package database;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.List;

/**
 * @author John Paul Cas
 *
 */
public interface EntityConvertable<T> {

    /**
     *
     * @param cursor
     * The Cursor to be converted
     * @return
     * The T Object
     */
    T convertCursorToObject(Cursor cursor);

    /**
     *
     * @param cursor
     * The Cursor to be converted to list
     * @return
     * The List T Object
     */
    List<T> convertCursorToList(Cursor cursor);

    /**
     *
     * @param object
     * The T Object to be converted to Convent Values
     * @return
     * The Content Values
     */
    ContentValues getContentValues(T object);

}

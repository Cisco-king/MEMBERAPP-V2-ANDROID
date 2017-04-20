package database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import database.Dao;
import database.entity.Specialization;

/**
 * @author John Paul Cas
 */
public class SpecializationDao extends AbstractDao<Specialization>
        implements Dao<Specialization> {


    public SpecializationDao(Context context) {
        super(context, Specialization.TABLE_NAME, Specialization.COLUMNS);
    }

    @Override
    public long insert(Specialization object) {
        return _insert(getContentValues(object));
    }

    @Override
    public void deleteAll() {
        _deleteAll();
    }

    @Override
    public Specialization find(String uniqueValue) {

        open();

        Cursor cursor = _findAllByFields(Specialization.CODE + EQUALS + uniqueValue);

        Specialization specialization = convertCursorToObject(cursor);

        close();

        return specialization;
    }

    @Override
    public List<Specialization> findAll() {
        Cursor cursor = _findAllByFieldsWithOrder(null, Specialization.CODE + " ASC");
        List<Specialization> specializations = convertCursorToList(cursor);

        return specializations;
    }


    @Override
    public Specialization convertCursorToObject(Cursor cursor) {

        Specialization specialization = null;

        if (cursor.moveToFirst()) {
            specialization = new Specialization(cursor);
        }

        return specialization;
    }

    @Override
    public List<Specialization> convertCursorToList(Cursor cursor) {

        List<Specialization> list = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                list.add(new Specialization(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

    @Override
    public ContentValues getContentValues(Specialization specialization) {
        ContentValues values = new ContentValues();

        values.put(Specialization.CODE , specialization.getCode());
        values.put(Specialization.DESCRIPTION, specialization.getDescription());

        return values;
    }

}

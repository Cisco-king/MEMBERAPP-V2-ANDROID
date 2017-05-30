package database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import database.Dao;
import database.table.Table;
import services.model.Procedure;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/8/2017.
 */

public class ProcedureDao extends AbstractDao<Procedure>
        implements Dao<Procedure> {


    public ProcedureDao(Context context) {
        super(context, Procedure.TABLE_NAME, Table.Procedure.COLUMN);
    }

    @Override
    public long insert(Procedure procedure) {
        return _insert(getContentValues(procedure));
    }

    public Boolean insertAllProcedues(List<Procedure> procedures) {
        int inserted = 0;
        int failToInsert = 0;
        int totalToBeInseted = procedures.size();

        for (Procedure procedure : procedures) {
            Timber.d("procedure id %s, code %s", procedure.getId(), procedure.getProcedureCode());
            boolean isInserted = insert(procedure) > 0 ? true : false;
            if (isInserted) {
                Timber.d("procedure inserted");
                inserted ++;
            } else {
                Timber.d("fail inseting");
                failToInsert ++;
            }
        }

        Timber.d("Total number of Procedures %s", totalToBeInseted);
        Timber.d("Number of Procedures Successfully inserted %s", inserted);
        Timber.d("Number of Procedure Fail to insert %s", failToInsert);
        if (inserted == totalToBeInseted) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }
    }

    @Override
    public void deleteAll() {
        _deleteAll();
    }

    public void update(Procedure procedure) {
        boolean isUpdated = _update(getContentValues(procedure, procedure.isSelected()), Procedure.ID + EQUALS + procedure.getId());
        if (isUpdated)
            Timber.d("update success ...");
        else
            Timber.d("update fail");
    }

    public void resetAllSelectedStatusToFalse() {
        open();
        Cursor cursor = _findAllByFields(Procedure.IS_SELECTED + EQUALS + 1);
        List<Procedure> procedures = convertCursorToList(cursor);

        close();
        for (Procedure procedure : procedures) {
            update(procedure);
        }
    }

    @Override
    public Procedure find(String uniqueValue) {
        open();
        Cursor cursor = _findAllByFields(Procedure.ID + EQUALS + uniqueValue);
        Procedure procedure = convertCursorToObject(cursor);
        close();

        return procedure;
    }

    public List<Procedure> findAllById(String id) {
        open();
        Cursor cursor = _findAllByFields(Procedure.ID + EQUALS + id);
        List<Procedure> procedures = convertCursorToList(cursor);

        close();
        return procedures;
    }

    @Override
    public List<Procedure> findAll() {
        open();
        Cursor cursor = _findAllByFieldsWithOrder(null, null);
        List<Procedure> procedures = convertCursorToList(cursor);

        close();
        return procedures;
    }

    @Override
    public Procedure convertCursorToObject(Cursor cursor) {
        Procedure procedure = null;
        if (cursor.moveToFirst()) {
            procedure = new Procedure(cursor);
        }

        return procedure;
    }

    @Override
    public List<Procedure> convertCursorToList(Cursor cursor) {
        List<Procedure> list = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                list.add(new Procedure(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

    @Override
    public ContentValues getContentValues(Procedure procedure) {
        ContentValues values = new ContentValues();
        values.put(Procedure.ID , procedure.getId());
        values.put(Procedure.SERVICE_CLASS_CODE, procedure.getServiceClassCode());
        values.put(Procedure.CODE, procedure.getProcedureCode());
        values.put(Procedure.DESCRIPTION, procedure.getProcedureDesc());
        values.put(Procedure.AMOUNT, procedure.getProcedureAmount());

        return values;
    }

    public ContentValues getContentValues(Procedure procedure, boolean isSelected) {
        ContentValues values = this.getContentValues(procedure);
        values.put(Procedure.IS_SELECTED, isSelected == true ? 1 : 0);

        return values;
    }

}

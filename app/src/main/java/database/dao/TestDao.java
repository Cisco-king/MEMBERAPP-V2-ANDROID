package database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import database.Dao;
import services.model.Test;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class TestDao extends AbstractDao<Test> implements Dao<Test> {


    public TestDao(Context context) {
        super(context, Test.TABLE_NAME, Test.COLUMNS);
    }

    @Override
    public long insert(Test object) {
        return _insert(getContentValues(object));
    }

    public Boolean bulkinsert(List<Test> tests) {
        int inserted = 0;
        int failToInsert = 0;
        int totalToBeInseted = tests.size();

        for (Test procedure : tests) {
            Timber.d("procedure id %s, code %s", procedure.getProcCode(), procedure.getProcedureName());
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

    @Override
    public Test find(String uniqueValue) {
        open();
        Cursor cursor = _findAllByFields(Test.PROCEDURE_CODE + EQUALS + "\'" + uniqueValue + "\'");
        Test procedure = convertCursorToObject(cursor);
        close();

        return procedure;
    }

    @Override
    public List<Test> findAll() {
        open();
        Cursor cursor = _findAllByFieldsWithOrder(null, null);
        List<Test> tests = convertCursorToList(cursor);

        close();
        return tests;
    }

    @Override
    public Test convertCursorToObject(Cursor cursor) {
        Test test = null;
        if (cursor.moveToFirst()) {
            test = new Test(cursor);
        }

        return test;
    }

    @Override
    public List<Test> convertCursorToList(Cursor cursor) {
        List<Test> list = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                list.add(new Test(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

    @Override
    public ContentValues getContentValues(Test test) {
        return new Test.Builder()
                .diagCode(test.getDiagCode())
                .diagDesc(test.getDiagDesc())
                .icd10Code(test.getIcd10Code())
                .procCode(test.getProcCode())
                .procedureName(test.getProcedureName())
                .approvalId(test.getApprovalId())
                .approvalType(test.getApprovalType())
                .amount(test.getAmount())
                .costCenter(test.getCostCenter())
                .procedureGroupId(test.getProcedureGroupId())
                .active(test.getActive())
                .build();
    }
}

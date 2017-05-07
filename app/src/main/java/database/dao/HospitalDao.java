package database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import database.Dao;
import database.table.Table;
import model.HospitalList;

/**
 * Created by casjohnpaul on 5/7/2017.
 */

public class HospitalDao extends AbstractDao<HospitalList> implements Dao<HospitalList> {


    public HospitalDao(Context context) {
        super(context, Table.Hospital.TABLE_NAME, Table.Hospital.COLUMN);
    }

    @Override
    public long insert(HospitalList hospital) {
        return _insert(getContentValues(hospital));
    }

    @Override
    public void deleteAll() {
        _deleteAll();
    }

    @Override
    public HospitalList find(String uniqueValue) {
        open();

        Cursor cursor = _findAllByFields(HospitalList.HOSPITAL_CODE + EQUALS + uniqueValue);
        HospitalList hospital = convertCursorToObject(cursor);
        close();

        return hospital;
    }

    public List<HospitalList> findAllHospitalByHospitalCode(String hospitalCode) {
        open();

        Cursor cursor = _findAllByFields(HospitalList.HOSPITAL_CODE + EQUALS + hospitalCode);
        List<HospitalList> hospitals = convertCursorToList(cursor);
        close();
        return hospitals;
    }

    @Override
    public List<HospitalList> findAll() {
        open();
        Cursor cursor = _findAllByFieldsWithOrder(null, null);
        List<HospitalList> doctors = convertCursorToList(cursor);

        close();
        return doctors;
    }

    @Override
    public HospitalList convertCursorToObject(Cursor cursor) {
        HospitalList doctor = null;
        if (cursor.moveToFirst()) {
            doctor = new HospitalList(cursor);
        }

        return doctor;
    }

    @Override
    public List<HospitalList> convertCursorToList(Cursor cursor) {
        List<HospitalList> list = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                list.add(new HospitalList(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

    @Override
    public ContentValues getContentValues(HospitalList hospital) {
        ContentValues values = new ContentValues();
        values.put(HospitalList.PHONE_NUMBER, hospital.getPhoneNo());
        values.put(HospitalList.REGION, hospital.getRegion());
        values.put(HospitalList.STREET_ADDRESS, hospital.getStreetAddress());
        values.put(HospitalList.CATEGORY, hospital.getCategory());
        values.put(HospitalList.FAX_NUMBER, hospital.getFaxno());
        values.put(HospitalList.ALIEAS, hospital.getAlias());
        values.put(HospitalList.KEYWORD, hospital.getKeyword());
        values.put(HospitalList.PROVINCE, hospital.getProvince());
        values.put(HospitalList.HOSPITAL_NAME, hospital.getHospitalName());
        values.put(HospitalList.HOSPITAL_CODE, hospital.getHospitalCode());
        values.put(HospitalList.COORDINATOR, hospital.getCoordinator());
        values.put(HospitalList.CONTACT_PERSON, hospital.getContactPerson());
        values.put(HospitalList.CITY, hospital.getCity());

        return values;
    }

}

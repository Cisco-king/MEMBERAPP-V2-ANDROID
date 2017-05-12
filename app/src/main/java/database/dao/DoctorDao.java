package database.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import database.Dao;
import database.EntityConvertable;
import database.entity.Doctor;
import database.table.Table;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/5/2017.
 */

public class DoctorDao extends AbstractDao<Doctor> implements Dao<Doctor> {



    public DoctorDao(Context context) {
        super(context, Doctor.TABLE_NAME, Table.Doctor.COLUMN);
    }


    @Override
    public long insert(Doctor doctor) {
        return _insert(getContentValues(doctor));
    }

    public Boolean insertAllDoctors(List<Doctor> doctors) {
        int inserted = 0;
        int failToInsert = 0;
        int totalDoctorToBeInserted = doctors.size();

        for (Doctor doctor : doctors) {
            boolean isInserted = insert(doctor) > 0 ? true : false;
            if (isInserted) {
                Timber.d("doctor inserted");
                inserted ++;
            } else {
                Timber.d("fail inseting");
                failToInsert ++;
            }
        }

        Timber.d("Total number of Doctors %s", totalDoctorToBeInserted);
        Timber.d("Number of Doctor Successfully inserted %s", inserted);
        Timber.d("Number of Doctor Fail to insert %s", failToInsert);
        if (inserted == totalDoctorToBeInserted) {
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
    public Doctor find(String uniqueValue) {
        // todo find
//        _findAllByFields(Doctor. + EQUALS + D)
        return null;
    }

    @Override
    public List<Doctor> findAll() {
        open();
        Cursor cursor = _findAllByFieldsWithOrder(null, null);
        List<Doctor> doctors = convertCursorToList(cursor);

        close();
        return doctors;
    }

    @Override
    public Doctor convertCursorToObject(Cursor cursor) {
        Doctor doctor = null;
        if (cursor.moveToFirst()) {
            doctor = new Doctor(cursor);
        }

        return doctor;
    }

    @Override
    public List<Doctor> convertCursorToList(Cursor cursor) {
        List<Doctor> list = new ArrayList<>();

        if (cursor.moveToFirst())
        {
            do {
                list.add(new Doctor(cursor));
            } while (cursor.moveToNext());
        }
        cursor.close();

        return list;
    }

    @Override
    public ContentValues getContentValues(Doctor doctor) {
        ContentValues values = new ContentValues();
        values.put(Doctor.DOCTOR_CODE , doctor.getDoctorCode());
        values.put(Doctor.LAST_NAME, doctor.getLastName());
        values.put(Doctor.FIRST_NAME, doctor.getFirstName());
        values.put(Doctor.MIDDLE_NAME, doctor.getMiddleName());
        values.put(Doctor.SPECIALIZATION_DESCRIPTION, doctor.getSpecializationDescription());
        values.put(Doctor.SPECIALIZATION_CODE, doctor.getSpecializationCode());
        values.put(Doctor.W_TAX, doctor.getWtax());
        values.put(Doctor.GRACE_PERIOD, doctor.getGracePeriod());
        values.put(Doctor.VAT, doctor.getVat());
        values.put(Doctor.CONTACT_NUMBER, doctor.getContactNumber());
        values.put(Doctor.CITY, doctor.getCity());
        values.put(Doctor.PROVINCE, doctor.getProvince());
        values.put(Doctor.REGION, doctor.getRegion());
        values.put(Doctor.PRC, doctor.getPrc());
        values.put(Doctor.STREET_ADDRESS, doctor.getStreetAddress());
        values.put(Doctor.ROOM_NUMBER, doctor.getRoomNo());
        values.put(Doctor.SCHEDULE, doctor.getSchedule());


        return values;
    }


}

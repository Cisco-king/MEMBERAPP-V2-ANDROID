package Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.medicard.com.medicard.NavigationActivity;
import android.util.Log;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


import model.Cities;
import model.CitiesAdapter;
import model.GetDoctorsToHospital;
import model.Hospital;
import model.HospitalList;
import model.Loa;
import model.LoaFetch;
import model.LoaList;
import model.Provinces;
import model.SimpleData;
import model.SpecializationList;
import model.Specializations;
import model.SpecsAdapter;
import utilities.Constant;
import utilities.SharedPref;

/**
 * Created by mpx-pawpaw on 1/3/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    ////CHECK DOCTORS TABLE
    private Context context;
    private SQLiteDatabase database;
    private Cursor cursor;

    private String TAG = "database";
    private String hospitalCode = "hospitalCode";
    private String hospitalName = "hospitalName";
    private String keyword = "keyword";
    private String alias = "alias";
    private String category = "category";
    private String coordinator = "coordinator";
    private String streetAddress = "streetAddress";
    private String city = "city";
    private String province = "province";
    private String region = "region";
    private String phoneNo = "phoneNo";
    private String faxno = "faxno";
    private String contactPerson = "contactPerson";
    private String excluded = "excluded";


    private String doctable = "doctor";
    // private String region = "region";
    //  private String streetAddress = "streetAddress";
    private String specialRem = "specialRem";
    private String docFname = "docFname";
    private String specDesc = "specDesc";
    private String doctorCode = "doctorCode";
    private String hospRemarks = "hospRemarks";
    private String docMname = "docMname";
    private String vat = "vat";
    private String wtax = "wtax";
    private String remarks = "remarks";
    //  private String city = "city";
    private String gracePeriod = "gracePeriod";
    // private String phoneNo = "phoneNo";
    private String specCode = "specCode";
    private String schedule = "schedule";
    //  private String faxno = "faxno";
    //   private String province = "province";
    //    private String hospitalName = "hospitalName";
    private String docLname = "docLname";
    //private String hospitalCode = "hospitalCode";
    //   private String contactPerson = "contactPerson";
    private String roomBoard = "roomBoard";
    private String remarks2 = "remarks2";
    private String room = "room";


    private String provinceCode = "provinceCode";
    private String provinceName = "provinceName";
    private String regionCode = "regionCode";
    private String provinceTable = "Province";


    // private String regionCode = "regionCode";
    //private String provinceCode = "provinceCode";
    private String cityName = "cityName";
    private String countryCode = "countryCode";
    private String cityCode = "cityCode";
    private String countryName = "countryName";
    private String cityTable = "CITY";

    private String specTable = "SPECIALIZATION";
    private String specializationCode = "specializationCode";
    private String specializationDescription = "specializationDescription";


    private String loaTable = "LOAREQUEST";
    private String procedureCode = "procedureCode";
    private String batchCode = "batchCode";
    private String reason = "reason";
    private String diagnosis = "diagnosis";
    // private String remarks = "remarks";
    private String diagnosisCode = "diagnosisCode";
    private String type = "type";
    private String updatedBy = "updatedBy";
    private String callTypeId = "callTypeId";
    private String id = "id";
    private String runningBill = "runningBill";
    private String memMi = "memMi";
    private String memberCode = "memberCode";
    private String memFname = "memFname";
    private String memLname = "memLname";
    // private String doctorCode = "doctorCode";
    private String actionTaken = "actionTaken";
    private String status = "status";
    private String updatedDate = "updatedDate";
    private String terminalNo = "terminalNo";
    private String approvalNo = "approvalNo";
    private String procedureAmount = "procedureAmount";
    private String callDate = "callDate";
    private String companyCode = "companyCode";
    //private String category= "category";
    private String callerId = "callerId";
    private String approvalDate = "approvalDate";
    //private String hospitalCode = "hospitalCode";
    private String procedureDesc = "procedureDesc";
    private String notes = "notes";
    private String dateAdmitted = "dateAdmitted";
    private String memCompany = "memCompany";
    //private String room = "room";
    private String docName = "docName";
    private String docSpec = "docSpec";
    private String docSpecCode = "docSpecCode";
    // private String hospitalName = "hospitalName";

    protected static final String databaseName = "Medicard";
    private String hospTable = "hospital";

    private static final int version = 1;


    private String createLoaRequest = " CREATE TABLE " +
            loaTable + " ( " +
            memCompany + " TEXT ," +
            procedureCode + " TEXT ," +
            batchCode + " TEXT ," +
            reason + " TEXT ," +
            diagnosis + " TEXT ," +
            remarks + " TEXT ," +
            diagnosisCode + " TEXT ," +
            type + " TEXT ," +
            updatedBy + " TEXT ," +
            callTypeId + " TEXT ," +
            id + " TEXT ," +
            runningBill + " TEXT ," +
            memMi + " TEXT ," +
            memberCode + " TEXT ," +
            memFname + " TEXT ," +
            memLname + " TEXT ," +
            doctorCode + " TEXT ," +
            actionTaken + " TEXT ," +
            status + " TEXT ," +
            updatedDate + " TEXT ," +
            terminalNo + " TEXT ," +
            approvalNo + " TEXT ," +
            procedureAmount + " TEXT ," +
            callDate + " TEXT ," +
            companyCode + " TEXT ," +
            category + " TEXT ," +
            callerId + " TEXT ," +
            approvalDate + " TEXT ," +
            hospitalCode + " TEXT ," +
            procedureDesc + " TEXT ," +
            notes + " TEXT ," +
            dateAdmitted + " TEXT ," +
            docName + " TEXT ," +
            docSpec + " TEXT ," +
            docSpecCode + " TEXT ," +
            hospitalName + " TEXT ," +
            schedule + " TEXT ," +
            room + " TEXT )  ";


    private String createSpecialization = " CREATE TABLE " +
            specTable + " ( " +
            specializationCode + " TEXT ," +
            specializationDescription + " TEXT )  ";


    private String createCityStatement = "CREATE TABLE " +
            cityTable + " ( " +
            regionCode + " TEXT ," +
            provinceCode + " TEXT ," +
            cityName + " TEXT ," +
            countryCode + " TEXT ," +
            cityCode + " TEXT ," +
            countryName + " TEXT )  ";

    private String createProvinceStatement = "CREATE TABLE " +
            provinceTable + " ( " +
            provinceCode + " TEXT ," +
            provinceName + " TEXT ," +
            regionCode + " TEXT )";

    private String createHospitalStatement = "CREATE TABLE " +
            hospTable + " ( " +
            hospitalCode + " TEXT ," +
            hospitalName + " TEXT ," +
            keyword + " TEXT ," +
            alias + " TEXT ," +
            category + " TEXT ," +
            coordinator + " TEXT ," +
            excluded + " TEXT ," +
            city + " TEXT ," +
            province + " TEXT ," +
            region + " TEXT ," +
            phoneNo + " TEXT ," +
            faxno + " TEXT ," +
            contactPerson + " TEXT ," +
            streetAddress + " TEXT )";

    private String createDoctorStatement = "CREATE TABLE " +
            doctable + " ( " +
            region + " TEXT ," +
            streetAddress + " TEXT ," +
            specialRem + " TEXT ," +
            docFname + " TEXT ," +
            specDesc + " TEXT ," +
            doctorCode + " TEXT ," +
            hospRemarks + " TEXT ," +
            docMname + " TEXT ," +
            vat + " TEXT ," +
            wtax + " TEXT ," +
            remarks + " TEXT ," +
            city + " TEXT ," +
            gracePeriod + " TEXT ," +
            phoneNo + " TEXT ," +
            specCode + " TEXT ," +
            schedule + " TEXT ," +
            faxno + " TEXT ," +
            province + " TEXT ," +
            hospitalName + " TEXT ," +
            docLname + " TEXT ," +
            hospitalCode + " TEXT ," +
            contactPerson + " TEXT ," +
            roomBoard + " TEXT ," +
            remarks2 + " TEXT ," +
            room + " TEXT )";


    public DatabaseHandler(Context context) {
        super(context, databaseName, null, version);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(createHospitalStatement);
        Log.e(TAG, createHospitalStatement);

        db.execSQL(createDoctorStatement);
        Log.e(TAG, createDoctorStatement);

        db.execSQL(createLoaRequest);
        Log.e(TAG, createLoaRequest);

        String data = "";
        String filterNull = "";
        try {
            filterNull = SharedPref.getStringValue(SharedPref.USER, SharedPref.FIRST_TIME, context);
            if (!filterNull.equals("null"))
                data = filterNull;
        } catch (Exception e) {

        }
        Log.d("LOG_IN", data);
        if (data.equals("TRUE") || data.equals("")) {
            db.execSQL(createProvinceStatement);
            Log.e(TAG, createProvinceStatement);
        }

        if (data.equals("TRUE") || data.equals("")) {
            db.execSQL(createCityStatement);
            Log.e(TAG, createCityStatement);
        }

        if (data.equals("TRUE") || data.equals("")) {
            db.execSQL(createSpecialization);
            Log.e(TAG, createSpecialization);
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    public void insertLoa(LoaList loa) {
        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(id, loa.getId());
        values.put(approvalNo, loa.getApprovalNo());
        values.put(batchCode, loa.getBatchCode());
        values.put(callerId, loa.getCallerId());
        values.put(callTypeId, loa.getCallerId());
        values.put(memberCode, loa.getMemberCode());
        values.put(hospitalCode, loa.getHospitalCode());
        values.put(companyCode, loa.getCompanyCode());
        values.put(doctorCode, loa.getDoctorCode());
        values.put(diagnosisCode, loa.getDiagnosisCode());
        values.put(procedureCode, loa.getProcedureCode());
        values.put(type, loa.getType());
        values.put(room, loa.getRoom());
        values.put(dateAdmitted, loa.getDateAdmitted());
        values.put(diagnosis, loa.getDiagnosis());
        values.put(procedureDesc, loa.getProcedureDesc());
        values.put(procedureAmount, loa.getProcedureAmount());
        values.put(actionTaken, loa.getActionTaken());
        values.put(updatedBy, loa.getUpdatedBy());
        values.put(updatedDate, loa.getUpdatedDate());
        values.put(remarks, loa.getRemarks());
        values.put(runningBill, loa.getRunningBill());
        values.put(notes, loa.getNotes());
        values.put(reason, loa.getReason());
        values.put(category, loa.getCategory());
        values.put(memLname, loa.getMemLname());
        values.put(memFname, loa.getMemFname());
        values.put(memMi, loa.getMemMi());
        values.put(memCompany, loa.getMemCompany());
        values.put(terminalNo, loa.getTerminalNo());
        values.put(callDate, loa.getCallDate());
        values.put(status, loa.getStatus());
        values.put(approvalDate, loa.getApprovalDate());


        values.put(docName, "");
        values.put(docSpec, "");
        values.put(docSpecCode, "");
        values.put(hospitalName, "");
        values.put(schedule, "");

        createSuccessful = db.insert(loaTable, null, values) > 0;
        db.close();

        if (createSuccessful) {
            Log.e("LOAD_LOA", loa.getId() + " created.");
        }
    }


    public void insertCity(Cities p) {
        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(regionCode, p.getRegionCode());
        values.put(provinceCode, p.getProvinceCode());
        values.put(cityCode, p.getCityCode());
        values.put(cityName, p.getCityName());
        values.put(countryCode, p.getCountryCode());
        values.put(countryName, p.getCountryName());

        createSuccessful = db.insert(cityTable, null, values) > 0;
        db.close();


        if (createSuccessful) {
            Log.e(TAG, cityTable + " created.");
        }
    }

    public void insertProvince(Provinces p) {

        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(provinceCode, p.getProvinceCode());
        values.put(provinceName, p.getProvinceName());
        values.put(regionCode, p.getRegionCode());

        createSuccessful = db.insert(provinceTable, null, values) > 0;

        db.close();

        if (createSuccessful) {
            Log.e(TAG, provinceName + " created.");
        }
    }


    public void insertSpecialization(Specializations specializations) {
        boolean createsuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(specializationCode, specializations.getSpecializationCode());
        values.put(specializationDescription, specializations.getSpecializationDescription());

        createsuccessful = db.insert(specTable, null, values) > 0;

        db.close();

        if (createsuccessful) {
            Log.e(TAG, specializationDescription + " created.");
        }

    }

    public void insertHospital(HospitalList hosp) {


        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(hospitalCode, hosp.getHospitalCode());
        values.put(hospitalName, hosp.getHospitalName());
        values.put(keyword, hosp.getKeyword());
        values.put(alias, hosp.getAlias());
        values.put(category, hosp.getCategory());
        values.put(coordinator, hosp.getCoordinator());
        values.put(streetAddress, hosp.getStreetAddress());
        values.put(city, hosp.getCity());
        values.put(province, hosp.getProvince());
        values.put(region, hosp.getRegion());
        values.put(phoneNo, hosp.getPhoneNo());
        values.put(faxno, hosp.getFaxno());
        values.put(contactPerson, hosp.getContactPerson());
        values.put(excluded, "false");

        createSuccessful = db.insert(hospTable, null, values) > 0;

        db.close();

        if (createSuccessful) {
            Log.e(TAG, hospitalName + " created.");
        }

    }


    public void insertDoctorList(GetDoctorsToHospital doc) {

        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(region, doc.getRegion());
        values.put(streetAddress, doc.getStreetAddress());
        values.put(specialRem, doc.getSpecialRem());
        values.put(docFname, doc.getDocFname());
        values.put(specDesc, doc.getSpecDesc());
        values.put(hospRemarks, doc.getHospRemarks());
        values.put(doctorCode, doc.getDoctorCode());
        values.put(docMname, doc.getDocMname());
        values.put(vat, doc.getVat());
        values.put(wtax, doc.getWtax());
        values.put(remarks, doc.getRemarks());
        values.put(city, doc.getCity());
        values.put(gracePeriod, doc.getGracePeriod());
        values.put(phoneNo, doc.getPhoneNo());
        values.put(specCode, doc.getSpecCode());
        values.put(schedule, doc.getSchedule());
        values.put(faxno, doc.getFaxno());
        values.put(province, doc.getProvince());
        values.put(hospitalName, doc.getHospitalName());
        values.put(docLname, doc.getDocLname());
        values.put(hospitalCode, doc.getHospitalCode());
        values.put(contactPerson, doc.getContactPerson());
        values.put(roomBoard, doc.getRoomBoard());
        values.put(remarks2, doc.getRemarks());
        values.put(room, doc.getRoom());


        createSuccessful = db.insert(doctable, null, values) > 0;

        db.close();

        if (createSuccessful) {
            Log.e(TAG, docLname + " created.");
        }

    }


    /**
     * @param sort_by           == ORDER BY
     * @param status_sort       = EXAMPLE = EXPIRED , APPROVED
     * @param service_type_sort = EXAMPLE CONSULTATION
     * @param date_start_sort   = START DATE FILTER
     * @param date_end_sort     = END DATE FILTER
     * @param doctor_sort       = LIST OF DOCTORS TO SEARCH
     * @param hospital_sort     = LIST HOSPITAL TO SEARCH
     * @return = list of filtered data of loa list
     */
    public ArrayList<LoaFetch> retrieveLoa(String sort_by, String status_sort, String service_type_sort,
                                           String date_start_sort, String date_end_sort,
                                           ArrayList<SimpleData> doctor_sort,
                                           ArrayList<SimpleData> hospital_sort) {

        ArrayList<LoaFetch> array = new ArrayList<>();
        String sql = "SELECT * FROM " + loaTable;
        sql += " WHERE " + " " + status + "  LIKE '%" + status_sort + "%' ";
        sql += " AND " + " " + remarks + "  LIKE '%" + service_type_sort + "%' ";

//TELL IF END OR START DATE HAS DATA
        if (date_start_sort.equals("") || date_end_sort.equals("")) {
            //DO NOTHING
        } else
            sql += " AND " + approvalDate + " BETWEEN date('" + date_start_sort + "') AND date('" + date_end_sort + "')";


        if (doctor_sort.size() != 0) {

            sql += " AND (";

            for (int doc = 0; doc < doctor_sort.size(); doc++) {
                if (doctor_sort.get(doc).getSelected().equals("true")) {
                    sql += " " + docName + "  LIKE '%" + doctor_sort.get(doc).getHospital() + "%'    OR ";
                }
            }

            sql = sql.substring(0, sql.length() - 6);

            sql += " ) ";
        }

        Log.d("HOSP_COUNT", hospital_sort.size() + "");
        if (hospital_sort.size() != 0) {

            sql += " AND (";

            for (int hosp = 0; hosp < hospital_sort.size(); hosp++) {
                if (hospital_sort.get(hosp).getSelected().equals("true")) {
                    sql += " " + hospitalName + "  LIKE '%" + hospital_sort.get(hosp).getHospital() + "%'    OR ";
                }
            }

            sql = sql.substring(0, sql.length() - 6);

            sql += " ) ";
        }

        sql += " ORDER BY " + sort_by + " ASC ";
        database = getReadableDatabase();
        cursor = database.rawQuery(sql, null);
        Log.e(TAG, cursor.getCount() + "");
        Log.e(TAG, sql + "");

        array.addAll(getDataLoa(cursor));


        cursor.close();
        database.close();
        return array;
    }

    private ArrayList<LoaFetch> getDataLoa(Cursor cursor) {
        ArrayList<LoaFetch> loa = new ArrayList<>();


        if (cursor.moveToFirst()) {
            do {
                LoaFetch p = new LoaFetch(
                        getCursor(cursor, procedureCode),
                        getCursor(cursor, batchCode),
                        getCursor(cursor, reason),
                        getCursor(cursor, memCompany),
                        getCursor(cursor, diagnosis),
                        getCursor(cursor, remarks),
                        getCursor(cursor, diagnosisCode),
                        getCursor(cursor, type),
                        getCursor(cursor, updatedBy),
                        getCursor(cursor, callTypeId),
                        getCursor(cursor, id),
                        getCursor(cursor, runningBill),
                        getCursor(cursor, memMi),
                        getCursor(cursor, memberCode),
                        getCursor(cursor, memFname),
                        getCursor(cursor, memLname),
                        getCursor(cursor, doctorCode),
                        getCursor(cursor, actionTaken),
                        getCursor(cursor, status),
                        getCursor(cursor, updatedBy),
                        getCursor(cursor, terminalNo),
                        getCursor(cursor, approvalNo),
                        getCursor(cursor, procedureAmount),
                        getCursor(cursor, callDate),
                        getCursor(cursor, companyCode),
                        getCursor(cursor, category),
                        getCursor(cursor, callerId),
                        getCursor(cursor, approvalDate),
                        getCursor(cursor, hospitalCode),
                        getCursor(cursor, procedureDesc),
                        getCursor(cursor, notes),
                        getCursor(cursor, dateAdmitted),
                        getCursor(cursor, room),
                        getCursor(cursor, docName),
                        getCursor(cursor, docSpec),
                        getCursor(cursor, docSpecCode),
                        getCursor(cursor, hospitalName),
                        getCursor(cursor, schedule)


                );

                loa.add(p);
                Log.d("LOAD_DATE", getCursor(cursor, id));
            } while (cursor.moveToNext());

        }


        return loa;
    }

    private String getCursor(Cursor cursor, String data) {
        return cursor.getString(cursor.getColumnIndex(data));
    }


    public ArrayList<Provinces> retrieveProvince() {
        ArrayList<Provinces> array = new ArrayList<>();
        String sql = "SELECT * FROM " + provinceTable;
        sql += " ORDER BY " + provinceName + " ASC ";
        database = getReadableDatabase();
        cursor = database.rawQuery(sql, null);
        Log.e(TAG, cursor.getCount() + "");

        if (cursor.moveToFirst()) {
            do {
                Provinces p = new Provinces(
                        cursor.getString(cursor.getColumnIndex(regionCode)),
                        cursor.getString(cursor.getColumnIndex(provinceCode)),
                        cursor.getString(cursor.getColumnIndex(provinceName))
                );

                array.add(p);
            } while (cursor.moveToNext());

        }


        cursor.close();
        database.close();
        return array;
    }


    public ArrayList<CitiesAdapter> retrieveCity(String search) {
        ArrayList<CitiesAdapter> array = new ArrayList<>();

        String sql = "";


        if (search.equals(Constant.QUERY_ALL)) {
            sql = "SELECT * FROM " + cityTable;
        } else {
            sql = "SELECT * FROM " + cityTable;
            sql += " WHERE " + " " + provinceCode + "  LIKE '%" + search + "%' ";
        }
        sql += " ORDER BY " + cityName + " ASC ";


        database = getReadableDatabase();
        cursor = database.rawQuery(sql, null);
        Log.e(TAG, cursor.getCount() + "");

        if (cursor.moveToFirst()) {
            do {
                CitiesAdapter p = new CitiesAdapter(
                        cursor.getString(cursor.getColumnIndex(regionCode)),
                        cursor.getString(cursor.getColumnIndex(countryName)),
                        cursor.getString(cursor.getColumnIndex(provinceCode)),
                        cursor.getString(cursor.getColumnIndex(cityName)),
                        cursor.getString(cursor.getColumnIndex(countryCode)),
                        cursor.getString(cursor.getColumnIndex(cityCode)),
                        "false"
                );

                array.add(p);
            } while (cursor.moveToNext());

        }


        cursor.close();
        database.close();
        return array;
    }


    public ArrayList<SpecsAdapter> retrieveSpecs() {
        ArrayList<SpecsAdapter> array = new ArrayList<>();
        String sql = "SELECT * FROM " + specTable;
        sql += " ORDER BY " + specializationDescription + " ASC ";
        database = getReadableDatabase();
        cursor = database.rawQuery(sql, null);
        Log.e(TAG, cursor.getCount() + "");

        if (cursor.moveToFirst()) {
            do {
                SpecsAdapter p = new SpecsAdapter(
                        cursor.getString(cursor.getColumnIndex(specializationCode)),
                        cursor.getString(cursor.getColumnIndex(specializationDescription)),
                        "false"
                );

                Log.e(TAG, "objectName: " + cursor.getColumnIndex(specializationDescription));

                array.add(p);
            } while (cursor.moveToNext());

        }


        cursor.close();
        database.close();
        return array;
    }


    public ArrayList<GetDoctorsToHospital> retrieveDoctor(String searchTerm, ArrayList<SpecsAdapter> selectedSpec, String sort_by, String room_number) {

        ArrayList<GetDoctorsToHospital> doc = new ArrayList<>();
        database = getReadableDatabase();


        String sql = "";
        sql += "SELECT * FROM " + doctable;
        sql += " WHERE (" + "UPPER(" + docLname + ") LIKE '%" + searchTerm + "%'";
        sql += " OR " + "UPPER(" + specDesc + ") LIKE '%" + searchTerm + "%'   ";
        sql += " OR " + "UPPER(" + docFname + ") LIKE '%" + searchTerm + "%'   ";
        sql += " AND  UPPER(" + room + ") LIKE '%" + room_number.toUpperCase() + "%' ) ";
        if (selectedSpec.size() != 0) {
            sql += " AND (";
            for (int x = 0; x < selectedSpec.size(); x++) {
                sql += "  " + specCode + " = '" + selectedSpec.get(x).getSpecializationCode() + "' " + "  OR  ";
            }
            //remove and
            sql = sql.substring(0, sql.length() - 6);
            sql += " ) ";
        }


        sql += " ORDER BY " + sort_by + "  ASC ";
        Log.e(TAG, "objectName: " + sql);
        cursor = database.rawQuery(sql, null);
        Log.e(TAG, "Count " + cursor.getCount());
        doc.addAll(getDoctoList(cursor));

        database.close();
        return doc;
    }


    private ArrayList<GetDoctorsToHospital> getDoctoList(Cursor cursor) {

        ArrayList<GetDoctorsToHospital> docs = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                GetDoctorsToHospital setDocs = new GetDoctorsToHospital();
                setDocs.setRegion(cursor.getString(cursor.getColumnIndex(region)));
                setDocs.setStreetAddress(cursor.getString(cursor.getColumnIndex(streetAddress)));
                setDocs.setSpecialRem(cursor.getString(cursor.getColumnIndex(specialRem)));
                setDocs.setDocFname(cursor.getString(cursor.getColumnIndex(docFname)));
                setDocs.setSpecDesc(cursor.getString(cursor.getColumnIndex(specDesc)));
                setDocs.setHospRemarks(cursor.getString(cursor.getColumnIndex(hospRemarks)));
                setDocs.setDoctorCode(cursor.getString(cursor.getColumnIndex(doctorCode)));
                setDocs.setDocMname(cursor.getString(cursor.getColumnIndex(docMname)));
                setDocs.setVat(cursor.getString(cursor.getColumnIndex(vat)));
                setDocs.setDocLname(cursor.getString(cursor.getColumnIndex(docLname)));


                setDocs.setWtax(cursor.getString(cursor.getColumnIndex(wtax)));
                setDocs.setRemarks(cursor.getString(cursor.getColumnIndex(remarks)));
                setDocs.setCity(cursor.getString(cursor.getColumnIndex(city)));
                setDocs.setGracePeriod(cursor.getString(cursor.getColumnIndex(gracePeriod)));
                setDocs.setPhoneNo(cursor.getString(cursor.getColumnIndex(phoneNo)));
                setDocs.setSpecCode(cursor.getString(cursor.getColumnIndex(specCode)));
                setDocs.setSchedule(cursor.getString(cursor.getColumnIndex(schedule)));
                setDocs.setFaxno(cursor.getString(cursor.getColumnIndex(faxno)));
                setDocs.setProvince(cursor.getString(cursor.getColumnIndex(province)));
                setDocs.setHospitalCode(cursor.getString(cursor.getColumnIndex(hospitalCode)));
                setDocs.setContactPerson(cursor.getString(cursor.getColumnIndex(contactPerson)));
                setDocs.setRoomBoard(cursor.getString(cursor.getColumnIndex(roomBoard)));
                setDocs.setRemarks2(cursor.getString(cursor.getColumnIndex(remarks2)));
                setDocs.setRoom(cursor.getString(cursor.getColumnIndex(room)));

                docs.add(setDocs);
            } while (cursor.moveToNext());
        }
        cursor.close();

        return docs;
    }


    private ArrayList<HospitalList> getHospList(Cursor cursor) {
        ArrayList<HospitalList> hospitals = new ArrayList<>();


        if (cursor.moveToFirst()) {


            do {


                HospitalList hospital = new HospitalList();
                hospital.setAlias(cursor.getString(cursor.getColumnIndex(alias)));
                hospital.setCategory(cursor.getString(cursor.getColumnIndex(category)));
                hospital.setCoordinator(cursor.getString(cursor.getColumnIndex(coordinator)));
                hospital.setHospitalCode(cursor.getString(cursor.getColumnIndex(hospitalCode)));
                hospital.setHospitalName(cursor.getString(cursor.getColumnIndex(hospitalName)));
                hospital.setKeyword(cursor.getString(cursor.getColumnIndex(keyword)));
                hospital.setStreetAddress(cursor.getString(cursor.getColumnIndex(streetAddress)));

                hospital.setCity(cursor.getString(cursor.getColumnIndex(city)));
                hospital.setProvince(cursor.getString(cursor.getColumnIndex(province)));
                hospital.setRegion(cursor.getString(cursor.getColumnIndex(region)));
                hospital.setPhoneNo(cursor.getString(cursor.getColumnIndex(phoneNo)));
                hospital.setFaxno(cursor.getString(cursor.getColumnIndex(faxno)));
                hospital.setContactPerson(cursor.getString(cursor.getColumnIndex(contactPerson)));

                hospitals.add(hospital);
                Log.e(TAG, "sql: " + cursor.getString(cursor.getColumnIndex(city)));
            } while (cursor.moveToNext());


        }


        cursor.close();
        return hospitals;
    }


    public void dropHospital() {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + hospTable;

        db.execSQL(sql);

    }


    public void dropDoctor() {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + doctable;

        db.execSQL(sql);
        db.close();
    }

    public void dropProvince() {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + provinceTable;

        db.execSQL(sql);
        db.close();
    }

    public void dropCity() {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + cityTable;

        db.execSQL(sql);
        db.close();
    }

    public void dropLoa() {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + loaTable;

        db.execSQL(sql);
        db.close();
    }

    public void dropSpecialization() {

        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM " + specTable;

        db.execSQL(sql);
        db.close();
    }


    public void getAlltoFalse() {

        database = getReadableDatabase();

        String sql = "";
        sql += "SELECT * FROM " + hospTable;
        sql += " WHERE " + excluded + " = 'true'";
        Log.e(TAG, "objectName: " + sql);
        cursor = database.rawQuery(sql, null);
        getFalse(cursor);
        database.close();

    }

    private void getFalse(Cursor cursor) {
        String gHosp = "";
        if (cursor.moveToFirst()) {


            do {
                gHosp = cursor.getString(cursor.getColumnIndex(hospitalCode));
                setExcludedToFalse(gHosp);
                Log.d("HOSP-ID", gHosp);
            } while (cursor.moveToNext());


        }

        cursor.close();
    }


    public void setExcludedToTrue(String ID) {

        String sql = "";
        SQLiteDatabase db = this.getWritableDatabase();
        sql = "UPDATE " + hospTable + " SET " + excluded + " = 'true' WHERE " + hospitalCode + " = '" + ID + "'";
        db.execSQL(sql);
        Log.d("ID", sql);
        db.close();
    }


    public void setExcludedToFalse(String ID) {

        String sql = "";
        SQLiteDatabase db = this.getWritableDatabase();
        sql = "UPDATE " + hospTable + " SET " + excluded + " = 'false' WHERE " + hospitalCode + " = '" + ID + "'";
        db.execSQL(sql);
        Log.d("ID", sql);
        db.close();
    }


    public ArrayList<HospitalList> retrieveHospital(String provinceCode, String data_sort, ArrayList<CitiesAdapter> selectedCity, String data) {
        ArrayList<HospitalList> arrayList = new ArrayList<>();
        database = getReadableDatabase();

        String s = data.toUpperCase().replace("'", "`");
        String sql = "";
        sql += "SELECT * FROM " + hospTable;
        sql += " WHERE " + hospitalName + "  = '" + s + "' ";

        sql += " AND ( " + excluded + " = 'false' ) ";
        if (!provinceCode.equals(Constant.QUERY_ALL)) {
            sql += " AND ";
            sql += province + "  LIKE '%" + provinceCode + "%' ";
        }
        if (selectedCity.size() != 0) {
            sql += " AND ";
            for (int x = 0; x < selectedCity.size(); x++) {
                sql += " ( " + city + " = '" + selectedCity.get(x).getCityName() + "' ) " + "  OR  ";
            }
            //remove and
            sql = sql.substring(0, sql.length() - 6);
        }
        sql += " ORDER BY " + data_sort + " ASC ";
        Log.e(TAG, "sql: " + sql);

        cursor = database.rawQuery(sql, null);
        Log.e(TAG, cursor.getCount() + "");
        arrayList.addAll(getHospList(cursor));


        ///SQL 2
        String sql2 = "";
        sql2 += "SELECT * FROM " + hospTable;
        sql2 += " WHERE " + hospitalName + "  LIKE '" + s + "%' AND " + hospitalName + " <> '" + s + "'";
        //AND " + hospitalName + " <> '" + s + "' ";
        sql2 += " AND ( " + excluded + " = 'false' ) ";
        if (!provinceCode.equals(Constant.QUERY_ALL)) {
            sql2 += " AND ";
            sql2 += province + "  LIKE '%" + provinceCode + "%' ";
        }
        if (selectedCity.size() != 0) {
            sql2 += " AND ";
            for (int x = 0; x < selectedCity.size(); x++) {
                sql2 += " ( " + city + " = '" + selectedCity.get(x).getCityName() + "' ) " + "  OR  ";
            }
            //remove and
            sql2 = sql2.substring(0, sql2.length() - 6);
        }
        sql2 += " ORDER BY " + data_sort + " ASC ";
        Log.e(TAG, "sql: " + sql2);

        cursor = database.rawQuery(sql2, null);
        Log.e(TAG, cursor.getCount() + "");
        arrayList.addAll(getHospList(cursor));


        ///SQL 3
        String sql3 = "";
        sql3 += "SELECT * FROM " + hospTable;
        sql3 += " WHERE " + hospitalName + "  LIKE '%" + s + "' AND " + hospitalName + " <> '" + s + "'";
        //AND " + hospitalName + " <> '" + s + "' AND " + hospitalName + "  <> '" + s + " %'";
        sql3 += " AND ( " + excluded + " = 'false' ) ";
        if (!provinceCode.equals(Constant.QUERY_ALL)) {
            sql3 += " AND ";
            sql3 += province + "  LIKE '%" + provinceCode + "%' ";
        }
        if (selectedCity.size() != 0) {
            sql3 += " AND ";
            for (int x = 0; x < selectedCity.size(); x++) {
                sql3 += " ( " + city + " = '" + selectedCity.get(x).getCityName() + "' ) " + "  OR  ";
            }
            //remove and
            sql3 = sql3.substring(0, sql3.length() - 6);
        }
        sql += " ORDER BY " + data_sort + " ASC ";
        Log.e(TAG, "sql: " + sql3);

        cursor = database.rawQuery(sql3, null);
        Log.e(TAG, cursor.getCount() + "");
        arrayList.addAll(getHospList(cursor));

//        ///SQL 4
//        String sql4 = "";
//        sql4 += "SELECT * FROM " + hospTable;
//        sql4 += " WHERE " + hospitalName + "  LIKE '% " + s + " %' " +
//         " AND " + hospitalName + " <> '" + s + "' AND " + hospitalName + "  <> '" + s + " %' AND  " + hospitalName + " <> '% " + s + "'";
//        sql4 += " AND ( " + excluded + " = 'false' ) ";
//        if (!provinceCode.equals(Constant.QUERY_ALL)) {
//            sql4 += " AND ";
//            sql4 += province + "  LIKE '%" + provinceCode + "%' ";
//        }
//        if (selectedCity.size() != 0) {
//            sql4 += " AND ";
//            for (int x = 0; x < selectedCity.size(); x++) {
//                sql4 += " ( " + city + " = '" + selectedCity.get(x).getCityName() + "' ) " + "  AND  ";
//            }
//            //remove and
//            sql4 = sql4.substring(0, sql4.length() - 6);
//        }
//        sql4 += " ORDER BY '" + data_sort + "' ASC ";
//        Log.e(TAG, "sql: " + sql4);
//
//        cursor = database.rawQuery(sql4, null);
//        Log.e(TAG, cursor.getCount() + "");
//        arrayList.addAll(getHospList(cursor));

        cursor.close();
        database.close();

        return arrayList;
    }


    public String getHospitalName(String searchCode) {
        String sql = " SELECT * FROM " + hospTable +
                " WHERE " + hospitalCode + " = '" + searchCode + "'";
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(sql, null);

        String gHosp = "";
        if (cursor.moveToFirst()) {


            do {
                gHosp = cursor.getString(cursor.getColumnIndex(hospitalName));
                Log.d("HOSP-ID", gHosp);
            } while (cursor.moveToNext());


        }


        return gHosp;
    }


//    docName + " TEXT ," +
//    docSpec + " TEXT ," +
//    docSpecCode + " TEXT ," +

    public void setDoctorToLoaReq(
            String ID,
            String getDocName,
            String getDocSpec,
            String getDocSpecCode,
            String getSched,
            String getRoom) {

        String sql = "";
        SQLiteDatabase db = this.getWritableDatabase();
        sql = "UPDATE " + loaTable +
                " SET " + docName + " = '" + getDocName +
                "', " + docSpec + " = '" + getDocSpec +
                "'," + docSpecCode + " = '" + getDocSpecCode +
                "'," + schedule + " = '" + getSched +
                "'," + room + " = '" + getRoom +
                "' WHERE " + id + " = '" + ID + "'";
        db.execSQL(sql);
        Log.d("ID", sql);
        Log.d("ID", getDocName);
        Log.d("ID", getDocSpec);
        Log.d("ID", getDocSpecCode);
        db.close();
    }

    public void setHospitalToLoaReq(String ID, String hospName) {

        String sql = "";
        SQLiteDatabase db = this.getWritableDatabase();

        sql = "UPDATE " + loaTable +
                " SET " + hospitalName + " = '" + hospName +
                "' WHERE " + id + " = '" + ID + "'";
        db.execSQL(sql);
        Log.d("ID", sql);
        Log.d("ID", hospitalName);

    }

    public ArrayList<String> getDateRange(String start, String end) {
        ArrayList<String> array = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor;
///SELECT * FROM Categories WHERE DateCreated BETWEEN '2012-03-11 00:00:00' AND '2015-05-12 23:59:59'
        String sql = " SELECT * FROM " + loaTable +
                " WHERE " + approvalDate + " BETWEEN date('" + start + "') AND date('" + end + "')";
        Log.d("SQL_DATE", sql);

        cursor = db.rawQuery(sql, null);
        Log.d("SQL_DATE_count", cursor.getCount() + "");
        if (cursor.moveToFirst()) {


            do {
                cursor.getString(cursor.getColumnIndex(hospitalName));
                Log.d("HOSP-NAME", cursor.getString(cursor.getColumnIndex(id)));

                array.add(cursor.getString(cursor.getColumnIndex(hospitalName)));
            } while (cursor.moveToNext());


        }

        cursor.close();


        return array;
    }
}

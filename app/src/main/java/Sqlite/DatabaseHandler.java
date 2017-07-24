package Sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;


import database.entity.Doctor;
import model.Cities;
import model.CitiesAdapter;
import model.GetDoctorsToHospital;
import model.HospitalList;
import model.LoaFetch;
import model.LoaList;
import model.Provinces;
import model.ProvincesAdapter;
import model.SimpleData;
import model.Specializations;
import model.SpecsAdapter;
import services.model.HospitalsToDoctor;
import services.model.Procedure;
import services.model.Test;
import timber.log.Timber;
import utilities.Constant;
import utilities.DateConverter;
import utilities.SharedPref;

/**
 * Created by mpx-pawpaw on 1/3/17.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    ////CHECK DOCTORS TABLE
    private Context context;
    //private SQLiteDatabase database;
    // private Cursor cursor;

    String primaryHosp = "MEDICARD PHILIPPINES, INC.";
    String primaryHosp2 = "MEDICard LIFESTYLE CENTER";
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

    private String withProvider = "withProvider";
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
    private String primaryComplaint = "primaryComplaint";
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

    // added new column

    private static final int version = 6;

    //
    private String createLoaRequest = " CREATE TABLE " +
            loaTable + " ( " +
            primaryComplaint + " TEXT ," +
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
            room + " TEXT, " +
            withProvider + " INTEGER DEFAULT 0 )";


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

    private static DatabaseHandler instance;


    public static synchronized DatabaseHandler getInstance(Context context) {

        if (instance == null) {
            instance = new DatabaseHandler(context.getApplicationContext());
        }
        return instance;
    }

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

        db.execSQL(Doctor.getTableStructure());
        db.execSQL(Procedure.getTableStructure());
        db.execSQL(Test.getTableStructure());

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
        Timber.d("Upgrading the database ... oldVersion : %s, newVersion %s", oldVersion, newVersion);

        db.execSQL("DROP TABLE IF EXISTS " + Doctor.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Procedure.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + Test.TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + hospTable);
        db.execSQL("DROP TABLE IF EXISTS " + doctable);
        db.execSQL("DROP TABLE IF EXISTS " + loaTable);
//        db.execSQL("");
        onCreate(db);
    }


    public void insertLoa(services.model.LoaList loa) {
        boolean createSuccessful = false;

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(id, loa.getId());
        values.put(primaryComplaint, loa.getPrimaryComplaint());
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
        values.put(status, testStatusExpiration(loa.getRemarks(), loa.getStatus(), loa.getApprovalDate()));
        values.put(approvalDate, loa.getApprovalDate());


        values.put(docName, "");
        values.put(docSpec, "");
        values.put(docSpecCode, "");
        values.put(hospitalName, "");
        values.put(schedule, "");

        values.put(withProvider, (loa.getWithProvider() ? 1 : 0) );

        Timber.d("loa actual value : %s", loa.getWithProvider());
        Timber.d("with provider is inserted %s", (loa.getWithProvider() ? 1 : 0));

        createSuccessful = db.insert(loaTable, null, values) > 0;


        if (createSuccessful) {
            Log.e("LOAD_LOA", loa.getId() + " created.");
        }
    }

    private String testStatusExpiration(String remarks, String status, String approvalDate) {
        String data = "";

        if (status.equals(Constant.CANCELLED)) {
            data = Constant.CANCELLED;
        } else if (remarks.contains("AVAILED")) {
            data = status;
        } else if (DateConverter.testExpiration(DateConverter.convertDate(approvalDate, new SimpleDateFormat("yyyy-MM-dd HH:mm")))) {
            if (remarks.contains("CONSULTATION") && status.contains("APPROVED")) {
//                data = "OUTSTANDING";
                data = "CONFIRMED";
            } else {
                data = status;
            }
        } else {
            data = "EXPIRED";
        }


        return data;
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
     * @param searchTerm        = query term
     * @return = list of filtered data of loa list
     */
    public ArrayList<LoaFetch> retrieveLoa(String sort_by, String status_sort, String service_type_sort,
                                           String date_start_sort, String date_end_sort,
                                           ArrayList<SimpleData> doctor_sort,
                                           ArrayList<SimpleData> hospital_sort, String searchTerm) {
        SQLiteDatabase database;
        Cursor cursor;
        ArrayList<LoaFetch> array = new ArrayList<>();
        String sql = "SELECT * FROM " + loaTable;
        sql += " WHERE (" + "UPPER(" + hospitalName + ") LIKE '%" + searchTerm + "%'";
        sql += " OR " + "UPPER(" + docName + ") LIKE '%" + searchTerm + "%'   )";
        sql += " AND " + status + "  LIKE '%" + status_sort + "%' ";

        if (!service_type_sort.equals(""))
            sql += " AND " + " " + remarks + "  = '" + service_type_sort.toUpperCase() + "' ";

        //TELL IF END OR START DATE HAS DATA
        if (date_start_sort.equals("") || date_end_sort.equals("")) {
            //DO NOTHING
            ///(Date(column_date) >= Date (2000-01-01) AND Date(column_date) <= Date (2050-01-01))
        } else if (date_start_sort.equals(date_end_sort)) {
            sql += "AND   DATE( " + approvalDate + ") == DATE('" + date_start_sort + "')";
        } else {
            sql += " AND ( DATETIME(" + approvalDate + ") >= DATETIME('" + date_start_sort + "') AND DATETIME(" + approvalDate + ") <= DATETIME('" + date_end_sort + "'))";
        }

        sql += getDataSorted(doctor_sort, true);
        sql += getDataSorted(hospital_sort, false);

        sql += " ORDER BY " + sort_by;

        //ONLY DESC ON DATE REQUEST
        // if (sort_by.contains("DATETIME(dateAdmitted)"))
        if (sort_by.contains("approvalNo"))
            sql += " DESC";
        else
            sql += " ASC";


        database = getReadableDatabase();
        cursor = database.rawQuery(sql, null);
        Log.e(TAG, cursor.getCount() + "");
        Log.e(TAG, sql + "");

        array.addAll(getDataLoa(cursor));

        return array;
    }


    private String getDataSorted(ArrayList<SimpleData> doctor_sort, boolean fromDoctor) {
        String sql = "";
        String seeker = "";
        boolean isDoctorAvailable = false;

        if (fromDoctor)
            seeker = docName;
        else
            seeker = hospitalName;
        if (doctor_sort.size() != 0) {


            for (int doc = 0; doc < doctor_sort.size(); doc++) {

                if (doc == 0)
                    sql += "   AND (";


                if (doctor_sort.get(doc).getSelected().equals("true")) {
                    isDoctorAvailable = true;
                    sql += " " + seeker + "  LIKE '%" + doctor_sort.get(doc).getHospital() + "%'    OR";
                }

            }


            if (isDoctorAvailable) {
                Log.d("ENDERS_GAME", isDoctorAvailable + "");
                sql = sql.substring(0, sql.length() - 3);
                sql += " ) ";

            } else {
                Log.d("ENDERS_GAME", isDoctorAvailable + "");
                sql = sql.substring(0, sql.length() - 6);

            }


        }

        return sql;
    }

    private ArrayList<LoaFetch> getDataLoa(Cursor cursor) {
        ArrayList<LoaFetch> loa = new ArrayList<>();


        if (cursor.moveToFirst()) {
            do {
                LoaFetch p = new LoaFetch(
                        getCursor(cursor, primaryComplaint),
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
                        getCursor(cursor, schedule),
                        getIntValue(cursor, withProvider)
                );

                loa.add(p);
                Log.d("LOAD_DATE", getCursor(cursor, id));
                Log.d("LOAD_DATE", getCursor(cursor, dateAdmitted));
                Log.d("LOAD_DATE", getCursor(cursor, docName) + " <<<");
            } while (cursor.moveToNext());
        }

        return loa;
    }

    private String getCursor(Cursor cursor, String data) {
        return cursor.getString(cursor.getColumnIndex(data));
    }

    private int getIntValue(Cursor cursor, String key) {
        return cursor.getInt(cursor.getColumnIndex(key));
    }


    public ArrayList<ProvincesAdapter> retrieveProvince(String get_SPEC_SEARCH) {
        String SPEC_SEARCH = get_SPEC_SEARCH.toUpperCase().replace("'", "`");
        Cursor cursor;
        SQLiteDatabase database;
        ArrayList<ProvincesAdapter> array = new ArrayList<>();
        String sql = "SELECT * FROM " + provinceTable;
        sql += " WHERE " + provinceName + " LIKE '%" + SPEC_SEARCH + "%' ";
        sql += " ORDER BY " + provinceName + " ASC ";
        database = getReadableDatabase();
        cursor = database.rawQuery(sql, null);
        Log.e(TAG, cursor.getCount() + "");

        if (cursor.moveToFirst()) {
            do {
                ProvincesAdapter p = new ProvincesAdapter(
                        cursor.getString(cursor.getColumnIndex(regionCode)),
                        cursor.getString(cursor.getColumnIndex(provinceCode)),
                        cursor.getString(cursor.getColumnIndex(provinceName)),
                        "false"
                );

                array.add(p);
            } while (cursor.moveToNext());

        }


        cursor.close();
        database.close();
        return array;
    }


    public ArrayList<CitiesAdapter> retrieveCity(ArrayList<ProvincesAdapter> prevSelectedProvince, String get_SPEC_SEARCH) {
        SQLiteDatabase database;
        String search = get_SPEC_SEARCH.toUpperCase().replace("'", "`");
        String SPEC_SEARCH = get_SPEC_SEARCH.toUpperCase().replace("'", "`");
        ArrayList<CitiesAdapter> array = new ArrayList<>();
        Cursor cursor;
        String sql = "";
        sql = "SELECT * FROM " + cityTable;
        sql += " WHERE " + " " + cityName + "  LIKE '%" + SPEC_SEARCH + "%' ";
        if (prevSelectedProvince.size() >= 1) {
            sql += " AND  ( ";
            for (int x = 0; x < prevSelectedProvince.size(); x++) {
                sql += provinceCode + "  LIKE '%" + prevSelectedProvince.get(x).getProvinceCode() + "%'  OR ";
            }

            sql = sql.substring(0, sql.length() - 5);
            sql += " ) ";
        }


        sql += " ORDER BY " + cityName + " ASC ";

        Log.d("SQL_CITY", sql);
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


    public ArrayList<SpecsAdapter> retrieveSpecs(String SPEC_SEARCH) {
        Cursor cursor;
        SQLiteDatabase database;
        ArrayList<SpecsAdapter> array = new ArrayList<>();
        String sql = "SELECT * FROM " + specTable;

        if (!SPEC_SEARCH.equals("")) {
            sql += " WHERE " + " " + specializationDescription + "  LIKE '%" + SPEC_SEARCH + "%' ";
        }

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


    public ArrayList<GetDoctorsToHospital> retrieveDoctor(String searchData, ArrayList<SpecsAdapter> selectedSpec, String sort_by, String room_number_Data) {
        String searchTerm = searchData.toUpperCase().replace("'", "`");
        String room_number = room_number_Data.toUpperCase().replace("'", "`");
        SQLiteDatabase database;
        Cursor cursor;
        ArrayList<GetDoctorsToHospital> doc = new ArrayList<>();
        database = getReadableDatabase();


        String sql = "";
        sql += "SELECT * FROM " + doctable;
        sql += " WHERE  " + room + " LIKE '%" + room_number + "%' ";
        sql += " AND " + "( UPPER(" + docLname + ") LIKE '%" + searchTerm + "%'";
        sql += " OR " + "UPPER(" + specDesc + ") LIKE '%" + searchTerm + "%'   ";
        sql += " OR " + "UPPER(" + docFname + ") LIKE '%" + searchTerm + "%'  )";

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

        // remove all the duplicate without rearranging the actual list
        /*Set<GetDoctorsToHospital> uniqueDoctor = new LinkedHashSet<>();
        uniqueDoctor.addAll(doc);
        Timber.d("doctor size befor set %s", doc.size());
        doc.clear();
        doc.addAll(uniqueDoctor);
        Timber.d("doctor size after set %s", doc.size());
        Timber.d("the set is being filter");*/

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

    private List<HospitalsToDoctor> getDoctoLists(Cursor cursor) {

        List<HospitalsToDoctor>  docs = new ArrayList<>();

        if (cursor.moveToFirst()) {

            do {
                HospitalsToDoctor setDocs = new HospitalsToDoctor();
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
                //setDocs.setGracePeriod(cursor.getString(cursor.getColumnIndex(gracePeriod)));
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
        Cursor cursor;
        SQLiteDatabase database;
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


    public ArrayList<HospitalList> retrieveHospital(String isMedicardOnly, ArrayList<ProvincesAdapter> selectedProvince,
                                                    String data_sort, ArrayList<CitiesAdapter> selectedCity, String data) {

        ArrayList<HospitalList> arrayList = new ArrayList<>();
        String s = data.toUpperCase().replace("'", "`");
        //TEST IF QUERY IS ONLY FOR MEDICARD CLINICS


      // if (!getSortByProvinceCityOrHospName(data_sort)) {
            arrayList.addAll(getOnlyMedicardClinics(selectedProvince, data_sort, selectedCity, isMedicardOnly, s));
     //   }
        arrayList.addAll(getHospitalList(selectedProvince, data_sort, selectedCity, isMedicardOnly, s));


        return arrayList;
    }

    public List<HospitalList> retrieveHospitalforTest(String isMedicardOnly, ArrayList<ProvincesAdapter> selectedProvince,
                                                      String data_sort, ArrayList<CitiesAdapter> selectedCity, String data) {

        List<HospitalList> arrayList = new ArrayList<>();
        String s = data.toUpperCase().replace("'", "`");
        //TEST IF QUERY IS ONLY FOR MEDICARD CLINICS


        // if (!getSortByProvinceCityOrHospName(data_sort)) {
        arrayList.addAll(getOnlyMedicardClinics(selectedProvince, data_sort, selectedCity, isMedicardOnly, s));
        //   }
        arrayList.addAll(getHospitalList(selectedProvince, data_sort, selectedCity, isMedicardOnly, s));


        return arrayList;
    }

    private boolean getSortByProvinceCityOrHospName(String data_sort) {
        Timber.d("Sort By : %s", data_sort);
        return data_sort.equalsIgnoreCase(hospitalName) || data_sort.equalsIgnoreCase(city) ||
                data_sort.equalsIgnoreCase(province);
    }

    private Collection<? extends HospitalList> getHospitalList(ArrayList<ProvincesAdapter> selectedProvince, String data_sort, ArrayList<CitiesAdapter> selectedCity, String isMedicardOnly, String s) {

        ArrayList<HospitalList> arrayList = new ArrayList<>();
        SQLiteDatabase database;
        Cursor cursor = null;
        database = getReadableDatabase();

        ///WILDCARD AT START
        //   if (selectedCity.size() == 0) {
        String sql2 = "";
        sql2 += "SELECT * FROM " + hospTable;
        sql2 += " WHERE " + hospitalName + "  LIKE '%" + s + "%' ";
     //   if (!getSortByProvinceCityOrHospName(data_sort)) {
            sql2 += " AND " + hospitalName + " NOT  LIKE '" + primaryHosp + "%' ";
            sql2 += " AND " + hospitalName + " NOT  LIKE '" + primaryHosp2 + "%' ";
      //  }
        sql2 += " AND ( " + excluded + " = 'false' ) ";

        if (selectedProvince.size() >= 1) {
            Log.d("testable", "getHospitalList: this method is called selectedProvince.size())");
            sql2 += "  AND ( ";
            for (int x = 0; x < selectedProvince.size(); x++) {
                sql2 += province + "  LIKE '%" + selectedProvince.get(x).getProvinceName() + "%'   OR  ";
            }
            String temp;
            temp = sql2;
            sql2 = "";
            sql2 = temp.substring(0, temp.length() - 4);
            sql2 += " ) ";
        }

        if (selectedCity.size() != 0) {
            Log.d("testable", "getHospitalList: this method is called selectedCity.size()");
            sql2 += " AND (";
            for (int x = 0; x < selectedCity.size(); x++) {
                sql2 += "   " + city + " LIKE '%" + selectedCity.get(x).getCityName() + "%'   " + "  OR  ";
            }
            //remove and
            sql2 = sql2.substring(0, sql2.length() - 6);
            sql2 += " ) ";
        }
        sql2 += " ORDER BY " + sortDefault(data_sort) + " COLLATE NOCASE ";
        Log.e(TAG, "sql: " + sql2);

        cursor = database.rawQuery(sql2, null);
        Log.e(TAG, cursor.getCount() + "");
        arrayList.addAll(getHospList(cursor));


        return arrayList;
    }

    public Collection<? extends HospitalList> getOnlyMedicardClinics(ArrayList<ProvincesAdapter> selectedProvince, String data_sort, ArrayList<CitiesAdapter> selectedCity, String isMedicardOnly, String data) {
        ArrayList<HospitalList> arrayList = new ArrayList<>();
        String s = data.toUpperCase().replace("'", "`");
        SQLiteDatabase database;
        Cursor cursor = null;
        database = getReadableDatabase();


        String primary = "";
        primary += "SELECT * FROM " + hospTable;
        primary += " WHERE " + hospitalName + " LIKE  '%" + s + "%' ";
        primary += " AND (" + hospitalName + "  LIKE '%" + primaryHosp + "%' ";
        primary += " OR " + hospitalName + "  LIKE '%" + primaryHosp2 + "%' )";

        primary += " AND ( " + excluded + " = 'false' ) ";
        if (selectedCity.size() != 0) {
            primary += " AND  (";
            for (int x = 0; x < selectedCity.size(); x++) {
                primary += "   " + city + " LIKE '%" + selectedCity.get(x).getCityName() + "%'  " + "  OR  ";
            }
            //remove and
            primary = primary.substring(0, primary.length() - 6);
            primary += " ) ";
        }
        if (selectedCity.size() == 0) {
            if (selectedProvince.size() >= 1) {
                primary += " AND (";
                for (int x = 0; x < selectedProvince.size(); x++) {
                    primary += " "+ province + "  LIKE '%" + selectedProvince.get(x).getProvinceName() + "%' OR ";
                }

                //remove and
                primary = primary.substring(0, primary.length() - 3);
                primary += " ) ";

            }
        }


        primary += " ORDER BY " +  data_sort  + " COLLATE NOCASE ";
        Log.e(TAG, "sql: " + primary);

        cursor = database.rawQuery(primary, null);
        Log.e(TAG, cursor.getCount() + "");
        arrayList.addAll(getHospList(cursor));


        return arrayList;
    }

    private String sortDefault(String dat) {

        return dat.equals("") || dat.equals("category") ? "hospitalName" : dat;
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

    public HospitalList getHospitalContact(String gethospitalCode) {

        Cursor cursor;
        SQLiteDatabase database;
        database = getReadableDatabase();

        String sql = "";
        sql += "SELECT * FROM " + hospTable;
        sql += " WHERE " + hospitalCode + " = '" + gethospitalCode + "'";
        Log.e(TAG, "objectName: " + sql);
        cursor = database.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

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


        database.close();


        return hospital;
    }

    public GetDoctorsToHospital findDoctoByCode(String getDoctorCode) {
        GetDoctorsToHospital setDocs = new GetDoctorsToHospital();

        Cursor cursor;
        SQLiteDatabase database;
        database = getReadableDatabase();

        String sql = "";
        sql += "SELECT * FROM " + doctable;
        sql += " WHERE " + doctorCode + " = '" + getDoctorCode + "'";
        Log.e(TAG, "objectName: " + sql);
        cursor = database.rawQuery(sql, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

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

        return setDocs;
    }

    public ArrayList<LoaFetch> retrieveHospital(ArrayList<SimpleData> getHospList) {
        SQLiteDatabase database;
        Cursor cursor;
        ArrayList<LoaFetch> hosp = new ArrayList<>();
        database = getReadableDatabase();


        String sql = "";
        sql += "SELECT * FROM " + loaTable;


        if (getHospList.size() != 0) {
            sql += " WHERE  ( ";
            for (int x = 0; x < getHospList.size(); x++) {
                if (getHospList.get(x).getSelected().equals("true")) {
                    sql += hospitalName + " = '" + getHospList.get(x).getHospital() + "' OR ";
                }
            }

            sql = sql.substring(0, sql.length() - 3);
            sql += " ) ";

        }

        Log.e(TAG, "objectName: " + sql);
        cursor = database.rawQuery(sql, null);
        Log.e(TAG, "Count " + cursor.getCount());
        hosp.addAll(getDataLoa(cursor));

        database.close();
        return hosp;
    }

    public Collection<? extends LoaFetch> retrieveDoctor(ArrayList<LoaFetch> dataLoa) {
        SQLiteDatabase database;
        Cursor cursor;
        ArrayList<LoaFetch> hosp = new ArrayList<>();
        database = getReadableDatabase();


        String sql = "";
        sql += "SELECT * FROM " + loaTable;


        if (dataLoa.size() != 0) {
            sql += " WHERE  ( ";
            for (int x = 0; x < dataLoa.size(); x++) {
                if (setDataDetailsSpecCharacters(dataLoa.get(x).getDoctorCode())) {
                    sql += doctorCode + " = '" + dataLoa.get(x).getDoctorCode() + "' OR ";
                }
            }

            sql = sql.substring(0, sql.length() - 3);
            sql += " ) ";

        }

        Log.e(TAG, "objectName: " + sql);
        cursor = database.rawQuery(sql, null);
        Log.e(TAG, "Count " + cursor.getCount());
        hosp.addAll(getDataLoa(cursor));

        database.close();
        return hosp;
    }


    private boolean setDataDetailsSpecCharacters(String doctorCode) {
        boolean data = true;

        for (int x = 0; x < doctorCode.length(); x++) {

            String character = String.valueOf(doctorCode.charAt(x));

            if (character.equals(" ")) {
                data = false;
                break;
            }
        }

        return data;
    }


}

package utilities;

/**
 * Created by mpx-pawpaw on 1/3/17.
 */

public class Constant {



    public static String MEMCODE = "MEMCODE";
    public static String TESTORIGIN = "TESTORIGIN";
    public static String FNAME = "FNAME";
    public static String BIRTHDAY = "BIRTHDAY";
    public static String AGE = "AGE";
    public static String CIVIL = "CIVIL";
    public static String GENDER = "GENDER";
    public static String COMPANY = "COMPANY";
    public static String STATUS = "STATUS";
    //this string is used for Member status
    public static String MEM_STATUS = "MEM_STATUS";
    public static String CANCELLED = "CANCELLED";
    public static String MEDICARD_ONLY = "MEDICARD_ONLY";

    // for loa key
    public static final String BATCH_CODE = "batchCode";

    // for loa status
    public static final String EXPIRED = "EXPIRED";

    public static String MEMTYPE = "MEMTYPE";
    public static String EFFECTIVE = "EFFECTIVE";
    public static String VALIDITY = "VALIDITY";
    public static String REMARK = "REMARK";
    public static String LNAME = "LNAME";
    public static String PLAN = "PLAN";
    public static String NAME = "NAME";
    public static String MEMBER_ID = "MEMBER_ID";

    public static String REFERENCECODE = "REFERENCECODE";
    public static String REQUEST = "REQUEST";
    public static String CONDITION = "CONDITION";
    public static String NOT_FOUND = "NOT_FOUND";
    public static String NOT_SET = "NOT_SET";
    public static String PROCEDURE = "PROCEDURE";
    public static String DOCTOR_WITH_PROVIDER = "DOCTOR_WITH_PROVIDER";
    public static String ALL_PROVINCES = "All Provinces";
    public static String ALL_CITIES = "All Cities";
    public static String ALL_SPECIALIZATION = "All Specialization";
    public static String SELECT = "SELECT";
    public static String SELECT_PROVINCE = "SELECT_PROVINCE";
    public static String SELECT_CODE = "SELECT_CODE";
    public static String SELECT_CITY = "SELECT_CITY";
    public static String SELECT_SPECIALIZATION = "SELECT_SPECIALIZATION";
    public static String SELECTED_CITY = "SELECTED_CITY";
    public static String SELECTED_SPECIALIZATION = "SELECTED_SPECIALIZATION";
    public static String SELECTED_PROVINCE = "SELECTED_PROVINCE";
    public static int QUERY_CODE = 0;
    public static String QUERY_ALL = "QUERY_ALL";
    public static int QUERY_SEARCH = 1;
    public static String NONE = "NONE";
    public static String PROVINCE_CODE = "PROVINCE_CODE";
    public static String PROVINCE_NAME = "PROVINCE_NAME";
    public static String SORT_BY = "SORT_BY";
    public static String ROOM_NUMBER = "ROOM_NUMBER";

    public static String DOCTOR_U = "SORT_BY";
    public static String DOCTOR_ROOM = "SORT_BY";
    public static String HOSP_CONTACT = "SORT_BY";
    public static String HOSP_CONTACT_PER = "SORT_BY";
    public static String HOSP_U = "SORT_BY";
    public static String SEARCH_STRING = "SEARCH_STRING";

    public static String DATA_SEARCHED = "DATA_SEARCHED";
    public static String POSITION = "POSITION";
    public static String SELECT_LOAREQUEST = "SELECT_LOAREQUEST";
    public static String SELECTED_REQUEST = "SELECTED_REQUEST";
    public static String SELECTED_HOSPITAL = "SELECTED_HOSPITAL";
    public static String HOSPITAL_CODE = "HOSPITAL_CODE";
    public static String LOA_REQUEST = "LOA_REQUEST";

    public static String SELECT_DOCTOR = "SELECT_DOCTOR";
    public static String SELECT_DOCTOR_ID = "SELECT_DOCTOR_ID";
    public static String SELECT_TEST = "SELECT_TEST";
    public static String SELECT_DIAG = "SELECT_DIAG";
    public static String SELECT_HOSP = "SELECT_HOSP";
    public static String SELECT_HOSP_ID = "SELECT_HOSP_ID";
    public static String DOCTOR_LIST = "DOCTOR_LIST";
    public static String SELECTED_DOCTOR = "SELECTED_DOCTOR";
    public static String SELECTED_START_DATE = "SELECTED_START_DATE";
    public static String SELECTED_END_DATE = "SELECTED_END_DATE";
    public static String SEARCHED_DATA = "SEARCHED_DATA";
    public static String SPEC_SEARCH = "SPEC_SEARCH";

    public static String SERVICE_TYPE = "SERVICE_TYPE";

    /*
     * This is the data for inputing the value for primary diagnosis
     * this will be use in RequestNewActivity
     * Method goToResult()
     */
    public static String PRIMARY_DIAGNOSIS = "PRIMARY_DIAGNOSIS";

    /*
     *This variable will be use in pending request in Tests
     * this will replace the value of R.id.tv_subTitle
     */
    public static String SUBTITLEPENDING = "This request has been routed for approval.\n Go to My Approval Requests to view the status of the request. If there is no update within two hours, please call our Customer Support at 841-8080 or Toll Free: 1-800-1-888-9001. Globe Toll Free 1-800-8-944-8400.\n";

    /*
     *This variable will be use in approved request in TESTS
     * this will replace the value of R.id.tv_subtitle
     */
    public static String SUBTITLEAPPROVED  ="This approval shall be subjected to existing benefits and \n actual available limits under the plan. Member may still \n be billed for the amount paid. \n\n This serves as your approved request form. Please download, print and submit this to the cost center of the clinic/hospital. \n\n Availment is subject to hospital/clinic's availability of \n service";

    /*
     *These variable is used in RecyclerView attachments in Test
     * You need to set up a adapter to display the list of attachments
     * put this String to a bundle in RequestNewActivity
     */
    public static String RVATTACHMENTS = "RVATTACHMENTS";
    public static String BUNDLEFORATTACHEMENTS = "BUNDLEFORATTACHEMENTS";

    /*
     *This variable is for puting a bundle in LoaList
     * @Params List<MaceRequest>
     */
    public static String BundleForMaceRequest = "BundleForMaceRequest";

    /*
     *This variable is used to access the reason for consult
     * @Param String
     * return String
     */
    public static String REASONFORCONSULT = "REASONFORCONSULT";
    public static String TESTSRESULTS = "TESTSRESULTS";

    /*
     *The following Strings are different types of Member Status
     */
    public static String ACTIVEMEMBER = "ACTIVE";
    public static String ONHOLDMEMBER = "ON HOLD";
    public static String FORREACTIVATION = "FOR REACTIVATION";
    public static String VERIFYPAYMENTWRMD = "VERIFY PAYMENT WITH RMD";
    public static String VERIFYRENEWAL = "VERIFY RENEWAL FROM MARKETING / SALES";
    public static String VERIFYMEMBERSHIP = "VERIFY MEMBERSHIP";




    // Directory name to store captured images and videos
    public static final String IMAGE_DIRECTORY_NAME = "Android File Upload";

    public static final String NO_CONNECTION_TO_SERVER = "CANNOT CONNECT TO SERVER";


    public static final String ZIP_NAME = "MaceListing.zip";
    public static final String DIAGNOSIS_CSV = "Diagnosis.csv";
    public static final String HOSPITAL_CSV = "Hospitals.csv";
    public static final String DOC_HOSP_CSV = "DocHosp.csv";
    public static final String ROOM_CAT_CSV = "RoomCategories.csv";
    public static final String ROOM_PLANS_CSV = "RoomPlans.csv";
    public static final String TEST_PROC_LIST_CSV = "TestProcList.csv";
}

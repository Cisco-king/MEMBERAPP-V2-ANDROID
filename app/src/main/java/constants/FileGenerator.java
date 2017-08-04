package constants;

import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/4/2017.
 */

public class FileGenerator {


    public static final String CONSULTATION = "CONSULTATION";

    public static final String MATERNITY_CONSULTATION = "MATERNITY";

    public static final String OTHER_TEST = "OTHER TEST";

    public static final String BASIC_TEST = "BASIC TEST";

    public static final String  TEST = "TEST";




    // file name
    public static final String FILE_NAME_CONSULTATION = "CONSULT_";

    public static final String FILE_NAME_MATERNITY_CONSULTATION = "MATERNITY_";

    public static final String FILE_NAME_OTHER_TEST = "OTHERTEST_";

    public static final String FILE_NAME_BASIC_TEST = "BASICTEST_";

    public static final String FILE_NAME_TESTS = "TESTS_";

    public static final String genFileName(String serviceType, String approvalCode) {

        Timber.d("service type %s", serviceType);
        StringBuilder fileName = new StringBuilder();

        if (serviceType.contains(MATERNITY_CONSULTATION)) {
            fileName.append(FILE_NAME_MATERNITY_CONSULTATION);
        } else if (serviceType.contains(CONSULTATION)) {
            fileName.append(FILE_NAME_CONSULTATION);
        } else if (serviceType.contains(OTHER_TEST)) {
            fileName.append(FILE_NAME_OTHER_TEST);
        } else if (serviceType.contains(BASIC_TEST)) {
            fileName.append(FILE_NAME_BASIC_TEST);
        } else if( serviceType.contains(TEST)){
            fileName.append(FILE_NAME_TESTS);
        }

        fileName.append(approvalCode);

        return fileName.toString();
    }

    public static final String genFileNameNoServiceType(String approvalCode) {

        StringBuilder fileName = new StringBuilder();

        fileName.append(approvalCode);

        return fileName.toString();
    }


}

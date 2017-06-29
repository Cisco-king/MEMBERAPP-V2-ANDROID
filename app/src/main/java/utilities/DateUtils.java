package utilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by casjohnpaul on 6/21/2017.
 */

public class DateUtils {

    public static String getCurrentDate() {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date now = new Date();

        return format.format(now);
    }

}

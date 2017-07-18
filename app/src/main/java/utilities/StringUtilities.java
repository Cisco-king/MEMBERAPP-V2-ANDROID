package utilities;

/**
 * Created by casjohnpaul on 5/4/2017.
 */
public class StringUtilities {


    public static final boolean isNotEmpty(String val) {
        if (val != null && !val.isEmpty() && val.length() > 0){
            return true;
        }

        return false;
    }

    public static final boolean isNotEmptyIncludingSpaces(String val) {
        System.out.println(!val.replace(" ","").isEmpty());
        if (val != null && !val.replace(" ","").isEmpty() && val.length() > 0){
            return true;
        }

        return false;
    }


}

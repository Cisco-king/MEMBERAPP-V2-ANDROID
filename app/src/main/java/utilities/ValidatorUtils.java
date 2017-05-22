package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by casjohnpaul on 5/16/2017.
 */

public class ValidatorUtils {

    //  public static Pattern charPattern = Pattern.compile("[^a-zA-Z. ]", Pattern.CASE_INSENSITIVE);
    public static Pattern charPattern = Pattern.compile("[^a-zA-Z0-9.]", Pattern.CASE_INSENSITIVE);

    public static boolean noSpecialCharacter(String str) {
        Matcher matcher = charPattern.matcher(str);
        boolean pass = matcher.find();

        return pass;
    }

}

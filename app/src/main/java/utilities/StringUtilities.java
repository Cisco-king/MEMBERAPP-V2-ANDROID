package utilities;

/**
 * Created by Aquino F. Aquino on 5/4/2017.
 */
public class StringUtilities {


    public static final boolean isNotEmpty(String val) {
        if (val != null && !val.isEmpty() && val.length() > 0) {
            return true;
        }

        return false;
    }

    public static final boolean isNotEmptyIncludingSpaces(String val) {
        if (val != null && !val.replace(" ", "").isEmpty() && !val.replace("\n", "").isEmpty()
                && !val.replace("\n ", "").isEmpty()
                && !val.replaceAll("[^\\w ]", "").replaceAll("\\s+", "+").isEmpty()
                && !val.replaceAll("\\r\\n|\\r|\\n", " ").isEmpty()
                && !val.replaceAll("[\\d+]", " ").isEmpty()
                && val.length() > 0) {
            return true;

        }
        return false;
    }


}

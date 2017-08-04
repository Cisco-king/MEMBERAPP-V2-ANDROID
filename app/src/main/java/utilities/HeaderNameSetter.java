package utilities;

/**
 * Created by mpx-pawpaw on 12/29/16.
 */

public class HeaderNameSetter {

    private static String consult = "CONSULT";
    private static String maternity = "MATERNITY";
    String origin;


    public static String setHeader(String origin) {
        String set = "";
        if (origin.equals(consult)) {
            set = "Consultation";
        } else if (origin.equals(maternity)) {
            set = "Maternity Consultation";
        }else{
            set = "TEST";
        }

        return set;
    }
}

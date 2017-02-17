package utilities;

/**
 * Created by mpx-pawpaw on 11/11/16.
 */

public class AgeCorrector {


    public  static  String age(String myAge) {

        if (myAge.contains(".") && myAge.length() > 1) {

            for (int x = 0; x < myAge.length(); x++) {

                if (String.valueOf(myAge.charAt(x)).equals(".")){


                    return myAge.substring(0 , x) ;

                }

            }

        }else
            return myAge;


        return " ";
    }
}

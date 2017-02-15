package utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mpx-pawpaw on 10/21/16.
 */

public class EmailTester {


    public boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }




    public boolean isPasswordMatching(String password, String confirmPassword) {
        Pattern pattern = Pattern.compile(password, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(confirmPassword);

        if (!matcher.matches()) {
            // do your Toast("passwords are not matching");
            return false;
        }

        return true;
    }

}

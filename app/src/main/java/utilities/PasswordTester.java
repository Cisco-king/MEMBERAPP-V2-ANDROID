package utilities;

/**
 * Created by mpx-pawpaw on 10/21/16.
 */

public class PasswordTester {
    /*
    EVERYTHING MUST BE TRUE ...
     */

    public static boolean tester(String password) {


        int passLenght = password.length();

        boolean ismorethan8Characters = passLenght >= 8 ;
        boolean hasUppercase = !password.equals(password.toLowerCase());
        boolean hasNumericChar = containsDigit(password);

        System.out.println("String ismorethan8Characters:" +   ismorethan8Characters);
        System.out.println("String hasUppercase:" +   hasUppercase);
        System.out.println("String hasNumericChar:" +   hasNumericChar);
        if(hasUppercase && ismorethan8Characters && hasNumericChar){
            return true ;
        }else
            return false ;



    }

    public static boolean containsDigit(String s)
    {
        if (s != null && !s.isEmpty())
        {
            for (char c : s.toCharArray())
            {
                if (Character.isDigit(c))
                {
                    return true;
                }
            }
        }

        return false;
    }


}

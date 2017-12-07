package utilities;

import android.support.annotation.NonNull;

/**
 * Created by mpx-pawpaw on 3/24/17.
 */

public class ErrorMessage {


    @NonNull
    public static String setErrorMessage(String data) {

        String message = "";
        if (data.contains("unexpected end of stream"))
            message = "MACE cannot connect to the server.";
        else if (data.contains("Connection timed out"))
            message = "Connection timed out. Please try again.";
        else if (data.contains("Failed to connect to") || data.contains("failed to connect to") || data.contains("no response to server"))
            message = "Cannot connect to server";
        else
            message = "An error occurred. Please try again.";

        return message;
    }
}

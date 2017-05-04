package utilities;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

/**
 * Created by casjohnpaul on 5/3/2017.
 */
public class PermissionUtililities {

    public static final int REQUESTCODE_STORAGE_PERMISSION = 100;


    public static String[] READ_WRITE_PERMISSION = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    /**
     *
     * @param activity
     * The Activity
     * @return
     * True if user Application has already permission to read and write
     */
    public static boolean hasPermissionToReadAndWriteStorage(Activity activity) {
        int readExternalStorage =
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.READ_EXTERNAL_STORAGE);
        int writeExternalStorage =
                ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (isGranted(readExternalStorage) && isGranted(writeExternalStorage)) return true;

        ActivityCompat.requestPermissions(activity, READ_WRITE_PERMISSION, REQUESTCODE_STORAGE_PERMISSION);

        return false;
    }

    /**
     *
     * @param permission
     * The Application Permission
     * @return
     * True if permission is granted else false
     */
    public static boolean isGranted(int permission) {
        return permission == PackageManager.PERMISSION_GRANTED;
    }

}

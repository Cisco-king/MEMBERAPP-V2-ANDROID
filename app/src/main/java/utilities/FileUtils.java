package utilities;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.ByteArrayOutputStream;
import java.io.File;

import constants.FileGenerator;

/**
 * Created by casjohnpaul on 5/24/2017.
 */

public class FileUtils {


    public static String getPathFromURI(Context context, Uri contentUri) {
        String res = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, null, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }

    public static Uri getImageUri(Context context, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }

    public static boolean fileExistance(String serviceType, String approvalNumber) {
        String loaFileName =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +
                        File.separator + "MediCard" + File.separator +
                        FileGenerator.genFileName(serviceType, approvalNumber) + ".pdf";

        File file = new File(loaFileName);
        return file.exists();
    }
    public static boolean fileExistance(String approvalNumber) {
        String loaFileName =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) +
                        File.separator + "MediCard" + File.separator +
                        FileGenerator.genFileNameNoServiceType(approvalNumber) + ".pdf";

        File file = new File(loaFileName);
        return file.exists();
    }

}

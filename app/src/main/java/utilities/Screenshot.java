package utilities;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Environment;
import android.util.Log;
import android.widget.ScrollView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import InterfaceService.ScreenshotCallback;

/**
 * Created by mpx-pawpaw on 2/1/17.
 */

public class Screenshot {


    public static Bitmap loadBitmapFromView(ScrollView v) {
        Bitmap b = Bitmap.createBitmap(v.getWidth(), v.getChildAt(0).getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.draw(c);
        return b;
    }

    public static void saveBitmap(Bitmap bitmap, ScreenshotCallback callback, Context context) {

        // saveToInternalStorage(bitmap , context , callback);
        //   boolean sdIsAvailable = false;

        //  File imagePath = null;
        //   File directory = null;
//        if (externalMemoryAvailable()) {
//            Log.d("FILE_PATH_async", "YES");
//        } else {
//            Log.d("FILE_PATH_async", "NO");
//            directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//
//        }
        //     directory = new File(Environment.getExternalStorageDirectory(), "Medicard");

        //    directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //    imagePath = new File(directory + File.separator + "Medicard");

//        String path = context.getFilesDir().getAbsolutePath();
//        File mediaStorageDir = new File(path +"/Medicard");
//        Log.d("App", mediaStorageDir.getAbsolutePath());
//        Log.d("App", path);
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                Log.d("App", "failed to create directory");
//            }
//        }
//        File sub = new File(Environment.getExternalStorageDirectory(Environment.DIRECTORY_PICTURES), "SAMPLE");
//        if (!sub.exists())
//            sub.mkdirs();
//        Log.d("App2", sub.getAbsolutePath());

//
//        if (directory.mkdirs())
//            Log.d("FILE_PATH_async", directory.getAbsolutePath());
//        else
//            Log.d("FILE_PATH_async", "NOOO");

//        FileOutputStream fos = null;
//        try {
//            fos = new FileOutputStream(imagePath);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
//            fos.flush();
//            fos.close();
//            callback.onSuccessfulScreenshot();
//        } catch (FileNotFoundException e) {
//            Log.e("GREC", e.getMessage(), e);
//        } catch (IOException e) {
//            Log.e("GREC", e.getMessage(), e);
//        }


        //File mediaStorageDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory(), "MEDICARD");
        // File mediaStorageDir = new File(PATH + "/Medicard");
        Log.d("App", mediaStorageDir.getAbsolutePath());
        // Log.d("App", PATH.getAbsolutePath());
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdir()) {
                Log.d("App", "failed to create directory");
            }
        }
//        File mypath;
//
//        File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//
//        mypath = new File(directory + File.separator + "CameraMEDICARD.jpeg");
//
//
//        Log.d("FILE_PATH_async", mypath.getAbsolutePath());
//        FileOutputStream fos = null;
//        try {
//
//            fos = new FileOutputStream(mypath);
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                fos.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

    }


    /* Checks if external storage is available for read and write */
    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }


    private static String saveToInternalStorage(Bitmap bitmapImage, Context context, ScreenshotCallback callback) {
        ContextWrapper cw = new ContextWrapper(context);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("/Medicard", Context.MODE_APPEND);
        // Create imageDir
        Log.d("FILE_PATH_async", directory.getAbsolutePath());
        File mypath = new File(directory, "Consultation.jpg");
        Log.d("FILE_PATH_async", mypath.getAbsolutePath());
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
                callback.onSuccessfulScreenshot();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
}

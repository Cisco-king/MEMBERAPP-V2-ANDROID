package utilities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.net.Uri;

import android.os.Environment;

import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;


import InterfaceService.ScreenshotCallback;

/**
 * Created by mpx-pawpaw on 2/2/17.
 */

public class ImageSaver {

    private String directoryName = "images";
    private String fileName = "image.png";
    private Context context;
    private boolean external;
    File directory;

    public ImageSaver(Context context) {
        this.context = context;
    }

    public ImageSaver setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public ImageSaver setExternal(boolean external) {
        this.external = external;
        return this;
    }

    public ImageSaver setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }

    public void save(Bitmap bitmapImage, ScreenshotCallback callback) {
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(createFile());
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);

            context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + createFile().getAbsolutePath())));
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("CREATE_BIT_1", e.getMessage());
        } finally {
            try {
                if (fileOutputStream != null) {
                    fileOutputStream.close();
                    callback.onSuccessfulScreenshot();
                }
            } catch (IOException e) {
                e.printStackTrace();
                Log.e("CREATE_BIT_2", e.getMessage());
            }
        }
    }


    @NonNull
    private File createFile() {

        if (external) {
            directory = getAlbumStorageDir(directoryName);
            Log.d("real_PATH", directory.getAbsolutePath());
            return new File(directory, File.separator + fileName);
        } else {
            directory = createAlbum(directoryName);
            return new File(directory, File.separator + fileName);
        }

    }

    private File createAlbum(String directoryName) {

        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),directoryName);

        if (!file.mkdirs()) {
            Log.e("ImageSaver", "Directory not created");
        }
        Log.d("real_PATH", file.getAbsolutePath());
        return file;
    }

    private File getAlbumStorageDir(String albumName) {
        File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),albumName);

        if (!file.mkdirs()) {
            Log.e("ImageSaver", "Directory not created");
        }

        return file;
    }

    public static boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    public static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    public Bitmap load() {
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(createFile());
            return BitmapFactory.decodeStream(inputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


}
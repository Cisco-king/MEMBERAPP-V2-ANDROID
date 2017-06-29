package utilities;

import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;
import timber.log.Timber;

/**
 * Created by mpx-pawpaw on 10/28/16.
 */

public class ImageConverters {


    public static String encodeToBase64(Bitmap image, Bitmap.CompressFormat compressFormat, int quality) {
        ByteArrayOutputStream byteArrayOS = new ByteArrayOutputStream();
        image.compress(compressFormat, quality, byteArrayOS);
        return Base64.encodeToString(byteArrayOS.toByteArray(), Base64.DEFAULT);
    }


    public Bitmap rotateBitmapOrientation(String photoFilePath) {

        // Create and configure BitmapFactory
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(photoFilePath, bounds);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(photoFilePath, opts);
        // Read EXIF Data
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(photoFilePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;
        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;
        // Rotate Bitmap
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
        // Return result
        return rotatedBitmap;
    }

    public String getRealPathFromURI(Uri contentUri, Context context) {
        String path = null;
        String[] proj = {MediaStore.MediaColumns.DATA};
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    public static Uri getImageContentUri(Context context, File imageFile) {
        String filePath = imageFile.getAbsolutePath();
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                new String[]{MediaStore.Images.Media._ID},
                MediaStore.Images.Media.DATA + "=? ",
                new String[]{filePath}, null);
        if (cursor != null && cursor.moveToFirst()) {
            int id = cursor.getInt(cursor.getColumnIndex(MediaStore.MediaColumns._ID));
            cursor.close();
            return Uri.withAppendedPath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "" + id);
        } else {
            if (imageFile.exists()) {
                ContentValues values = new ContentValues();
                values.put(MediaStore.Images.Media.DATA, filePath);
                return context.getContentResolver().insert(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
            } else {
                return null;
            }
        }
    }


    public String getPath(Uri uri, Context context) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(projection[0]);
        String filePath = cursor.getString(columnIndex);
        cursor.close();


        //   circleImageView2.setImageBitmap(BitmapFactory.decodeFile(filePath));
        return cursor.getString(column_index);
    }


    public static Bitmap loadBitmapFromView(View v) {
        Bitmap b = Bitmap.createBitmap(v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(b);
        v.layout(0, 0, v.getLayoutParams().width, v.getLayoutParams().height);
        v.draw(c);
        return b;
    }


    public void saveToInternalStorage(final CircleImageView circleImageView, final Bitmap bitmapImage, Context context, final boolean isCamera) {


        final Bitmap[] newBitmap = new Bitmap[1];
        AsyncTask asyncTask = new AsyncTask() {

            @Override
            protected void onPreExecute() {
                if (bitmapImage.getHeight() < 150 || bitmapImage.getWidth() < 150) {
                } else {
                     //   if (bitmapImage.getHeight() >4000 || bitmapImage.getWidth() > 4000){
                            newBitmap[0] = getResizedBitmap(bitmapImage, 150, 150);
                       // }else
                         //   newBitmap[0] = getResizedBitmap(bitmapImage, bitmapImage.getWidth() /2, bitmapImage.getHeight() /2);

                }
            }

            @Override
            protected Object doInBackground(Object[] params) {

                File mypath;

                File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                if (isCamera) {
                    mypath = new File(directory + File.separator + "CameraMEDICARD.jpeg");
                } else {
                    mypath = new File(directory + File.separator + "GalleryMEDICARD.jpeg");

                }

                Log.d("FILE_PATH_async", mypath.getAbsolutePath());
                FileOutputStream fos = null;
                try {

                    fos = new FileOutputStream(mypath);
                    newBitmap[0].compress(Bitmap.CompressFormat.JPEG, 100, fos);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                return null;
            }


            @Override
            protected void onPostExecute(Object o) {

                circleImageView.setImageBitmap(newBitmap[0]);
            }
        };


        asyncTask.execute();


    }

    public void saveToInternalStorage2(final Bitmap bitmapImage, Context context, final boolean isCamera) {


        final Bitmap[] newBitmap = new Bitmap[1];
        AsyncTask asyncTask = new AsyncTask() {

            @Override
            protected void onPreExecute() {
                if (bitmapImage.getHeight() < 150 || bitmapImage.getWidth() < 150) {
                } else {
                    //   if (bitmapImage.getHeight() >4000 || bitmapImage.getWidth() > 4000){
                    newBitmap[0] = getResizedBitmap(bitmapImage, 150, 150);
                    // }else
                    //   newBitmap[0] = getResizedBitmap(bitmapImage, bitmapImage.getWidth() /2, bitmapImage.getHeight() /2);

                }
            }

            @Override
            protected Object doInBackground(Object[] params) {

                File mypath;

                File directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                if (isCamera) {
                    mypath = new File(directory + File.separator + "CameraMEDICARD.jpeg");
                } else {
                    mypath = new File(directory + File.separator + "GalleryMEDICARD.jpeg");

                }

                Log.d("FILE_PATH_async", mypath.getAbsolutePath());
                FileOutputStream fos = null;
                try {

                    fos = new FileOutputStream(mypath);
                    newBitmap[0].compress(Bitmap.CompressFormat.JPEG, 100, fos);

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                return null;
            }


            @Override
            protected void onPostExecute(Object o) {
                Timber.d("success...");
            }
        };


        asyncTask.execute();


    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();

        Log.d("NEWWIDTH", resizedBitmap.getWidth() + "");
        Log.d("NEWHeight", resizedBitmap.getHeight() + "");
        return resizedBitmap;
    }


}

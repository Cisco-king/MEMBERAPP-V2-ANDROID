package utilities;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import Sqlite.DatabaseHandler;

/**
 * Created by IPC on 1/3/2018.
 */

public class DataReaderFromCsv {
    Context context;
    DatabaseHandler dataBaseHandler;
    int indexOfFile;
    ArrayList<String> filenames;

    public DataReaderFromCsv(Context context, DatabaseHandler dataBaseHandler) {
        this.context = context;
        this.dataBaseHandler = dataBaseHandler;
        this.indexOfFile = 0;
        this.filenames = new ArrayList<>();
    }

    public void csvFileReader(Context context, String CSVFileName) {
        Log.d("File", CSVFileName);
        AssetManager manager = context.getResources().getAssets();
        InputStream inStream = null;
        String text = "";
        try {
            inStream = new FileInputStream("/data/data/" + context.getPackageName() + "/" + CSVFileName);
            Log.v("TAG", "Text File: " + inStream.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        BufferedReader buffer = new BufferedReader(new InputStreamReader(inStream));
        String line = "";
        Log.e("BUFFER", inStream.toString());
        try {
            int x = 0;
            while ((line = buffer.readLine()) != null) {
                String[] rows = line.split("~");
                if (rows.length == 0) {
                    Log.e("FILENAME ", String.valueOf(this.filenames.get(this.indexOfFile) + " ROW: " +String.valueOf(x++)));
                    Log.d("CSVParser", "Skipping Bad CSV Row");
                    continue;
                }
//                Log.e("FILENAME ", String.valueOf(this.filenames.get(this.indexOfFile) + " ROW: " +String.valueOf(x++)));
                switch(CSVFileName){
                    /**
                     * HOSPITAL AD
                     * */
//                    case Constant.DIAGNOSIS_CSV:
//                        dataBaseHandler.insertToDiagnosis(rows);
//                        break;
                    case Constant.HOSPITAL_CSV:
                        dataBaseHandler.insertToHospital(rows,String.valueOf(this.filenames.get(this.indexOfFile)),String.valueOf(x++));
                        break;
                    case Constant.DOC_HOSP_CSV:
                        dataBaseHandler.insertToDocHosp(rows,String.valueOf(this.filenames.get(this.indexOfFile)),String.valueOf(x++));
                        break;
//                    case Constant.ROOM_CAT_CSV:
//                        dataBaseHandler.insertToRoomCat(rows);
//                        break;
//                    case Constant.ROOM_PLANS_CSV:
//                        dataBaseHandler.insertToRoomPlan(rows);
//                        break;
//                    case Constant.TEST_PROC_LIST_CSV:
//                        dataBaseHandler.insertToTestProcList(rows);
//                        break;
                    default:
                        System.out.println("ERROR DB INSERT");
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.indexOfFile++;
        if(this.indexOfFile < this.filenames.size()){
            csvFileReader(context, this.filenames.get(this.indexOfFile));
        }
    }

    public boolean unZip(Context context, String zipname) {
        InputStream is;
        ZipInputStream zis;
        AssetManager manager = context.getResources().getAssets();
        InputStream inStream = null;
        try {
            inStream = manager.open(zipname);
            BufferedInputStream bis = new BufferedInputStream(manager.open(zipname));
            zis = new ZipInputStream(bis);
            ZipEntry ze;

            while ((ze = zis.getNextEntry()) != null) {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024];
                int count;
                String filename = ze.getName();
                FileOutputStream fout = new FileOutputStream("/data/data/" + context.getPackageName() + "/" + filename);
                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                    byte[] bytes = baos.toByteArray();
                    fout.write(bytes);
                    baos.reset();
                }
                fout.close();
                zis.closeEntry();
                filenames.add(filename);
            }
            zis.close();
            csvFileReader(context, this.filenames.get(this.indexOfFile));

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}

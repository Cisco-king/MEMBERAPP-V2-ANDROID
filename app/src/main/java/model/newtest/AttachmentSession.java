package model.newtest;

import java.io.File;
import java.util.ArrayList;

import model.AttachmentModel;

/**
 * Created by macbookpro on 8/13/17.
 */

public class AttachmentSession {

    private static ArrayList<File> fileAttachments = new ArrayList<>();


    public static void addAttachemnt(File file){
        fileAttachments.add(file);
    }

    public static void removeAttachment(File file){
        fileAttachments.remove(file);
    }

    public static ArrayList<File> getAllAttachments(){
        return fileAttachments;
    }

    public static void releaseContent() {
        fileAttachments.clear();
        fileAttachments = new ArrayList<>();
    }
}

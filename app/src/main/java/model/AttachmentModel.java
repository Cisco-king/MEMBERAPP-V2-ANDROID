package model;

import java.io.File;

/**
 * Created by macbookpro on 8/13/17.
 */

public class AttachmentModel {

    private File file;

    private String requestCode;

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(String requestCode) {
        this.requestCode = requestCode;
    }
}

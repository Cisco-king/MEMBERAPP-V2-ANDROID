package utilities;

import android.content.Context;

/**
 * Created by mpx-pawpaw on 2/2/17.
 */

public class PdfSaver {

    Context context;
    private String fileName = "file_pdf";
    private String directoryName = "PDF";

    public PdfSaver(Context context) {
        this.context = context;
    }


    public PdfSaver setFileName(String fileName) {
        this.fileName = fileName;
        return this;
    }

    public PdfSaver setDirectoryName(String directoryName) {
        this.directoryName = directoryName;
        return this;
    }
}

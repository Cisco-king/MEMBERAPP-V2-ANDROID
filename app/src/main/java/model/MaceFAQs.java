package model;

/**
 * Created by IPC on 11/22/2017.
 */

public class MaceFAQs {

    private String header;
    private String body;
    private String index;


    public MaceFAQs() {
    }

    public MaceFAQs(String header, String body, String index) {
        this.header = header;
        this.body = body;
        this.index = index;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }
}

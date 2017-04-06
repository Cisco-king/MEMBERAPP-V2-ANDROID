package model;

/**
 * Created by mpx-pawpaw on 4/6/17.
 */

public class Disclaimer {
    private String hasDisclaimer;

    private String responseCode;

    private String responseDesc;

    public String getHasDisclaimer() {
        return hasDisclaimer;
    }

    public void setHasDisclaimer(String hasDisclaimer) {
        this.hasDisclaimer = hasDisclaimer;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseDesc() {
        return responseDesc;
    }

    public void setResponseDesc(String responseDesc) {
        this.responseDesc = responseDesc;
    }

    @Override
    public String toString() {
        return "ClassPojo [hasDisclaimer = " + hasDisclaimer + ", responseCode = " + responseCode + ", responseDesc = " + responseDesc + "]";
    }
}

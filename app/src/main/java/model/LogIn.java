package model;

/**
 * Created by mpx-pawpaw on 10/25/16.
 */

public class LogIn {
    private String username;

    private String deviceID;

    private String versionNo;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ClassPojo [username = " + username + ", deviceID = " + deviceID + ", versionNo = " + versionNo + ", password = " + password + "]";
    }
}

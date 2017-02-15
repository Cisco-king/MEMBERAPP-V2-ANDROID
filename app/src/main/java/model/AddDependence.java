package model;

/**
 * Created by mpx-pawpaw on 11/24/16.
 */

public class AddDependence {

    private String username;

    private String depMemberCode;

    private String memberCode;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDepMemberCode() {
        return depMemberCode;
    }

    public void setDepMemberCode(String depMemberCode) {
        this.depMemberCode = depMemberCode;
    }

    public String getMemberCode() {
        return memberCode;
    }

    public void setMemberCode(String memberCode) {
        this.memberCode = memberCode;
    }

    @Override
    public String toString() {
        return "ClassPojo [username = " + username + ", depMemberCode = " + depMemberCode + ", memberCode = " + memberCode + "]";
    }


}

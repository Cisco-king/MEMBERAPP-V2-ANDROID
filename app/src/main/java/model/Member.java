package model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by John Paul Cas on 4/12/2017.
 */

public class Member implements Parcelable {

    private String name;
    private String memberId;
    private String remarks;
    private String gender;
    private String company;
    private String age;

    public Member() {

    }

    public Member(Builder builder) {
        setName(builder.name);
        setMemberId(builder.memberId);
        setRemarks(builder.remarks);
        setGender(builder.gender);
        setCompany(builder.company);
        setAge(builder.age);
    }


    protected Member(Parcel in) {
        readFromParcel(in);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(memberId);
        dest.writeString(remarks);
        dest.writeString(gender);
        dest.writeString(company);
        dest.writeString(age);
    }

    /**
     *
     * @param in
     * Read data from {@link Parcel}
     */
    protected void readFromParcel(Parcel in) {
        name = in.readString();
        memberId = in.readString();
        remarks = in.readString();
        gender = in.readString();
        company = in.readString();
        age = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Member> CREATOR = new Creator<Member>() {
        @Override
        public Member createFromParcel(Parcel in) {
            return new Member(in);
        }

        @Override
        public Member[] newArray(int size) {
            return new Member[size];
        }
    };

    /**
     *
     * @return
     * The Name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The MemberId
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     *
     * @param memberId
     * The MemberId
     */
    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    /**
     *
     * @return
     * The Remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     *
     * @param remarks
     * The Remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     *
     * @return
     * The Gender
     */
    public String getGender() {
        return gender;
    }

    /**
     *
     * @param gender
     * The Gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     *
     * @return
     * The Company
     */
    public String getCompany() {
        return company;
    }

    /**
     *
     * @param company
     * The Company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     *
     * @return
     * The Age
     */
    public String getAge() {
        return age;
    }

    /**
     *
     * @param age
     * The Age
     */
    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return new StringBuilder("Member")
                .append("\nName : " + getName())
                .append("\nMemberId : " + getMemberId())
                .append("\nRemarks : " + getRemarks())
                .append("\nGender : " + getGender())
                .append("\nCompany : " + getCompany())
                .append("\nAge : " + getAge())
                .toString();
    }

    public static final class Builder {
        private String name;
        private String memberId;
        private String remarks;
        private String gender;
        private String company;
        private String age;

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder memberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public Builder gender(String gender) {
            this.gender = gender;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
            return this;
        }

        public Builder age(String age) {
            this.age = age;
            return this;
        }

        public Member build() {
            return new Member(this);
        }
    }

}

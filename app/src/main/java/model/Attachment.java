package model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by casjohnpaul on 5/30/2017.
 */

public class Attachment implements Parcelable {

    private String fileName;
    private String uri;

    public Attachment() {
    }

    private Attachment(Builder builder) {
        setFileName(builder.fileName);
        setUri(builder.uri);
    }

    protected Attachment(Parcel in) {
        fileName = in.readString();
        uri = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fileName);
        dest.writeString(uri);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Attachment> CREATOR = new Creator<Attachment>() {
        @Override
        public Attachment createFromParcel(Parcel in) {
            return new Attachment(in);
        }

        @Override
        public Attachment[] newArray(int size) {
            return new Attachment[size];
        }
    };

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }


    public static final class Builder {
        private String fileName;
        private String uri;

        public Builder() {
        }

        public Builder fileName(String val) {
            fileName = val;
            return this;
        }

        public Builder uri(String val) {
            uri = val;
            return this;
        }

        public Attachment build() {
            return new Attachment(this);
        }
    }

}

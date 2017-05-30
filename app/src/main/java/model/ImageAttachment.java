package model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by casjohnpaul on 5/25/2017.
 */

public class ImageAttachment implements Parcelable {

    private Bitmap image;
    private String fileName;

    public ImageAttachment() {
    }

    private ImageAttachment(Builder builder) {
        setImage(builder.image);
        setFileName(builder.fileName);
    }

    protected ImageAttachment(Parcel in) {
        image = in.readParcelable(Bitmap.class.getClassLoader());
        fileName = in.readString();
    }

    public static final Creator<ImageAttachment> CREATOR = new Creator<ImageAttachment>() {
        @Override
        public ImageAttachment createFromParcel(Parcel in) {
            return new ImageAttachment(in);
        }

        @Override
        public ImageAttachment[] newArray(int size) {
            return new ImageAttachment[size];
        }
    };

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(image, flags);
        dest.writeString(fileName);
    }


    public static final class Builder {
        private Bitmap image;
        private String fileName;

        public Builder() {
        }

        public Builder image(Bitmap val) {
            image = val;
            return this;
        }

        public Builder fileName(String val) {
            fileName = val;
            return this;
        }

        public ImageAttachment build() {
            return new ImageAttachment(this);
        }
    }

}

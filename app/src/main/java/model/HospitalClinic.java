package model;

/**
 * Created by John Paul Cas on 4/19/2017.
 */

public class HospitalClinic {

    private String name;

    public HospitalClinic() {

    }

    public HospitalClinic(Builder builder) {
        setName(builder.name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static final class Builder {

        private String name;

        public Builder() { }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public HospitalClinic build() {
            return new HospitalClinic(this);
        }
    }

}

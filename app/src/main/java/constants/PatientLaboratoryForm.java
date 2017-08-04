package constants;

import interfacez.OutPatientConsultation;
import interfacez.PatientLaboratory;

/**
 * Created by casjohnpaul on 5/3/2017.
 */

public class PatientLaboratoryForm implements PatientLaboratory {

    private String validFrom;
    private String validUntil;


    private String referenceNumber;
    private String doctor;
    private String hospital;
    private String memberName;
    private String age;
    private String gender;
    private String company;
    private String companyId;
    private String remarks;
    private String dateEffectivity;
    private String validityDate;

    private String serviceType;
    private String diagnosis;
    private String procedure;

    public PatientLaboratoryForm(Builder builder) {
        validFrom = builder.validFrom;
        validUntil = builder.validUntil;


        referenceNumber = builder.referenceNumber;
        doctor = builder.doctor;
        hospital = builder.hospital;
        memberName = builder.memberName;
        age = builder.age;
        gender = builder.gender;
        remarks = builder.remarks;
        company = builder.company;
        companyId = builder.companyId;
        dateEffectivity = builder.dateEffectivity;
        validityDate = builder.validityDate;

        serviceType = builder.serviceType;
        diagnosis = builder.diagnosis;
        procedure = builder.procedure;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public String getValidUntil() {
        return validUntil;
    }


    public String getReferenceNumber() {
        return referenceNumber;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getHospital() {
        return hospital;
    }

    public String getMemberName() {
        return memberName;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }


    public String getCompany() {
        return company;
    }

    public String getRemarks() {
        return remarks;
    }

    public String getDateEffectivity() {
        return dateEffectivity;
    }

    public String getValidityDate() {
        return validityDate;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }


    public static class Builder {

        private String validFrom;
        private String validUntil;

        private String referenceNumber;
        private String doctor;
        private String hospital;
        private String memberName;


        private String age;
        private String gender;
        private String company;
        private String companyId;
        private String remarks;
        private String dateEffectivity;
        private String validityDate;

        private String serviceType;
        private String diagnosis;
        private String procedure;

        public Builder() {
        }

        public Builder validFrom(String validFrom) {
            this.validFrom = validFrom;
            return this;
        }

        public Builder validUntil(String validUntil) {
            this.validUntil = validUntil;
            return this;
        }


        public Builder referenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
            return this;
        }

        public Builder doctor(String doctor) {
            this.doctor = doctor;
            return this;
        }

        public Builder hospital(String hospital) {
            this.hospital = hospital;
            return this;
        }

        public Builder memberName(String memberName) {
            this.memberName = memberName;
            return this;
        }

        public Builder age(String age) {
            this.age = age;
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

        public Builder companyId(String companyId){
            this.companyId = companyId;
            return this;
        }

        public Builder remarks(String remarks) {
            this.remarks = remarks;
            return this;
        }

        public Builder dateEffectivity(String dateEffectivity) {
            this.dateEffectivity = dateEffectivity;
            return this;
        }

        public Builder validityDate(String validityDate) {
            this.validityDate = validityDate;
            return this;
        }

        public Builder serviceType(String serviceType){
            this.serviceType = serviceType;
            return this;
        }

        public Builder diagnosis(String diagnosis){
            this.diagnosis = diagnosis;
            return this;
        }


        public Builder procedure(String procedure){
            this.procedure = procedure;
            return this;
        }

        public PatientLaboratoryForm build() {
            return new PatientLaboratoryForm(this);
        }

    }

}


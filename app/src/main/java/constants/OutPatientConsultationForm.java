package constants;

import interfacez.OutPatientConsultation;

/**
 * Created by casjohnpaul on 5/3/2017.
 */

public class OutPatientConsultationForm implements OutPatientConsultation {

    private String validFrom;
    private String validUntil;

    private String dateOfConsult;
    private String referenceNumber;
    private String doctor;
    private String hospital;
    private String memberName;
    private String age;
    private String gender;
    private String memberId;
    private String company;
    private String remarks;
    private String dateEffectivity;
    private String validityDate;

    private String chiefComplaint;
    private String historyOfPresentIllness;
    private String pastOrFamilyHistory;

    private String serviceType;
    private String batchCode;

    public OutPatientConsultationForm(Builder builder) {
        validFrom = builder.validFrom;
        validUntil = builder.validUntil;

        dateOfConsult = builder.dateOfConsult;
        referenceNumber = builder.referenceNumber;
        doctor = builder.doctor;
        hospital = builder.hospital;
        memberName = builder.memberName;
        age = builder.age;
        gender = builder.gender;
        memberId = builder.memberId;
        remarks = builder.remarks;
        company = builder.company;
        dateEffectivity = builder.dateEffectivity;
        validityDate = builder.validityDate;

        chiefComplaint = builder.chiefComplaint;
        historyOfPresentIllness = builder.historyOfPresentIllness;
        pastOrFamilyHistory = builder.pastOrFamilyHistory;

        serviceType = builder.serviceType;
        batchCode = builder.batchCode;
    }

    public String getValidFrom() {
        return validFrom;
    }

    public String getValidUntil() {
        return validUntil;
    }

    public String getDateOfConsult() {
        return dateOfConsult;
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

    public String getMemberId() {
        return memberId;
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

    public String getChiefComplaint() {
        return chiefComplaint;
    }

    public String getHistoryOfPresentIllness() {
        return historyOfPresentIllness;
    }

    public String getPastOrFamilyHistory() {
        return pastOrFamilyHistory;
    }

    public String getServiceType() {
        return serviceType;
    }

    public String getBatchCode() {
        return batchCode;
    }

    public static class Builder {

        private String validFrom;
        private String validUntil;

        private String dateOfConsult;
        private String referenceNumber;
        private String doctor;
        private String hospital;
        private String memberName;
        private String age;
        private String gender;
        private String memberId;
        private String company;
        private String remarks;
        private String dateEffectivity;
        private String validityDate;

        private String chiefComplaint;
        private String historyOfPresentIllness;

        private String pastOrFamilyHistory;

        private String serviceType;

        private String batchCode;

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

        public Builder dateOfConsult(String dateOfConsult) {
            this.dateOfConsult = dateOfConsult;
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

        public Builder memberId(String memberId) {
            this.memberId = memberId;
            return this;
        }

        public Builder company(String company) {
            this.company = company;
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

        public Builder chiefComplaint(String chiefComplaint) {
            this.chiefComplaint = chiefComplaint;
            return this;
        }

        public Builder historyOfPresentIllness(String historyOfPresentIllness) {
            this.historyOfPresentIllness = historyOfPresentIllness;
            return this;
        }

        public Builder pastOrFamilyHistory(String pastOrFamilyHistory) {
            this.pastOrFamilyHistory = pastOrFamilyHistory;
            return this;
        }

        public Builder serviceType(String serviceType) {
            this.serviceType = serviceType;
            return this;
        }

        public Builder bactchCode(String batchCode) {
            this.batchCode = batchCode;
            return this;
        }

        public OutPatientConsultationForm build() {
            return new OutPatientConsultationForm(this);
        }

    }

}


package database.table;

/**
 * <p>
 *     The table name and fields
 * </p>
 *
 * @author John Paul Cas
 */
public interface Table {

    interface Specialization {

        String TABLE_NAME = "SPECIALIZATION";

        String CODE = "specializationCode";

        String DESCRIPTION = "specializationDescription";

        String COLUMNS[] = {CODE, DESCRIPTION};

    }

    interface Doctor {

        String TABLE_NAME = "DoctorList";

        String DOCTOR_CODE = "doctorCode";

        String LAST_NAME = "lastName";

        String FIRST_NAME = "firstName";

        String MIDDLE_NAME = "middleName";

        String SPECIALIZATION_DESCRIPTION = "specializationDescription";

        String SPECIALIZATION_CODE = "specializationCode";

        String W_TAX = "wtax";

        String GRACE_PERIOD = "gracePeriod";

        String VAT = "vat";

        String CONTACT_NUMBER = "contactNumber";

        String CITY = "city";

        String PROVINCE = "province";

        String REGION = "region";

        String PRC = "prc";

        String STREET_ADDRESS = "streetAddress";

        String ROOM_NUMBER = "roomNo";

        String SCHEDULE = "schedule";

        String COLUMN[] = {
                DOCTOR_CODE,
                LAST_NAME,
                FIRST_NAME,
                MIDDLE_NAME,
                SPECIALIZATION_DESCRIPTION,
                SPECIALIZATION_CODE,
                W_TAX,
                GRACE_PERIOD,
                VAT,
                CONTACT_NUMBER,
                CITY,
                PROVINCE,
                REGION,
                PRC,
                STREET_ADDRESS,
                ROOM_NUMBER,
                SCHEDULE
        };

    }

    interface Hospital {

        String TABLE_NAME = "hospital";

        String HOSPITAL_CODE = "hospitalCode";

        String HOSPITAL_NAME = "hospitalName";

        String KEYWORD = "keyword";

        String ALIEAS = "alias";

        String CATEGORY = "category";

        String COORDINATOR = "coordinator";

        String EXCLUDED = "excluded";

        String CITY = "city";

        String PROVINCE = "province";

        String REGION = "region";

        String PHONE_NUMBER = "phoneNo";

        String FAX_NUMBER = "faxno";

        String CONTACT_PERSON = "contactPerson";

        String STREET_ADDRESS = "streetAddress";

        String COLUMN[] = {
                HOSPITAL_CODE,
                HOSPITAL_NAME,
                KEYWORD,
                ALIEAS,
                CATEGORY,
                COORDINATOR,
                EXCLUDED,
                CITY,
                PROVINCE,
                REGION,
                PHONE_NUMBER,
                FAX_NUMBER,
                CONTACT_PERSON,
                STREET_ADDRESS
        };
    }

    interface Procedure {

        String TABLE_NAME = "procedure";

        String ID = "id";

        String SERVICE_CLASS_CODE = "serviceClassCode";

        String CODE = "procedureCode";

        String DESCRIPTION = "procedureDescription";

        String AMOUNT = "procedureAmount";

        String IS_SELECTED = "isSelected";

        String COLUMN[] = {
                ID,
                SERVICE_CLASS_CODE,
                CODE,
                DESCRIPTION,
                AMOUNT,
                IS_SELECTED
        };

    }

    interface Procedure2 {

        String DIAGNOSIS_CODE = "diagnosisCode";

        String DIAGNOSIS_DESCRIPTION = "diagnosisDescription";

        String ICD_10_CODE = "icd10Code";

        String PROCEDURE_ID = "procedureId";

        String PROCEDURE_CODE = "procedureCode";

        String APPROVAL_ID = "approvalId";

        String APPROVAL_TYPE = "approvalType";

        String AMOUNT = "ammount";

        String DIAGNOSIS_TYPE = "diagnosisType";

        String DIAGNOSIS_TYPE_DESCRIPTION = "diagnosisTypeDescription";

        String PRO_CLASS_CODE = "proClassCode";

    }

    interface Diagnosis {

        String DIAGNOSIS_CODE = "diagCode";

        String DIAGNOSIS_DESCRIPTION = "diagDesc";

        String DIAGNOSIS_REMARKS = "diagRemarks";

        String TYPE = "type";

        String TYPE_DESCRIPTION = "typeDesc";

        String ICD_CODE = "icd10Code";

        String ICD_DESCRIPTION = "icd10Desc";

        String STATUS = "status";

        String COLUMNS[] = {
                DIAGNOSIS_CODE,
                DIAGNOSIS_DESCRIPTION,
                DIAGNOSIS_REMARKS,
                TYPE,
                TYPE_DESCRIPTION,
                ICD_CODE,
                ICD_DESCRIPTION,
                STATUS};

    }

    interface Test {

        String TABLE_NAME = "test";

        String DIAGNOSIS_CODE = "diagnosisCode";

        String DIAGNOSIS_DESCRIPTION = "diagnosisDescription";

        String ICD_CODE = "icdCode";

        String PROCEDURE_CODE = "procedureCode";

        String PROCEDURE_NAME = "procedureName";

        String APPROVAL_ID = "approvalId";

        String APPROVAL_TYPE = "approvalType";

        String AMOUNT = "amount";

        String COST_CENTER = "costCenter";

        String PROCEDURE_GROUP_ID = "procedureGroupId";

        String ACTIVE = "active";

        String IS_SELECTED = "isSelected";

        String COLUMNS[] = {
                DIAGNOSIS_CODE,
                DIAGNOSIS_DESCRIPTION,
                ICD_CODE,
                PROCEDURE_CODE,
                PROCEDURE_NAME,
                APPROVAL_ID,
                APPROVAL_TYPE,
                AMOUNT,
                COST_CENTER,
                PROCEDURE_GROUP_ID,
                ACTIVE,
                IS_SELECTED
        };
    }

}

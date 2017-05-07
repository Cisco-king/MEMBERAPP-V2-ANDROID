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

}

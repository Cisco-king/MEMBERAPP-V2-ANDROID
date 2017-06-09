package utilities;

import android.os.Environment;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import constants.OutPatientConsultationForm;
import constants.FileGenerator;
import interfacez.OutPatientConsultation;
import timber.log.Timber;

/**
 * Created by casjohnpaul on 5/3/2017.
 */

public class PdfGenerator {

    public static final String TAG = "xyz";


    public static final void generatePdfLoaConsultationForm(
            OutPatientConsultationForm patientForm,
            InputStream stream) {
        // maternity, othertest, basictest

        String remarks = patientForm.getRemarks();
        Timber.d("remarks ############################### : %s", remarks);
        Timber.d("batchCode %s", patientForm.getBatchCode());

        String loaFileName = FileGenerator.genFileName(patientForm.getServiceType(), patientForm.getReferenceNumber());

        try {
            File pdfFolder = new File(Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DCIM), "MediCard");
            if (!pdfFolder.exists()) {
                pdfFolder.mkdir();
                Timber.d("generate folder name loaConsultation...");
            }

            //Create time stamp
            Date date = new Date();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(date);

            File loaFormFile = new File(pdfFolder, loaFileName + ".pdf");
            Timber.d("folder path %s", loaFormFile.toString());

            OutputStream output = new FileOutputStream(loaFormFile);

            PdfReader reader = new PdfReader(stream);
            PdfStamper stamper = new PdfStamper(reader, output);
            AcroFields fields = stamper.getAcroFields();

            remarks = trim(remarks);

            if (patientForm.getRemarks().length() == 1 && patientForm.getRemarks().equals(","))
                remarks = "";


            if (isFieldPresent(fields, OutPatientConsultation.VALID_FROM)) {
                fields.setField(OutPatientConsultationForm.VALID_FROM, patientForm.getValidFrom());

            }

            if (isFieldPresent(fields, OutPatientConsultation.VALID_UNTIL)) {
                fields.setField(OutPatientConsultationForm.VALID_UNTIL, patientForm.getValidUntil());
            }

            if (isFieldPresent(fields, OutPatientConsultation.DATE_OF_CONSULT)) {
                fields.setField(OutPatientConsultationForm.DATE_OF_CONSULT, patientForm.getDateOfConsult());
            }

            if (isFieldPresent(fields, OutPatientConsultation.REFERENCE_NUMBER)) {
                fields.setField(OutPatientConsultationForm.REFERENCE_NUMBER, patientForm.getReferenceNumber());
            }

            if (isFieldPresent(fields, OutPatientConsultation.DOCTOR)) {
                fields.setField(OutPatientConsultationForm.DOCTOR, patientForm.getDoctor());
            }

            if (isFieldPresent(fields, OutPatientConsultation.HOSPITAL)) {
                fields.setField(OutPatientConsultationForm.HOSPITAL, patientForm.getHospital());
            }

            if (isFieldPresent(fields, OutPatientConsultation.MEMBER_NAME)) {
                fields.setField(OutPatientConsultationForm.MEMBER_NAME, patientForm.getMemberName());
            }

            if (isFieldPresent(fields, OutPatientConsultation.AGE)) {
                fields.setField(OutPatientConsultationForm.AGE, patientForm.getAge());
            }

            if (isFieldPresent(fields, OutPatientConsultation.GENDER)) {
                fields.setField(OutPatientConsultationForm.GENDER, patientForm.getGender());
            }

            if (isFieldPresent(fields, OutPatientConsultation.MEMBER_ID)) {
                fields.setField(OutPatientConsultationForm.MEMBER_ID, patientForm.getMemberId());
            }

            if (isFieldPresent(fields, OutPatientConsultation.COMPANY)) {
                fields.setField(OutPatientConsultationForm.COMPANY, patientForm.getCompany());
            }

            if (isFieldPresent(fields, OutPatientConsultation.REMARKS)) {
                fields.setField(OutPatientConsultationForm.REMARKS, remarks);
            }

            if (isFieldPresent(fields, OutPatientConsultation.DATE_EFFECTIVITY)) {
                fields.setField(OutPatientConsultationForm.DATE_EFFECTIVITY, patientForm.getDateEffectivity());
            }

            if (isFieldPresent(fields, OutPatientConsultation.VALIDITY_DATE)) {
                fields.setField(OutPatientConsultationForm.VALIDITY_DATE, patientForm.getValidityDate());
            }

            if (isFieldPresent(fields, OutPatientConsultation.CHIEF_COMPLAINT)) {
                fields.setField(OutPatientConsultationForm.CHIEF_COMPLAINT, patientForm.getChiefComplaint());
            }

            if (isFieldPresent(fields, OutPatientConsultation.HISTORY_oF_PERSENT_ILLNESS)) {
                fields.setField(OutPatientConsultationForm.HISTORY_oF_PERSENT_ILLNESS, patientForm.getHistoryOfPresentIllness());
            }

            if (isFieldPresent(fields, OutPatientConsultation.PAST_OR_FAMILY_HISTORY)) {
                fields.setField(OutPatientConsultationForm.PAST_OR_FAMILY_HISTORY, patientForm.getPastOrFamilyHistory());
            }

            Timber.d("patientForm %s", patientForm.getBatchCode());
            if (isFieldPresent(fields, OutPatientConsultation.BATCH_CODE)) {
                fields.setField(OutPatientConsultationForm.BATCH_CODE, patientForm.getBatchCode());
            }

//            fields.setField(OutPatientConsultationForm.VALID_UNTIL, patientForm.getValidUntil());
//            fields.setField(OutPatientConsultationForm.DATE_OF_CONSULT, patientForm.getDateOfConsult());
//            fields.setField(OutPatientConsultationForm.REFERENCE_NUMBER, patientForm.getReferenceNumber());
//            fields.setField(OutPatientConsultationForm.DOCTOR, patientForm.getDoctor());
//            fields.setField(OutPatientConsultationForm.HOSPITAL, patientForm.getHospital());
//            fields.setField(OutPatientConsultationForm.MEMBER_NAME, patientForm.getMemberName());
//            fields.setField(OutPatientConsultationForm.AGE, patientForm.getAge());
//            fields.setField(OutPatientConsultationForm.GENDER, patientForm.getGender());
//            fields.setField(OutPatientConsultationForm.MEMBER_ID, patientForm.getMemberId());
//            fields.setField(OutPatientConsultationForm.COMPANY, patientForm.getCompany());
//            fields.setField(OutPatientConsultationForm.REMARKS, remarks);
//            fields.setField(OutPatientConsultationForm.DATE_EFFECTIVITY, patientForm.getDateEffectivity());
//            fields.setField(OutPatientConsultationForm.VALIDITY_DATE, patientForm.getValidityDate());
//            fields.setField(OutPatientConsultationForm.CHIEF_COMPLAINT, patientForm.getChiefComplaint());
//            fields.setField(OutPatientConsultationForm.HISTORY_oF_PERSENT_ILLNESS, patientForm.getHistoryOfPresentIllness());
//            fields.setField(OutPatientConsultationForm.PAST_OR_FAMILY_HISTORY, patientForm.getPastOrFamilyHistory());

            // remove the field to make the pdf look like clean
            stamper.setFormFlattening(true);
            stamper.close();

        } catch (IOException e) {
            Timber.d("error message %s", e.toString());
        } catch (DocumentException e) {
            Timber.d("error message %s", e.toString());
        }
    }

    public static boolean isFieldPresent(AcroFields fields, String fieldName) {
        return fields.getFieldItem(fieldName) != null;
    }

    public static final String trimText(String text) {
        return text.replace("\n", "  ").replace("\r", "");
    }

    public static final String trim(String sentence) {
        return sentence.replace(",,", "").replace("''", "").replace(", , ", "");
    }


}

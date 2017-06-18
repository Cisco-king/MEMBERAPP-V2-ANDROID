package com.medicard.member.core.session;

import services.model.Diagnosis;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class DiagnosisSession {

    public static final Diagnosis diagnosis = new Diagnosis();

    public static void setDiagnosis(Diagnosis content) {
        diagnosis.init(content);
    }

    public static Diagnosis getDiagnosis() {
        return diagnosis;
    }
}

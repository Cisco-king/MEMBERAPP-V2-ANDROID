package com.medicard.member.core.session;

import com.medicard.member.core.model.DiagnosisTests;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by casjohnpaul on 6/19/2017.
 */
public class DiagnosisTestSession {

    private static List<DiagnosisTests> diagnosisTest = new ArrayList<>();
    private static boolean displayAll = false;


    public static void setDiagnosisTests(DiagnosisTests diagnosisTests) {
        diagnosisTest.add(diagnosisTests);
    }

    public static DiagnosisTests getDiagnosisTests(int position) {
        return diagnosisTest.get(position);
    }

    public static List<DiagnosisTests> getAllDiagnosisTests() {
        return diagnosisTest;
    }

    public static void releaseContent() {
        diagnosisTest.clear();
        diagnosisTest = new ArrayList<>();
    }

    public static void setDisplayAll(boolean displayAll) {
        DiagnosisTestSession.displayAll = displayAll;
    }

    public static boolean isDisplayAll() {
        return displayAll;
    }
}

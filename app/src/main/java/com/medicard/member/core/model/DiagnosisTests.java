package com.medicard.member.core.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import services.model.Diagnosis;
import services.model.Test;

/**
 * Created by casjohnpaul on 6/19/2017.
 */

public class DiagnosisTests  implements Serializable{

    private Diagnosis diagnosis;
    private List<Test> tests;


    public DiagnosisTests() {
        this.diagnosis = new Diagnosis();
        this.tests = new ArrayList<>();
    }

    public DiagnosisTests(Diagnosis diagnosis, List<Test> tests) {
        this.diagnosis = diagnosis;
        this.tests = tests;
    }

    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis diagnosis) {
        this.diagnosis = diagnosis;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }

    public void addTest(Test test) {
        this.tests.add(test);
    }
}

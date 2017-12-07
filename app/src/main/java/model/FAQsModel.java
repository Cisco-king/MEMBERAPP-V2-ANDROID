package model;

import java.util.ArrayList;

/**
 * Created by IPC on 11/22/2017.
 */

public class FAQsModel {

    private ArrayList<MaceFAQs> maceFaqs;

    public ArrayList<MaceFAQs> getMaceFaqs() {
        return maceFaqs;
    }

    public void setMaceFaqs(ArrayList<MaceFAQs> maceFaqs) {
        this.maceFaqs = maceFaqs;
    }


    @Override
    public String toString() {
        return "ClassPojo [maceFaqs = " + maceFaqs + "]";
    }
}

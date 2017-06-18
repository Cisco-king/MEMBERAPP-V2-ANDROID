package com.medicard.member.core.constant;

import com.medicard.member.core.model.SimpleObject;

/**
 * Created by casjohnpaul on 6/18/2017.
 */
public class NewTest {

    public static final SimpleObject consult = new SimpleObject();


    public static void addReasonForConsult(String reason) {
        consult.setSimple(reason);
    }

    public static String getReasonForConsult() {
        if (consult != null) {
            return consult.getSimple();
        } else {
            return null;
        }
    }

}

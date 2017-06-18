package com.medicard.member.core.session;

import com.medicard.member.core.model.SimpleObject;

/**
 * Created by casjohnpaul on 6/18/2017.
 */

public class ConsultSession {

    private static final SimpleObject reasonForConsult = new SimpleObject();

    public static void setReasonForConsult(String reason) {
        reasonForConsult.setSimple(reason);
    }

    public static String getReasonForConsult() {
        return reasonForConsult.getSimple();
    }
}

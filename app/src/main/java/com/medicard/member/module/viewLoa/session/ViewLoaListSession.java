package com.medicard.member.module.viewLoa.session;

import com.medicard.member.core.model.DiagnosisTests;

import java.util.ArrayList;
import java.util.List;

import services.model.MaceRequest;

/**
 * Created by macbookpro on 7/26/17.
 */

public class ViewLoaListSession {

    private static List<MaceRequest> maceRequestsList = new ArrayList<>();


    public static void setMaceRequests(MaceRequest maceRequests) {
        maceRequestsList.add(maceRequests);
    }

    public static MaceRequest getMaceRequests(int position) {
        return maceRequestsList.get(position);
    }

    public static int getSize(){
        return maceRequestsList.size();
    }

    public static List<MaceRequest> getAllMaceRequestLists() {
        return maceRequestsList;
    }

}

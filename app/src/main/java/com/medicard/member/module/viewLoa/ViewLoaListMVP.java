package com.medicard.member.module.viewLoa;

import com.medicard.member.module.test.TestMvp;

import java.util.List;

import modules.base.Mvp;
import services.model.MaceRequest;

/**
 * Created by macbookpro on 7/26/17.
 */

public interface ViewLoaListMVP {

    interface View extends Mvp.View{
        void displayLoaList(List<MaceRequest> maceRequests);

    }

    interface Presenter extends Mvp.Presenter<View> {
        void loadLoaList(List<MaceRequest> maceRequestList);

    }

}

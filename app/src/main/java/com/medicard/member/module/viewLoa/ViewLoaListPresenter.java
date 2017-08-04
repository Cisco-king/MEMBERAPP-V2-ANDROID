package com.medicard.member.module.viewLoa;

import java.util.List;

import services.model.MaceRequest;

/**
 * Created by macbookpro on 7/26/17.
 */

public class ViewLoaListPresenter implements ViewLoaListMVP.Presenter{


    private ViewLoaListMVP.View viewLoaView;



    @Override
    public void attachView(ViewLoaListMVP.View view) {
        viewLoaView = view;
    }

    @Override
    public void detachView() {
        viewLoaView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void loadLoaList(List<MaceRequest> maceRequestList) {

    }
}

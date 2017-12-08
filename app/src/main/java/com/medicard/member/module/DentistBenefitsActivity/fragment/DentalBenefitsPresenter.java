package com.medicard.member.module.DentistBenefitsActivity.fragment;

import android.content.Context;

import java.net.SocketTimeoutException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import services.response.DentalBenefitsResponse;
import utilities.SharedPref;

/**
 * Created by macbookpro on 7/31/17.
 */

public class DentalBenefitsPresenter implements DentalBenefitsMVP.Presenter {

    Context context;
    private DentalBenefitsMVP.View dentalBenefitsView;

    public DentalBenefitsPresenter(Context context){
        this.context = context;
    }

    @Override
    public void attachView(DentalBenefitsMVP.View view) {
        dentalBenefitsView = view;
    }

    @Override
    public void detachView() {
        dentalBenefitsView = null;
    }

    @Override
    public void attachCallback() {

    }

    @Override
    public void detachCallback() {

    }

    @Override
    public void loadDentalBenefits(String memberCode, final DentalBenefitCallback callback) {
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getDentalBenefitByMemberCode("2621761")
                .enqueue(new Callback<DentalBenefitsResponse>() {
                    @Override
                    public void onResponse(Call<DentalBenefitsResponse> call, Response<DentalBenefitsResponse> response) {
                        if(response.body() != null){
                            System.out.println("=============== MENSAHE " + response.body().getDentalBenefits());
                            callback.onSuccess(response.body().getDentalBenefits());
                        }
                        callback.onSuccess("");
                    }

                    @Override
                    public void onFailure(Call<DentalBenefitsResponse > call, Throwable t) {

                    }
                });

    }

    public interface DentalBenefitCallback{
        void onSuccess(String message);
    }
}

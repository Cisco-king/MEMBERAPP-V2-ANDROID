package com.medicard.member.module.viewLoa;

import android.content.Context;
import android.os.Bundle;

import com.medicard.member.module.viewLoa.session.ViewLoaListSession;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.SimpleData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import services.AppInterface;
import services.AppService;
import services.model.MaceRequest;
import services.response.LoaListResponse;

/**
 * Created by macbookpro on 7/26/17.
 */

public class ViewLoaRetrieve {

    Context context;
    public ViewLoaRetrieve(Context context){
        this.context = context;
    }

    public void getLoa(String memberCode, final ViewLoaRetrieveCallback callback) {
        AppInterface appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.getLoaList(memberCode)
                .enqueue(new Callback<LoaListResponse>() {
                    @Override
                    public void onResponse(Call<LoaListResponse> call, Response<LoaListResponse> response) {
                        if(response.isSuccessful()){
                            try {
                                if(response.body()!=null){
                                    callback.onSuccess(response.body().getLoaList());
                                }else {
                                    callback.onFailure();
                                }
                            }catch (Exception e){
                                e.printStackTrace();
                                callback.onFailure();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<LoaListResponse> call, Throwable t) {
                        callback.onFailure();
                    }
                });

    }

    public void replactDataArray(ArrayList<SimpleData> masterList, ArrayList<SimpleData> temp) {
        masterList.clear();
        masterList.addAll(temp);
        temp.clear();
    }

    public interface ViewLoaRetrieveCallback{

        void onSuccess(List<MaceRequest> maceRequests);

        void onFailure();

        void gotoLoaPage(List<MaceRequest> maceRequestList, int position);
    }
}

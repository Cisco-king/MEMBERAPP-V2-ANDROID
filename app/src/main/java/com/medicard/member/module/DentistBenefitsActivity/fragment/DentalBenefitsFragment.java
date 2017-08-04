package com.medicard.member.module.DentistBenefitsActivity.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.module.DentistBenefitsActivity.DentalBenefitsActivity;
import com.medicard.member.module.DiagnosisTallyActivity.DiagnosisTallyActivity;
import com.medicard.member.module.base.BaseActivity;
import com.medicard.member.module.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by macbookpro on 7/31/17.
 */

public class DentalBenefitsFragment extends BaseFragment  {

    DentalBenefitsMVP.Presenter presenter;
    DentalBenefitsActivity listener;
    private String memberCode = "2621763";

    @BindView(R.id.tv_dental_benefits_details)
    TextView tv_dental_benefits_details;
    String message;

   public static Fragment newInstance(){
       DentalBenefitsFragment fragment = new DentalBenefitsFragment();
       return fragment;
   }


    @Override
    protected void initComponents(View view, Bundle savedInstanceState) {
        super.initComponents(view, savedInstanceState);


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if(context instanceof BaseActivity){
            this.listener = (DentalBenefitsActivity) context;
        }
    }

    @Override
    public int getLayoutResource() {
        return R.layout.content_dental_benefits;


    }

}

package com.medicard.member.module.termsandcondition;

import android.annotation.TargetApi;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.medicard.member.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by casjohnpaul on 6/21/2017.
 */

public class TermsAndCondition extends AppCompatActivity {

    public static final String TITLE = "title";

    FancyButton btn_back;
    @BindView(R.id.tvConforme) TextView tvConforme;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);
        ButterKnife.bind(this);
        String title = getIntent().getStringExtra(TITLE);
        bindData(title);
        setupWindowAnimations();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_back = (FancyButton) findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void bindData(String title) {
        tvConforme.setText(title);
    }


    @TargetApi(21)
    private void setupWindowAnimations() {
        // We are not interested in defining a new Enter Transition. Instead we change default transition duration
        getWindow().getEnterTransition().setDuration(getResources().getInteger(R.integer.anim_duration_long));
    }

}

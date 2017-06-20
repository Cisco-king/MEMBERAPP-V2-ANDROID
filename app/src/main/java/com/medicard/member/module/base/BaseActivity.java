package com.medicard.member.module.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.medicard.member.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;

/**
 * Created by casjohnpaul on 6/17/2017.
 */

public abstract class BaseActivity extends AppCompatActivity {


    @BindView(R.id.toolbar) Toolbar toolbar;

    @BindView(R.id.toolbarBack) FancyButton toolbarBack;
    @BindView(R.id.toolbarTitle) TextView toolbarTitle;

    protected Context context;

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        initComponents(bundle);
    }

    protected void initComponents(Bundle savedInstanceState) {
        setContentView(getLayoutResource());
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        context = this;
        toolbarTitle.setText(activityTitle());
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack() { finish(); }

    protected abstract String activityTitle();

    protected abstract int getLayoutResource();

}

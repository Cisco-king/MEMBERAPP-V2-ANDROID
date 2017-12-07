package com.medicard.member.module.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.Visibility;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.medicard.member.R;
import com.medicard.member.core.utilities.TransitionHelper;

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

    @SuppressWarnings("unchecked")
    protected void transitionTo(Intent i) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivity(i, transitionActivityOptions.toBundle());
    }

    protected void transitionToResult(Intent i, int code) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(this, true);
        ActivityOptionsCompat transitionActivityOptions = ActivityOptionsCompat.makeSceneTransitionAnimation(this, pairs);
        startActivityForResult(i, code, transitionActivityOptions.toBundle());
    }

    protected void setupWindowAnimations() {
        Transition transition = buildEnterTransition();
        getWindow().setEnterTransition(transition);
    }

    protected Visibility buildEnterTransition() {
        Slide enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        enterTransition.setSlideEdge(Gravity.RIGHT);
        return enterTransition;
    }

    private Visibility buildReturnTransition() {
        Visibility enterTransition = new Slide();
        enterTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
        return enterTransition;
    }

    @OnClick(R.id.toolbarBack)
    public void onClickBack() {
        /*Visibility returnTransition = buildReturnTransition();
        getWindow().setReturnTransition(returnTransition);*/
        finishAfterTransition();
    }

    protected abstract String activityTitle();

    protected abstract int getLayoutResource();

}

package com.medicard.member;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import fragments.fragment_memberInfo;
import mehdi.sakout.fancybuttons.FancyButton;
import model.ChangePassword;
import utilities.SharedPref;


public class AccountActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2, fab3;
    private Animation fab_open, fab_close, rotate_forward, rotate_backward, fade_in, fade_out, flip;
    ImageView blackBG;
    TextView tv_print, tv_email, tv_view;

    Button btn_viewMember;
    CircleImageView iv_user;
    FancyButton btn_back;
    Context context;
    Toolbar toolbar;
    SharedPref s = new SharedPref();

    private Animation animation1;
    private Animation animation2;
    private boolean isBackOfCardShowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        context = this;

        init();

    }

    private void init() {
        Fragment fragment = null;
        fragment = new fragment_memberInfo();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();


        toolbar = (Toolbar) findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        btn_back = (FancyButton) toolbar.findViewById(R.id.btn_back);
        btn_viewMember = (Button) findViewById(R.id.btn_viewMember);
        iv_user = (CircleImageView) findViewById(R.id.iv_user);

        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_print = (TextView) findViewById(R.id.tv_print);
        tv_view = (TextView) findViewById(R.id.tv_view);

        blackBG = (ImageView) findViewById(R.id.blackBG);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab3);

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);
        fade_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_animation);
        fade_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_animation);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate_backward);
        animation1 = AnimationUtils.loadAnimation(this, R.anim.to_middle);
        animation2 = AnimationUtils.loadAnimation(this, R.anim.from_middle);
        animation1.setAnimationListener(this);
        animation2.setAnimationListener(this);


        btn_viewMember.setOnClickListener(this);
        btn_back.setOnClickListener(this);
        iv_user.setOnClickListener(this);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);

        btn_back.setVisibility(View.GONE);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_user:
                flipImage();
                break;

            case R.id.btn_viewMember:
                Log.d("back is touch", "back is touch");
                break;

            case R.id.fab:

                animateFAB();
                break;
            case R.id.fab1:

                animateFAB();
                break;
            case R.id.fab2:

                break;
            case R.id.fab3:

                break;
        }

    }

    private void flipImage() {
        iv_user.clearAnimation();
        iv_user.setAnimation(animation1);
        iv_user.startAnimation(animation1);
    }

    public void animateFAB() {

        if (isFabOpen) {
            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);

            tv_email.setAnimation(fab_close);
            tv_print.setAnimation(fab_close);
            tv_view.setAnimation(fab_close);

            blackBG.startAnimation(fade_out);
            blackBG.setVisibility(View.GONE);

            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);

            tv_email.setAnimation(fab_open);
            tv_print.setAnimation(fab_open);
            tv_view.setAnimation(fab_open);

            blackBG.startAnimation(fade_in);
            blackBG.setVisibility(View.VISIBLE);

            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isFabOpen = true;

        }
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

        if (animation == animation1) {
            if (isBackOfCardShowing) {
              //  iv_user.setImageResource(R.drawable.icon);
            } else {
               // iv_user.setImageResource(R.drawable.imageplaceholder);
            }
            iv_user.clearAnimation();
            iv_user.setAnimation(animation2);
            iv_user.startAnimation(animation2);
        } else {
            isBackOfCardShowing = !isBackOfCardShowing;
        }

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}

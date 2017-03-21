package com.medicard.member;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;


import fragments.fragment_changePassword;
import fragments.fragment_memberInfo;

import utilities.SharedPref;
import v2.fragment_loaRequest;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPref s = new SharedPref();

    TextView tv_memberInfo;

    FrameLayout container_body;
    DrawerLayout drawer;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;
    Toolbar toolbar;
    TextView tv_loa_req, btn_logOut, tv_name, tv_account_settings;
    ImageButton btn_nav;
    Context context;
    TextView tv_header;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        init();
    }

    private void init() {
        context = this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // MOVING BURGER MENU TO THE RIGHT`
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);

        container_body = (FrameLayout) findViewById(R.id.container_body);
        tv_memberInfo = (TextView) findViewById(R.id.tv_memberInfo);
        tv_account_settings = (TextView) findViewById(R.id.tv_account_settings);
        btn_logOut = (TextView) findViewById(R.id.btn_logOut);
        btn_nav = (ImageButton) findViewById(R.id.btn_nav);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_header = (TextView) findViewById(R.id.tv_header);
        tv_loa_req = (TextView) findViewById(R.id.tv_loa_req);
        btn_nav.setOnClickListener(this);
        tv_account_settings.setOnClickListener(this);
        btn_logOut.setOnClickListener(this);
        tv_memberInfo.setOnClickListener(this);
        tv_loa_req.setOnClickListener(this);


        tv_name.setText(s.getStringValue(s.USER, s.NAME, context));


        tv_header.setText("My Account");

        fragment = null;
        fragment = new fragment_memberInfo();
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container_body, fragment);
        fragmentTransaction.commit();

    }

    @Override
    public void onBackPressed() {


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_memberInfo:
                if (fragment.equals(fragment_memberInfo.class)) {
                    closeDrawer() ;
                } else {

                    startActivity(new Intent(NavigationActivity.this, NavigationActivity.class));
                    finish();
                }

                break;

            case R.id.tv_account_settings:



                tv_header.setText("Account Settings");

                fragment = null;
               fragment = new fragment_changePassword();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();

                closeDrawer() ;

                break;

            case R.id.btn_logOut:


                new AlertDialog.Builder(context)
                        .setTitle("Hold On")
                        .setMessage("Are you sure you want to logout?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                SharedPref sharedPref = new SharedPref();
                                sharedPref.setStringValue(sharedPref.USER, sharedPref.masterUSERNAME, "", context);
                                sharedPref.setStringValue(sharedPref.USER, sharedPref.masterPASSWORD, "", context);

                                startActivity(new Intent(NavigationActivity.this, SignInActivity.class));
                                finish();
                            }

                        })
                        .setNegativeButton("No", null)
                        .show();

                closeDrawer() ;

                break;

            case R.id.btn_nav:
                closeDrawer() ;
                break;


            case R.id.tv_loa_req:

                tv_header.setText(getString(R.string.my_loa_req));
                fragment = null;
                fragment = new fragment_loaRequest();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();

                closeDrawer() ;
                break;
        }
    }

    private void closeDrawer(){

        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT);
        } else {
            drawer.openDrawer(Gravity.RIGHT);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPref s = new SharedPref();
        Log.d("REFRESH1", "REFRESH1");

    }

}

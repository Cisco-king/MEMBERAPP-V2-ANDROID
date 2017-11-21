package com.medicard.member;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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


import com.medicard.member.module.viewLoa.ViewLoaListFragment;
import com.tapadoo.alerter.Alert;

import butterknife.BindView;
import butterknife.ButterKnife;
import fragments.fragment_changePassword;
import fragments.fragment_dentistList;
import fragments.fragment_doctorList;
import fragments.fragment_hospitalList;
import fragments.fragment_memberInfo;

import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.SharedPref;
import v2.fragment_loaRequest;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener {

    SharedPref s = new SharedPref();



    @BindView(R.id.container_body)
    FrameLayout container_body;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer_layout;

    @BindView(R.id.tv_memberInfo)
    TextView tv_memberInfo;
    @BindView(R.id.tv_account_settings)
    TextView tv_account_settings;
    @BindView(R.id.tv_name)
    TextView tv_name;
    @BindView(R.id.tv_loa_req)
    TextView tv_loa_req;
    @BindView(R.id.btn_logOut)
    TextView btn_logOut;
    @BindView(R.id.tv_header)
    TextView tv_header;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.btn_nav)
    ImageButton btn_nav;

    @BindView(R.id.nav_dentist)
    TextView nav_dentist;
    @BindView(R.id.nav_doctor)
    TextView nav_doctor;
    @BindView(R.id.nav_hospital)
    TextView nav_hospital;




    Context context;
    Fragment fragment;
    FragmentTransaction fragmentTransaction;

    ConnectivityManager connectivityManager;
    NetworkInfo wifiInfo, mobileInfo;
    boolean connected = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        ButterKnife.bind(this);

        init();
    }

    private void init() {
        context = this;
        setSupportActionBar(toolbar);

        // MOVING BURGER MENU TO THE RIGHT`
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setDisplayShowHomeEnabled(false);


        btn_nav.setOnClickListener(this);
        tv_account_settings.setOnClickListener(this);
        btn_logOut.setOnClickListener(this);
        tv_memberInfo.setOnClickListener(this);
        tv_loa_req.setOnClickListener(this);
        nav_dentist.setOnClickListener(this);
        nav_doctor.setOnClickListener(this);
        nav_hospital.setOnClickListener(this);

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
        tv_header.setText("My Account");
        if (fragment.equals(fragment_memberInfo.class)) {
            if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                drawer_layout.closeDrawer(Gravity.LEFT);
            }
        } else {
            if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
                drawer_layout.closeDrawer(Gravity.LEFT);
            }
            fragment = null;
            fragment = new fragment_memberInfo();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.tv_memberInfo:
                tv_header.setText("My Account");
                if (fragment.equals(fragment_memberInfo.class)) {
                    closeDrawer();
                } else {
                    closeDrawer();
                    fragment = null;
                    fragment = new fragment_memberInfo();
                    fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.container_body, fragment);
                    fragmentTransaction.commit();
                }

                break;

            case R.id.nav_dentist:
                tv_header.setText("Dentist List");
                fragment = null;
                fragment = new fragment_dentistList();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();
                closeDrawer();
                break;

            case R.id.nav_doctor:
                tv_header.setText("Doctor List");
                fragment = null;
                fragment = new fragment_doctorList();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();
                closeDrawer();

                break;

            case R.id.nav_hospital:
                tv_header.setText("Hospital List");

                fragment = null;
                fragment = new fragment_hospitalList();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();
                closeDrawer();

                break;


            case R.id.tv_account_settings:
                tv_header.setText("Account Settings");

                fragment = null;
                fragment = new fragment_changePassword();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_body, fragment);
                fragmentTransaction.commit();

                closeDrawer();

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

                closeDrawer();

                break;

            case R.id.btn_nav:
                closeDrawer();
                break;


            case R.id.tv_loa_req:
                tv_header.setText(getString(R.string.my_loa_req));
                fragment = null;
                fragment = new fragment_loaRequest();
                fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.container_body, ViewLoaListFragment.newInstance());
                fragmentTransaction.commit();
                closeDrawer();
                break;
        }
    }

    private void closeDrawer() {

        if (drawer_layout.isDrawerOpen(Gravity.LEFT)) {
            drawer_layout.closeDrawer(Gravity.LEFT);
        } else {
            drawer_layout.openDrawer(Gravity.LEFT);
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPref s = new SharedPref();
        Log.d("REFRESH1", "REFRESH1");

    }

}

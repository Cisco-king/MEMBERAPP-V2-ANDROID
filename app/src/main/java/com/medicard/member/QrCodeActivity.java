package com.medicard.member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import mehdi.sakout.fancybuttons.FancyButton;
import utilities.DateConverter;
import utilities.QrCodeCreator;

public class QrCodeActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView img_qrcode;
    TextView tv_name, ed_idNumber;
    Toolbar toolbar;
    String memberId = "";
    String BIRTHDAY = "";
    FancyButton btn_back;

    DateConverter dateConverter = new DateConverter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr_code);

        init();

    }

    private void init() {
        QrCodeCreator qrCodeCreator = new QrCodeCreator();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        memberId = getIntent().getExtras().getString("MEMBER_ID");
        BIRTHDAY = getIntent().getExtras().getString("BIRTHDAY");


        btn_back = (FancyButton) toolbar.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        img_qrcode = (ImageView) findViewById(R.id.img_qrcode);
        img_qrcode.setImageBitmap(qrCodeCreator.getBitmapFromString(memberId));
        tv_name = (TextView) findViewById(R.id.tv_name);
        ed_idNumber = (TextView) findViewById(R.id.ed_idNumber);

        tv_name.setText(getIntent().getExtras().getString("NAME"));
        ed_idNumber.setText(memberId + "");
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.btn_back:

//                startActivity(new Intent(QrCodeActivity.this, MemberAccountActivity.class));
//                overridePendingTransition(R.anim.push_down_in , 0);
                finish();
                break;
        }
    }
}

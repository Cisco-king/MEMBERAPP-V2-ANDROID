package android.medicard.com.medicard;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.frosquivel.magicalcamera.MagicalCamera;
import com.squareup.picasso.Callback;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import mehdi.sakout.fancybuttons.FancyButton;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import services.App;
import services.AppInterface;
import services.AppService;
import services.AppServicewithBasicAuth;
import utilities.AgeCorrector;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.GenderPicker;
import utilities.ImageConverters;
import utilities.NetworkTest;
import utilities.Permission;
import utilities.SetBlockedUser;
import utilities.SharedPref;
import utilities.ShowImagePicker;
import utilities.StatusSetter;
import v2.RequestButtonsActivity;


public class MemberAccountActivity extends AppCompatActivity implements View.OnClickListener, Animation.AnimationListener {
    LinearLayout blackBG;
    FloatingActionButton fab, fab1;
    private Animation animation1;
    private Animation animation2;

    private Boolean isFabOpen = true;
    ShowImagePicker showImagePicker = new ShowImagePicker();
    ImageConverters imageConverter = new ImageConverters();
    AlertDialogCustom alertDialogCustom = new AlertDialogCustom();
    GenderPicker genderPicker = new GenderPicker();
    NetworkTest networkTest = new NetworkTest();
    SharedPref sharedPref = new SharedPref();
    ProgressDialog pd;
    Permission permission;
    FancyButton btn_back;
    Toolbar toolbar;
    Button btn_displayQR;
    Context context;
    ProgressBar progressBar;
    TextView tv_view;
    ScrollView sv_scroll;
    TextView tv_name, tv_memberID, tv_birth, tv_age, tv_civil_status, tv_gender, tv_company;
    TextView tv_plan, tv_account_status, tv_member_code, tv_member_type, tv_effective_date, tv_validity_date;
    CircleImageView ci_edit, circleImageView2;

    final int CAMERA_RQ = 200;
    final int GALLERY_RQ = 300;

    public String MEMBER_ID = "";
    public String BIRTHDAY = "";
    final String TAG = "ACCOUNT_act";

    File output = null;
    boolean userIsPrincipal = false;
    TextView[] tv_remarks = new TextView[8];

    public String GALLERY = "Gallery";
    public String CAMERA = "Camera";
    public String CANCEL = "Cancel";

    AlertDialog.Builder builder;
    AlertDialog alert;

    private Animation fab_open, fab_close, rotate_forward, rotate_backward, fade_in, fade_out, flip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_account);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        init();


    }

    private void init() {
        context = this;


        blackBG = (LinearLayout) findViewById(R.id.blackBG);
        tv_view = (TextView) findViewById(R.id.tv_view);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab1 = (FloatingActionButton) findViewById(R.id.fab1);


        fab_open = AnimationUtils.loadAnimation(context, R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(context, R.anim.fab_close);
        fade_in = AnimationUtils.loadAnimation(context, R.anim.fade_in_animation);
        fade_out = AnimationUtils.loadAnimation(context, R.anim.fade_out_animation);
        rotate_forward = AnimationUtils.loadAnimation(context, R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(context, R.anim.rotate_backward);
        animation1 = AnimationUtils.loadAnimation(context, R.anim.to_middle);
        animation2 = AnimationUtils.loadAnimation(context, R.anim.from_middle);
        animation1.setAnimationListener(this);
        animation2.setAnimationListener(this);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);


        pd = new ProgressDialog(MemberAccountActivity.this, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Uploading Photo...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);


        MEMBER_ID = getIntent().getExtras().getString("MEMCODE");
        BIRTHDAY = getIntent().getExtras().getString("BIRTHDAY");
        permission = new Permission();

        circleImageView2 = (CircleImageView) findViewById(R.id.circleImageView2);

        tv_birth = (TextView) findViewById(R.id.tv_birth);
        tv_age = (TextView) findViewById(R.id.tv_age);
        tv_civil_status = (TextView) findViewById(R.id.tv_civil_status);
        tv_gender = (TextView) findViewById(R.id.tv_gender);
        tv_company = (TextView) findViewById(R.id.tv_company);

        tv_account_status = (TextView) findViewById(R.id.tv_account_status);
        tv_member_code = (TextView) findViewById(R.id.tv_member_code);
        tv_member_type = (TextView) findViewById(R.id.tv_member_type);
        tv_effective_date = (TextView) findViewById(R.id.tv_effective_date);
        tv_validity_date = (TextView) findViewById(R.id.tv_validity_date);
        tv_plan = (TextView) findViewById(R.id.tv_plan);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        sv_scroll = (ScrollView) findViewById(R.id.sv_scroll);

        tv_remarks[0] = (TextView) findViewById(R.id.tv_remarks1);
        tv_remarks[1] = (TextView) findViewById(R.id.tv_remarks2);
        tv_remarks[2] = (TextView) findViewById(R.id.tv_remarks3);
        tv_remarks[3] = (TextView) findViewById(R.id.tv_remarks4);
        tv_remarks[4] = (TextView) findViewById(R.id.tv_remarks5);
        tv_remarks[5] = (TextView) findViewById(R.id.tv_remarks6);
        tv_remarks[6] = (TextView) findViewById(R.id.tv_remarks7);


        tv_memberID = (TextView) findViewById(R.id.tv_memberID);
        tv_name = (TextView) findViewById(R.id.tv_name);

        btn_back = (FancyButton) toolbar.findViewById(R.id.btn_back);
        tv_age.setOnClickListener(this);
        btn_displayQR = (Button) findViewById(R.id.btn_displayQR);
        ci_edit = (CircleImageView) findViewById(R.id.ci_edit);
        btn_displayQR.setOnClickListener(this);
        ci_edit.setOnClickListener(this);
        btn_back.setOnClickListener(this);


        tv_memberID.setText(getIntent().getExtras().getString("MEMCODE"));
        tv_name.setText(getIntent().getExtras().getString("FNAME") + " " + getIntent().getExtras().getString("LNAME"));

        tv_birth.setText(getIntent().getExtras().getString("BIRTHDAY"));
        AgeCorrector ageCorrector = new AgeCorrector();
        tv_age.setText(ageCorrector.age(getIntent().getExtras().getString("AGE")));
        tv_civil_status.setText(getIntent().getExtras().getString("CIVIL"));
        tv_gender.setText(genderPicker.setGender((Integer.parseInt(getIntent().getExtras().getString("GENDER")))));
        tv_company.setText(getIntent().getExtras().getString("COMPANY"));

        StatusSetter statusSetter = new StatusSetter();
        tv_account_status.setText(statusSetter.setStatus(getIntent().getExtras().getString("STATUS")));
        tv_member_code.setText(MEMBER_ID);
        tv_member_type.setText(getIntent().getExtras().getString("MEMTYPE"));
        tv_member_type.setText(getIntent().getExtras().getString("MEMTYPE"));
        tv_effective_date.setText(getIntent().getExtras().getString("EFFECTIVE"));
        tv_validity_date.setText(getIntent().getExtras().getString("VALIDITY"));
        tv_plan.setText(getIntent().getExtras().getString("PLAN"));

        //SAVE TO SHARED PREF FOR LAST ACTIVITY RTHIS WLL BE SHOWN
        SharedPref.setStringValue(SharedPref.USER, SharedPref.VAL_DATE, getIntent().getExtras().getString("VALIDITY"), context);
        SharedPref.setStringValue(SharedPref.USER, SharedPref.EFF_DATE, getIntent().getExtras().getString("EFFECTIVE"), context);
        for (int x = 0; x < 7; x++) {

            tv_remarks[x].setVisibility(View.GONE);

        }
        tv_remarks[0].setVisibility(View.VISIBLE);
        tv_remarks[0].setText(getIntent().getExtras().getString("REMARK"));


        sv_scroll.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (isFabOpen)
                    return true;
                else
                    return false;

            }
        });


        if (getIntent().getExtras().getString("USER").equals("DEPENDENT")) {
            userIsPrincipal = false;
        } else
            userIsPrincipal = true;


        if (!userIsPrincipal) {
            fab.setVisibility(View.GONE);
        }


        getPhoto();
        animateFAB();
        setFlag(getIntent().getExtras().getString("STATUS"));
    }

    private void setFlag(String status) {


        if (status.equals("DISAPPROVED") || status.equals("RESIGNED") || status.equals("CANCELLED") ||
                status.equals("LAPSE (NON RENEW)") || status.equals("FOR REACTIVATION")) {

            showBlockDialog(context, status);

        }


    }


    public void showBlockDialog(Context context, final String status) {

        final Dialog dialog = new Dialog(context);
        final Button btn_proceed;


        final ProgressDialog pd;
        pd = new ProgressDialog(context, R.style.MyTheme);
        pd.setCancelable(false);
        pd.setMessage("Loading Doctors...");
        pd.setProgressStyle(android.R.style.Widget_ProgressBar_Small);


        TextView tv_title;

        dialog.setContentView(R.layout.dialog_blocked);
        btn_proceed = (Button) dialog.findViewById(R.id.btn_proceed);

        tv_title = (TextView) dialog.findViewById(R.id.textView3);
        tv_title.setText(SetBlockedUser.setTitle(status));
        btn_proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();


            }
        });


        dialog.show();

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        dialog.getWindow().setLayout(width, Toolbar.LayoutParams.WRAP_CONTENT);
    }


    private void animateFAB() {

        if (isFabOpen) {
            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);


            tv_view.setAnimation(fab_close);

            blackBG.startAnimation(fade_out);
            blackBG.setVisibility(View.GONE);

            fab1.setClickable(false);

            isFabOpen = false;

        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);


            tv_view.setAnimation(fab_open);

            blackBG.startAnimation(fade_in);
            blackBG.setVisibility(View.VISIBLE);

            fab1.setClickable(true);

            isFabOpen = true;


        }
    }


    private void getPhoto() {


        progressBar.setVisibility(View.VISIBLE);


        Picasso.with(context)

                .load(AppInterface.PHOTOLINK + MEMBER_ID)
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .placeholder(R.drawable.imageplaceholder)
                .error(R.drawable.imageplaceholder)
                .into(circleImageView2, new Callback() {
                    @Override
                    public void onSuccess() {

                        progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }


    @Override
    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.btn_back:

                // startActivity(new Intent(MemberInfoActivity.this , NavigationActivity.class));
                // overridePendingTransition(R.anim.fade_in_animation , 0);
                finish();
                break;

            case R.id.btn_displayQR:
                if (!isFabOpen) {

                    Intent gotoQrCodeView = new Intent(context, QrCodeActivity.class);
                    gotoQrCodeView.putExtra("NAME", getIntent().getExtras().getString("FNAME") + " " + getIntent().getExtras().getString("LNAME"));
                    gotoQrCodeView.putExtra("MEMBER_ID", getIntent().getExtras().getString("MEMCODE"));
                    gotoQrCodeView.putExtra("BIRTHDAY", getIntent().getExtras().getString("BIRTHDAY"));
                    startActivity(gotoQrCodeView);
                    overridePendingTransition(R.anim.push_up_in, R.anim.push_down_out);

                }
                break;

            case R.id.ci_edit:
                if (!isFabOpen) {
                    showMe(context);
                }
                break;


            case R.id.fab:
                animateFAB();
                break;

            case R.id.fab1:
                animateFAB();
                gotoRequestButton();
                break;


        }
    }


    private void gotoRequestButton() {

        Intent intent = new Intent(context, RequestButtonsActivity.class);
        intent.putExtra(Constant.MEMBER_ID, MEMBER_ID);
        intent.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
        intent.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.FNAME) + " " + getIntent().getExtras().getString(Constant.LNAME));
        intent.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
        intent.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
        intent.putExtra(Constant.AGE, getIntent().getExtras().getString(Constant.AGE));
        startActivity(intent);

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent event) {
        Log.d("RETURN", event.getMessage());


    }

    private void showDeletePhoto(String id) {

        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Removing photo will require member to go to validation process. Continue?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        if (NetworkTest.isOnline(context)) {
                            deleteImage(MEMBER_ID);
                        } else {
                            getPhoto();
                            alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);

                        }

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        getPhoto();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    private void deleteImage(String member_id) {

        pd.show();
        AppInterface appInterface;
        appInterface = AppServicewithBasicAuth.createApiService(AppInterface.class, AppInterface.ENDPOINT, sharedPref.getStringValue(sharedPref.USER, sharedPref.masterUSERNAME, context), sharedPref.getStringValue(sharedPref.USER, sharedPref.masterPASSWORD, context));
        appInterface.deletePhoto(member_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Upload error:", e.getMessage());
                        pd.dismiss();
                        alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.unknown_msg, 1);
                        getPhoto();
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        alertDialogCustom.showMe(context, alertDialogCustom.success, alertDialogCustom.delete_msg, 2);
                        pd.dismiss();
                        getPhoto();
                    }
                });
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }

    public static class MessageEvent {

        private String message;

        public MessageEvent(String message) {
            this.message = message;
        }


        public String getMessage() {
            return message;
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            if (requestCode == CAMERA_RQ) {

                if (resultCode != Activity.RESULT_CANCELED) {

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inSampleSize = 8;


                    //get file
                    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File output = new File(dir + File.separator + "CameraMEDICARD.jpeg");
                    Log.d("FILE_PATH", output.getAbsolutePath());

                    //ROtate if need
                    Bitmap bitmap;
                    ImageConverters imageConverters = new ImageConverters();
                    bitmap = imageConverters.rotateBitmapOrientation(output.getAbsolutePath());


                    //Save bitmap
                    imageConverters.saveToInternalStorage(circleImageView2, bitmap, context, true);


                    try {

                        File f = new File(dir, "CameraMEDICARD.jpeg");
                        Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
                        circleImageView2.setImageBitmap(b);
                        Log.v("Upload", f.toString());
                        showImageUpload(f, MEMBER_ID);

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }


            } else if (requestCode == GALLERY_RQ) {


                try {
                    //get file
                    Uri selectedImageUri = data.getData();
                    String imagepath = imageConverter.getRealPathFromURI(selectedImageUri, context);
                    File imageFile = new File(imagepath);
                    Bitmap b = BitmapFactory.decodeStream(new FileInputStream(imageFile));

                    //Save bitmap
                    Log.d("WIDTH", b.getWidth() + "");
                    Log.d("Height", b.getHeight() + "");
                    imageConverter.saveToInternalStorage(circleImageView2, b, context, false);

                    //send bitmap
                    File dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    File f = new File(dir, "GalleryMEDICARD.jpeg");
                    Bitmap newBitmap = BitmapFactory.decodeStream(new FileInputStream(f));
                    circleImageView2.setImageBitmap(newBitmap);
                    Log.v("Upload", f.toString());
                    showImageUpload(f, MEMBER_ID);
                } catch (Exception e) {
                    Log.e("ERROR", e.getMessage());
                }
            } else {

            }


        } catch (Exception e) {

        }


    }

    public void showImageUpload(final File file, final String memberID) {


        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Uploading of photo will request validation. Continue?");
        builder1.setCancelable(false);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                        if (NetworkTest.isOnline(context)) {
                            uploadImage(file, memberID);
                        } else {
                            getPhoto();
                            alertDialogCustom.showMe(context, alertDialogCustom.NO_Internet_title, alertDialogCustom.NO_Internet, 1);

                        }

                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        getPhoto();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();


    }

    private void uploadImage(final File file, String memberCode) {

        pd.show();

        RequestBody fbody = RequestBody.create(MediaType.parse("image/jpeg"), file);
        RequestBody name = RequestBody.create(MediaType.parse("text/plain"), memberCode);
        Log.v("Upload", file.toString());
        AppInterface appInterface;
        appInterface = AppService.createApiService(AppInterface.class, AppInterface.ENDPOINT);
        appInterface.upload(fbody, name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
                .subscribe(new Subscriber<ResponseBody>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        try {


                            Log.e("Upload error:", e.getMessage());
                            pd.dismiss();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorUploadPhoto, 1);
                            getPhoto();
                        } catch (Exception error) {

                            Log.e("Upload error:", e.getMessage());
                            pd.dismiss();
                            alertDialogCustom.showMe(context, alertDialogCustom.HOLD_ON_title, alertDialogCustom.errorUploadPhoto, 1);
                            getPhoto();

                            Log.e("Rx_ERROR", error.getMessage());
                        }

                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        Log.v("Upload", "success");
                        pd.dismiss();
                        alertDialogCustom.showMe(context, alertDialogCustom.success, alertDialogCustom.success_msg, 2);
                        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
                        circleImageView2.setImageBitmap(myBitmap);

                    }
                });
    }


    // CHOOSE CAMERA OR GALLERY

    public void showMe(Context contex) {

        final CharSequence[] items = {CAMERA, GALLERY, CANCEL};

        builder = new AlertDialog.Builder(contex);
        alert = builder.create();
        alert.show();
        builder.setTitle("Choose Option:");
        builder.setItems(items, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {


                setDetails(items[item]);


            }
        }).show();

    }

    private void setDetails(CharSequence item) {


        if (item.equals(GALLERY)) {
            if (Permission.checkPermissionStorage(context)) {

                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, GALLERY_RQ);
            }

        }
        if (item.equals(CAMERA)) {


            if (Permission.checkPermissionCamera(context)) {
                if (Permission.checkPermissionStorage(context)) {

                    Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    File dir =
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                    output = new File(dir, "CameraMEDICARD.jpeg");
                    i.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(output));

                    startActivityForResult(i, CAMERA_RQ);

                }
            }
        }


        if (item.equals(CANCEL)) {

            alert.dismiss();

        }

    }


    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

}

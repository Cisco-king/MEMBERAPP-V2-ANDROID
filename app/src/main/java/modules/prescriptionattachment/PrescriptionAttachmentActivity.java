package modules.prescriptionattachment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Config;
import android.util.Log;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.itextpdf.text.pdf.codec.Base64;
import com.medicard.member.R;
import com.medicard.member.module.diagnosistest.TestByDiagnosisActivity;
import com.medicard.member.module.hospital.HospitalActivity;
import com.tapadoo.alerter.Alerter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.IncompleteAnnotationException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Attachment;
import model.ImageAttachment;
import model.newtest.AttachmentSession;
import modules.base.activities.BaseActivity;
import modules.dummy.DummyActivity;
import modules.prescriptionattachment.adapter.AttachmentAdapter;
import modules.requestnewapproval.RequestNewActivity;
import timber.log.Timber;
import utilities.AlertDialogCustom;
import utilities.Constant;
import utilities.FileUtils;
import utilities.ImageConverters;
import utilities.Permission;
import utilities.PermissionUtililities;

public class PrescriptionAttachmentActivity extends BaseActivity
        implements PrescriptionAttachmentMvp.View, AttachmentAdapter.OnAttachmentClickListener {


    public static final int SELECT_PICTURE = 100;
    public static final int SELECT_PICTURE_FROM_CAMERA = 101;
    public static final int MEDIA_TYPE_IMAGE = 1;
    public int o = 0;
    public int count = 0;
    public File f;

    AttachmentSession attachmentSession;
    String memberStatus = "";

    public static final String ATTACHMENT = "attachment";
    Bitmap photo;

    //this is for activityresult in image
    final int CAMERA_RQ = 290;
    private String encodedString;
    private ProgressDialog pd;


    @BindView(R.id.rvAttachment)
    RecyclerView rvAttachment;


    @BindView(R.id.btnUpload)
    Button btnUpload;

    @BindView(R.id.btn_next)
    Button btn_next;

    //Store the url of the file
    private Uri fileUri;

    private PrescriptionAttachmentMvp.Presenter presenter;

    private AttachmentAdapter attachmentAdapter;
    private List<ImageAttachment> imageAttachments;

    private Uri mCapturedImageURI = null;
    private List<Attachment> attachments;

    int counter = 0;

    File output = null;

    AlertDialog.Builder builder;
    AlertDialog alert;

    //List of strings for choosing a camera
    public String GALLERY = "Gallery";
    public String CAMERA = "Camera";
    public String CANCEL = "Cancel";

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_prescription_attachment;
    }

    @Override
    protected void initViews() {
        super.initViews();

        presenter = new PrescriptionAttachmentPresenter();

        presenter.attachView(this);
        presenter.attachCallback();

        setupWindowAnimations();

        pd = new ProgressDialog(this);


        imageAttachments = new ArrayList<>();

        attachments = new ArrayList<>();
        attachmentAdapter = new AttachmentAdapter(context,attachments, this);

        rvAttachment.setLayoutManager(new LinearLayoutManager(context));
        rvAttachment.setAdapter(attachmentAdapter);

        attachmentSession = new AttachmentSession();

        memberStatus = getIntent().getExtras().getString(Constant.MEM_STATUS);

        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.detachCallback();
    }

    @OnClick(R.id.btnUpload)
    public void onUpload() {
        if (attachments.size() != 3) {
            if (PermissionUtililities.hasPermissionToReadAndWriteStorage(this)) {
                showMe(context);
            }
        } else {
            Alerter.create(this)
                    .setText("Exceeded number of attachments!")
                    .setBackgroundColor(R.color.orange_a200)
                    .show();
        }

    }


    @OnClick(R.id.btn_next)
    public void onNextClick() {
        try {
            if (attachments.size() != 0) {
                if (PermissionUtililities.hasPermissionToReadAndWriteStorage(this)) {
                    Intent goToHospitalActivity = new Intent(this, HospitalActivity.class);
                    goToHospitalActivity.putExtra(RequestNewActivity.ATTACHMENT, new ArrayList<>(attachments));
                    goToHospitalActivity.putExtra(Constant.MEMBER_ID, getIntent().getExtras().getString(Constant.MEMBER_ID));
                    goToHospitalActivity.putExtra(Constant.GENDER, getIntent().getExtras().getString(Constant.GENDER));
                    goToHospitalActivity.putExtra(Constant.BIRTHDAY, getIntent().getExtras().getString(Constant.BIRTHDAY));
                    goToHospitalActivity.putExtra(Constant.NAME, getIntent().getExtras().getString(Constant.NAME));
                    goToHospitalActivity.putExtra(Constant.REMARK, getIntent().getExtras().getString(Constant.REMARK));
                    goToHospitalActivity.putExtra(Constant.COMPANY, getIntent().getExtras().getString(Constant.COMPANY));
                    goToHospitalActivity.putExtra(Constant.AGE, getIntent().getExtras().getString(Constant.AGE));
                    goToHospitalActivity.putExtra(Constant.MEM_STATUS, memberStatus);
                    transitionTo(goToHospitalActivity);
                }
            } else {
                Alerter.create(this)
                        .setText("Please upload Attachment(s) to proceed.")
                        .setBackgroundColor(R.color.orange_a200)
                        .show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
                Uri selectImageUri = data.getData();
                Timber.d("uri : %s", selectImageUri.toString());
                if (selectImageUri != null) {
                    try {
                        Bitmap picture = MediaStore.Images.Media.getBitmap(context.getContentResolver(), selectImageUri);
                        File file = new File(FileUtils.getPathFromURI(context, FileUtils.getImageUri(this, picture)));

                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        picture.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                        byte[] ba = bao.toByteArray();

                        try {
                            encodedString = Base64.encodeBytes(ba, 0);
                        } catch (Exception e1) {
                        }


                        System.out.println("File to save from gallery" + file);
                        attachmentSession.addAttachemnt(file);
                        counter++;
                        Timber.d("counter %s", counter);
                        try {
                            pd.setProgressStyle(R.style.MyTheme);
                            pd.setMessage("Loading");
                            pd.show();
                        /*imageAttachments.add(new ImageAttachment.Builder()
                                .image(picture)
                                .fileName(file.getName())
                                .build());
                        attachmentAdapter.update(imageAttachments);*/
                            attachments.add(
                                    new Attachment.Builder()
                                            .fileName(file.getName())
                                            .content(encodedString.toString())
                                            .uri(selectImageUri.toString())
                                            .build());
                            attachmentAdapter.update(attachments);
                            pd.dismiss();


                        } catch (Exception e) {
                            Timber.d("error message %s", e.toString());
                        }
                    } catch (IOException e) {
                        Timber.d(e.toString());
                    }
                }
            } else if (requestCode == CAMERA_RQ) {
                if (resultCode != Activity.RESULT_CANCELED) {
                    if (data != null) {
                        ImageConverters imageConverters = new ImageConverters();

                        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);

                        photo = (Bitmap) data.getExtras().get("data");
                        Bitmap bitmapOrg = photo;
                        ByteArrayOutputStream bao = new ByteArrayOutputStream();
                        bitmapOrg.compress(Bitmap.CompressFormat.JPEG, 100, bao);
                        byte[] ba = bao.toByteArray();

                        try {
                            encodedString = Base64.encodeBytes(ba, 0);
                        } catch (Exception e1) {
                        }

                        if (android.os.Environment.getExternalStorageState()
                                .equals(android.os.Environment.MEDIA_MOUNTED)) {

                            String fileName = +count + ".jpg";
                            f = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                                    "/IMG_" + fileName);
                            try {
                                f.createNewFile();
                                FileOutputStream fo = new FileOutputStream(f);
                                fo.write(ba);
                                fo.close();
                            } catch (IOException e) {

                            }


                        }
                        attachmentSession.addAttachemnt(f);
                        counter++;

                        try {
                        /*imageAttachments.add(new ImageAttachment.Builder()
                                .image(picture)
                                .fileName(file.getName())
                                .build());
                        attachmentAdapter.update(imageAttachments);*/
                            attachments.add(
                                    new Attachment.Builder()
                                            .fileName(f.getName())
                                            .content(encodedString.toString())
                                            .build());
                            attachmentAdapter.update(attachments);

                        } catch (Exception e) {
                            Timber.d("error message %s", e.toString());
                        }


                    }
                } else {


                }
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case PermissionUtililities.REQUESTCODE_STORAGE_PERMISSION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    uploadAttachment();

                } else {
                    Timber.d("permission denied");
                }
            }

            return;
        }
    }


    public void uploadAttachment() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    }

    @Override
    public void onRemoveAttachment(int position) {
        //remove the current attachemt's position
        attachments.remove(position);
        //attachmentAdapter.update(imageAttachments);
        attachmentAdapter.update(attachments);
    }




    /*
     * This lines of code is intended for accessing the camera of the phone
     *
     */

    private void setDetails(CharSequence item) {


        if (item.equals(GALLERY)) {
            uploadAttachment();
            alert.dismiss();
        }

        if (item.equals(CAMERA)) {


            if (Permission.checkPermissionCamera(context)) {
                if (Permission.checkPermissionStorage(context)) {
                    takePhoto();
                }
                alert.dismiss();
            }
        }


        if (item.equals(CANCEL)) {

            alert.dismiss();

        }
    }

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

    private void takePhoto() {

        //increment the number of image from the camera intent
        count++;
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");

        // start the image capture Intent
        startActivityForResult(i, CAMERA_RQ);
    }

    /**
     * Checking device has camera hardware or not
     */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }

    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * returning image / video
     */
    private static File getOutputMediaFile(int type) {

        // External sdcard location
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), Constant.IMAGE_DIRECTORY_NAME);

        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {

                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date());
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");

        } else {
            return null;
        }

        return mediaFile;
    }


}

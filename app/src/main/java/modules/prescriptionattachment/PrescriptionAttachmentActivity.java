package modules.prescriptionattachment;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.medicard.member.R;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.IncompleteAnnotationException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import mehdi.sakout.fancybuttons.FancyButton;
import model.Attachment;
import model.ImageAttachment;
import modules.base.activities.BaseActivity;
import modules.dummy.DummyActivity;
import modules.prescriptionattachment.adapter.AttachmentAdapter;
import modules.requestnewapproval.RequestNewActivity;
import timber.log.Timber;
import utilities.FileUtils;
import utilities.PermissionUtililities;

public class PrescriptionAttachmentActivity extends BaseActivity
        implements PrescriptionAttachmentMvp.View, AttachmentAdapter.OnAttachmentClickListener {


    public static final int SELECT_PICTURE = 100;

    public static final String ATTACHMENT = "attachment";

    @BindView(R.id.rvAttachment)
    RecyclerView rvAttachment;

    @BindView(R.id.fbDone)
    FancyButton fbDone;

    @BindView(R.id.btnUpload)
    Button btnUpload;

    private PrescriptionAttachmentMvp.Presenter presenter;

    private AttachmentAdapter attachmentAdapter;
    private List<ImageAttachment> imageAttachments;

    private List<Attachment> attachments;

    int counter = 0;

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

        imageAttachments = new ArrayList<>();

        attachments = new ArrayList<>();
        attachmentAdapter = new AttachmentAdapter(attachments, this);

        rvAttachment.setLayoutManager(new LinearLayoutManager(this));
        rvAttachment.setAdapter(attachmentAdapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.detachCallback();
    }

    @OnClick(R.id.btnUpload)
    public void onUpload() {
        if (PermissionUtililities.hasPermissionToReadAndWriteStorage(this)) {
            uploadAttachment();
        }

    }

    @OnClick(R.id.fbDone)
    public void onDoneClick() {
//        Intent intent = new Intent(this, RequestNewActivity.class);
        /*Intent intent = new Intent(this, DummyActivity.class);
        intent.putExtra(ATTACHMENT, new ArrayList<>(attachments));
        startActivity(intent);*/
        if (PermissionUtililities.hasPermissionToReadAndWriteStorage(this)) {
            Intent intent = new Intent(this, RequestNewActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.putExtra(RequestNewActivity.ATTACHMENT, new ArrayList<>(attachments));
            transitionTo(intent);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == SELECT_PICTURE) {
            Uri selectImageUri = data.getData();
            Timber.d("uri : %s", selectImageUri.toString());
            if (selectImageUri != null) {
                try {
                    Bitmap picture = MediaStore.Images.Media.getBitmap(context.getContentResolver(), selectImageUri);
                    File file = new File(FileUtils.getPathFromURI(context, FileUtils.getImageUri(this, picture)));
                    Timber.d("file name %s", file.getName());

                    counter++;
                    Timber.d("counter %s", counter);
                    try {
                        /*imageAttachments.add(new ImageAttachment.Builder()
                                .image(picture)
                                .fileName(file.getName())
                                .build());
                        attachmentAdapter.update(imageAttachments);*/
                        attachments.add(
                                new Attachment.Builder()
                                        .fileName(file.getName())
                                        .uri(selectImageUri.toString())
                                        .build());
                        attachmentAdapter.update(attachments);

                    } catch (Exception e) {
                        Timber.d("error message %s", e.toString());
                    }
                } catch (IOException e) {
                    Timber.d(e.toString());
                }
            }
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
        attachments.remove(position);
//        attachmentAdapter.update(imageAttachments);
        attachmentAdapter.update(attachments);
    }
}

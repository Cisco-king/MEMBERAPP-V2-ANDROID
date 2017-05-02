package modules.testattachment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.widget.Button;

import com.medicard.member.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import butterknife.BindView;
import butterknife.OnClick;
import constants.PermissionConstant;
import mehdi.sakout.fancybuttons.FancyButton;
import modules.base.activities.TestTrackableActivity;
import modules.requestnewapproval.RequestNewActivity;
import timber.log.Timber;
import utilities.ImageConverters;
import utilities.Permission;

public class TestAttachmentActivity extends TestTrackableActivity implements TestAttachment.View {


    @BindView(R.id.fbDone) FancyButton fbDone;
    @BindView(R.id.btnUploadAttachment) Button btnUploadAttachment;

    private ImageConverters imageConverters;
    private TestAttachment.Presenter presenter;

    @Override
    protected int getLayoutResource() {
        return R.layout.activity_test_attachment;
    }

    @Override
    protected void initViews() {
        super.initViews();
        setToolbarCustomableTitle("Tests");

        imageConverters = new ImageConverters();
        presenter = new TestAttachmentPresenter();

        presenter.attachView(this);
        presenter.attachCallback();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
        presenter.detachCallback();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_OK && resultCode == PermissionConstant.GALLERY) {
            try {
                // get the image file from repo
                Uri selectedImageUri = data.getData();
                String imagepath = imageConverters.getRealPathFromURI(selectedImageUri, context);
                File imageFile = new File(imagepath);
                Bitmap bitmap = BitmapFactory.decodeStream(new FileInputStream(imageFile));


            } catch (FileNotFoundException e) {
                Timber.d("%s", e.toString());
            }

        }
    }

    @OnClick(R.id.fbDone)
    public void onDoneClick() {
        startActivity(new Intent(this, RequestNewActivity.class));
    }

    @OnClick(R.id.btnUploadAttachment)
    public void onUploadAttachment() {
        if (Permission.checkPermissionStorage(context)) {
            Intent galleryIntent =
                    new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(galleryIntent, PermissionConstant.GALLERY);
        } else {
            Timber.d("gallery permission denied by android software");
        }
    }

}

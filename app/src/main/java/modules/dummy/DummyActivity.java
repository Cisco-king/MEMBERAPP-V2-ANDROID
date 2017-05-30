package modules.dummy;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.medicard.member.R;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Attachment;
import modules.prescriptionattachment.PrescriptionAttachmentActivity;
import timber.log.Timber;

/**
 * This activity is for testing purposes only
 */
public class DummyActivity extends AppCompatActivity {


    @BindView(R.id.ivUpload) ImageView ivUpload;
    ArrayList<Attachment> attachments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dummy);
        ButterKnife.bind(this);

        attachments = getIntent().getParcelableArrayListExtra(PrescriptionAttachmentActivity.ATTACHMENT);
        if (attachments.size() > 0) {
            Timber.d("attacment size from dummy %s", attachments.size());
            Uri parse = Uri.parse(attachments.get(0).getUri());
            try {
                Bitmap picture = MediaStore.Images.Media.getBitmap(this.getContentResolver(), parse);
                ivUpload.setImageBitmap(picture);
            } catch (IOException e) {
                Timber.d("error %s", e.toString());
            }
        }
    }


}

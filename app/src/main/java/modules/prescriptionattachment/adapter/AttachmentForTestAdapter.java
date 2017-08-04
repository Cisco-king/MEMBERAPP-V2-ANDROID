package modules.prescriptionattachment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Attachment;
import model.ImageAttachment;
import timber.log.Timber;

/**
 * Created by macbookpro on 7/25/17.
 */

public class AttachmentForTestAdapter extends
        RecyclerView.Adapter<AttachmentForTestAdapter.ViewHolder> {



    private ArrayList<Attachment> attachments;

    public AttachmentForTestAdapter(ArrayList<Attachment> attachments) {

        this.attachments = attachments;
    }


    @Override
    public AttachmentForTestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_file_attachment, parent, false);
        return new AttachmentForTestAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(AttachmentForTestAdapter.ViewHolder holder, int position) {
//        ImageAttachment imageAttachment = imageAttachments.get(position);
        Attachment attachment = attachments.get(position);
//        holder.tvHospitalOrClinicName.setText(imageAttachment.getFileName());
        holder.tv_attachments.setText(attachment.getFileName());
    }

    public void update(/*List<ImageAttachment> imageAttachments*/ArrayList<Attachment> attachments) {
        Timber.d("image attachments size %s", attachments.size());
        this.attachments = attachments;
        notifyDataSetChanged();
    }

    public void addAttachment(/*ImageAttachment imageAttachment*/Attachment attachment) {
//        this.imageAttachments.add(imageAttachment);
        this.attachments.add(attachment);
        notifyDataSetChanged();
    }

    public void removeAttachment(int position) {
        /*if (imageAttachments.size() > 0) {
            imageAttachments.remove(position);
            notifyDataSetChanged();
        }*/
        if (attachments.size() > 0) {
            attachments.remove(position);
            notifyDataSetChanged();
        }
    }

    @Override
    public int getItemCount() {
        return attachments.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_attachments)
        TextView tv_attachments;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);

        }
    }

}

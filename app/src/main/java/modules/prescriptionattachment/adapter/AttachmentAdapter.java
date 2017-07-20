package modules.prescriptionattachment.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.medicard.member.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Attachment;
import model.ImageAttachment;
import timber.log.Timber;

/**
 *
 * Created by casjohnpaul on 5/14/2017.
 */

public class AttachmentAdapter extends
        RecyclerView.Adapter<AttachmentAdapter.ViewHolder> {

    private List<ImageAttachment> imageAttachments;

    private List<Attachment> attachments;
    private OnAttachmentClickListener onItemClickListener;

    public AttachmentAdapter(/*List<ImageAttachment> imageAttachments*/List<Attachment> attachments, OnAttachmentClickListener onItemClickListener) {
//        this.imageAttachments = imageAttachments;
        this.attachments = attachments;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_file, parent, false);
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        ImageAttachment imageAttachment = imageAttachments.get(position);
        Attachment attachment = attachments.get(position);
//        holder.tvHospitalOrClinicName.setText(imageAttachment.getFileName());
        holder.tvHospitalOrClinicName.setText(attachment.getFileName());
    }

    public void update(/*List<ImageAttachment> imageAttachments*/List<Attachment> attachments) {
        Timber.d("image attachments size %s", attachments.size());
        this.attachments = attachments;
        this.imageAttachments = imageAttachments;
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

    public class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        @BindView(R.id.tvHospitalOrClinicName)
        TextView tvHospitalOrClinicName;
        @BindView(R.id.btnRemove)
        Button btnRemove;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            btnRemove.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btnRemove) {
                onItemClickListener.onRemoveAttachment(getAdapterPosition());
            }
        }
    }

    public interface OnAttachmentClickListener {
        void onRemoveAttachment(int position);
    }

}

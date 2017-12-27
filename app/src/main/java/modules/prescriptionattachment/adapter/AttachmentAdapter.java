package modules.prescriptionattachment.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import utilities.AlertDialogCustom;

/**
 * Created by casjohnpaul on 5/14/2017.
 */

public class AttachmentAdapter extends
        RecyclerView.Adapter<AttachmentAdapter.ViewHolder> {

    private List<ImageAttachment> imageAttachments;

    private List<Attachment> attachments;
    private OnAttachmentClickListener onItemClickListener;
    private AlertDialogCustom alertDialogCustom;
    private Context context;

    public AttachmentAdapter(Context context,/*List<ImageAttachment> imageAttachments*/List<Attachment> attachments, OnAttachmentClickListener onItemClickListener) {
//        this.imageAttachments = imageAttachments;
        this.attachments = attachments;
        this.context = context;
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_file, parent, false);
        alertDialogCustom = new AlertDialogCustom();
        return new ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
//        ImageAttachment imageAttachment = imageAttachments.get(position);
        Attachment attachment = attachments.get(position);
//        holder.tvHospitalOrClinicName.setText(imageAttachment.getFileName());
        holder.tvHospitalOrClinicName.setText(attachment.getFileName());
        try {
            byte[] decodedString = Base64.decode(attachment.getContent(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.iv_file.setImageBitmap(decodedByte);
        } catch (Exception e) {
            holder.iv_file.setVisibility(View.GONE);
        }

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
        @BindView(R.id.iv_file)
        ImageView iv_file;
        @BindView(R.id.lv_file)
        LinearLayout lv_file;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            btnRemove.setOnClickListener(this);
            lv_file.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.btnRemove:
                    onItemClickListener.onRemoveAttachment(getAdapterPosition());
                    break;
                case R.id.lv_file:
                    alertDialogCustom.showSelectedAttachment(context, attachments, getAdapterPosition(), "");
                    break;
            }

        }
    }

    public interface OnAttachmentClickListener {
        void onRemoveAttachment(int position);
    }

}

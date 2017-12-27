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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import model.Attachment;
import model.ImageAttachment;
import timber.log.Timber;
import utilities.AlertDialogCustom;

/**
 * Created by macbookpro on 7/25/17.
 */

public class AttachmentForTestAdapter extends
        RecyclerView.Adapter<AttachmentForTestAdapter.ViewHolder> {


    private ArrayList<Attachment> attachments;
    private Context context;
    AlertDialogCustom alertDialogCustom;

    public AttachmentForTestAdapter(Context context, ArrayList<Attachment> attachments) {

        this.attachments = attachments;
        this.context = context;
    }


    @Override
    public AttachmentForTestAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.loa_file_row, parent, false);
        alertDialogCustom = new AlertDialogCustom();
        return new AttachmentForTestAdapter.ViewHolder(row);
    }

    @Override
    public void onBindViewHolder(AttachmentForTestAdapter.ViewHolder holder, int position) {
//        ImageAttachment imageAttachment = imageAttachments.get(position);
        Attachment attachment = attachments.get(position);
//        holder.tvHospitalOrClinicName.setText(imageAttachment.getFileName());
        holder.tv_no.setText("" + (position + 1));

        try{
            byte[] decodedString = Base64.decode(attachment.getContent(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            holder.iv_image.setImageBitmap(decodedByte);
        }catch (Exception e){
            holder.iv_image.setVisibility(View.GONE);
        }

        holder.tv_filename.setText(attachment.getFileName());
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
        @BindView(R.id.tv_no)
        TextView tv_no;
        @BindView(R.id.iv_image)
        ImageView iv_image;
        @BindView(R.id.tv_filename)
        TextView tv_filename;
        @BindView(R.id.lv_file)
        LinearLayout lv_file;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            lv_file.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialogCustom.showSelectedAttachment(context,attachments,getAdapterPosition(),"","");
                }
            });
        }
    }

}

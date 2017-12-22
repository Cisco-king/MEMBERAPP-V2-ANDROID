package adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.medicard.member.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import services.model.AttachmentObject;
import services.model.MaceRequest;
import utilities.AlertDialogCustom;
import utilities.SharedPref;

/**
 * Created by IPC on 12/21/2017.
 */

public class FileUploadLoaAdapter extends RecyclerView.Adapter<FileUploadLoaAdapter.Holder> {
    private Context context;
    ArrayList<AttachmentObject> attachmentObjectArrayList = new ArrayList<>();
   AlertDialogCustom alertDialogCustom;

    public FileUploadLoaAdapter(Context context, ArrayList<AttachmentObject> attachmentObjectArrayList,AlertDialogCustom alertDialogCustom) {
        this.context = context;
        this.attachmentObjectArrayList = attachmentObjectArrayList;
        this.alertDialogCustom = alertDialogCustom;
    }


    @Override
    public FileUploadLoaAdapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.loa_file_row, parent, false);
        return new Holder(view);

    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {


        AttachmentObject attachmentObject = attachmentObjectArrayList.get(position);

        holder.tv_no.setText("" + (position + 1));

        byte[] decodedString = Base64.decode(attachmentObject.getContent(), Base64.DEFAULT);
        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);

        holder.iv_image.setImageBitmap(decodedByte);
        holder.tv_filename.setText(attachmentObject.getOriginalFileName());

    }

    @Override
    public int getItemCount() {
        return attachmentObjectArrayList.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_no)
        TextView tv_no;
        @BindView(R.id.iv_image)
        ImageView iv_image;
        @BindView(R.id.tv_filename)
        TextView tv_filename;
        @BindView(R.id.lv_file)
        LinearLayout lv_file;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            lv_file.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialogCustom.showSelectedAttachment(context,attachmentObjectArrayList,getAdapterPosition());
                }
            });
        }
    }
}
